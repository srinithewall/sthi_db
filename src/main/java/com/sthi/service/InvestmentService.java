package com.sthi.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sthi.dto.InvestmentTypeDto;
import com.sthi.model.Investment;
import com.sthi.model.InvestmentType;
import com.sthi.repo.InvestmentRepo;
import com.sthi.repo.InvestmentTypeRepo;

import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class InvestmentService {
    private static final Logger logger = LoggerFactory.getLogger(InvestmentService.class);

    private final InvestmentRepo investmentRepo;
    private final InvestmentTypeRepo investmentTypeRepo;

    public InvestmentService(InvestmentRepo investmentRepo, InvestmentTypeRepo investmentTypeRepo) {
        this.investmentRepo = investmentRepo;
        this.investmentTypeRepo = investmentTypeRepo;
    }

    // -------------------------
    // Main Business Methods
    // -------------------------

    /**
     * Update the status of a single installment and recalculate earned/balance
     * for all installments from #1 up to the last installment that has status = 1.
     */
    @Transactional
    public Investment updateInvestmentStatus(Long investmentId, Integer status) {
        logger.info("Updating status for investment ID: {} to status: {}", investmentId, status);

        // 1) Load + update this installment's status (in-memory)
        Investment investment = loadInvestmentOrThrow(investmentId);
        investment.setStatus(status);
        investment.setUpdatedAt(LocalDateTime.now());

        // Persist the status change first so subsequent reads see the updated status
        investmentRepo.save(investment);

        // 2) Load investment type + parse rate
        InvestmentType type = loadInvestmentTypeOrThrow(investment.getTypeId());
        double rate = parseInterestRate(type); // "1%" -> 0.01

        // 3) Recalculate sequentially up to last paid installment and persist those recalculated rows
        recalcCarryForwardUpToLastPaid(investment.getUserId(), investment.getTypeId(), rate);

        // Return the saved investment (status already saved above)
        return investment;
    }

    @Transactional
    public void assignInvestment(Integer investmentTypeId, Integer userId) {
        boolean exists = investmentRepo.existsByUserIdAndTypeId(userId, investmentTypeId);
        if (exists) throw new IllegalStateException("Investment type already assigned to user");

        InvestmentType type = investmentTypeRepo.findById(investmentTypeId)
                .orElseThrow(() -> new EntityNotFoundException("Investment type not found"));

        List<Investment> investments = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        YearMonth current = YearMonth.from(now);

        for (int i = 0; i < type.getDuration(); i++) {
            YearMonth ym = current.plusMonths(i);
            String month = ym.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH).toUpperCase();

            Investment inv = new Investment();
            inv.setTypeId(investmentTypeId);
            inv.setUserId(userId);
            inv.setNo(i + 1);
            inv.setMonth(month);
            inv.setYear(ym.getYear());
            inv.setAmount(type.getAmount());
            inv.setBonus(0);             
            inv.setStatus(0);             
            inv.setEarned(0);             
            inv.setBalance(0);            
            inv.setCreatedAt(now);
            inv.setUpdatedAt(now);
            investments.add(inv);
        }
        investmentRepo.saveAll(investments);
    }

    // -------------------------
    // Recalculation Logic
    // -------------------------

    /**
     * Recalculate sequentially from installment #1 up to the highest installment number
     * that currently has status = 1 (lastPaidNo).
     *
     * For each installment in that range:
     *  - principal = prevBalance + (amount + bonus)  if status == 1
     *  - principal = prevBalance                       if status == 0
     *  - earned  = principal * rate
     *  - balance = principal + earned
     *
     * Persist recalculated earned/balance/updatedAt for every row in that range
     * (this updates unpaid rows too so they reflect compounded interest).
     */
    private void recalcCarryForwardUpToLastPaid(Integer userId, Integer typeId, double rate) {
        List<Investment> all = investmentRepo.findByUserIdAndTypeIdOrderByNoAsc(userId, typeId);
        if (all.isEmpty()) {
            logger.info("No installments found for userId={} typeId={}", userId, typeId);
            return;
        }

        // Find the highest installment number that currently has status=1
        int lastPaidNo = 0;
        for (Investment inv : all) {
            Integer s = inv.getStatus();
            Integer no = inv.getNo();
            if (s != null && s == 1 && no != null && no > lastPaidNo) {
                lastPaidNo = no;
            }
        }

        if (lastPaidNo == 0) {
            logger.info("No paid installments found for userId={} typeId={} — nothing to recalc", userId, typeId);
            return;
        }

        logger.info("Recalculating installments 1..{} (lastPaidNo={} ) for userId={} typeId={}",
                lastPaidNo, lastPaidNo, userId, typeId);

        double prevBalance = 0.0;
        LocalDateTime now = LocalDateTime.now();
        List<Investment> toPersist = new ArrayList<>();

        for (Investment inv : all) {
            Integer no = inv.getNo();
            if (no == null) continue;
            if (no > lastPaidNo) break; // we only need to recalc up to lastPaidNo

            int status = inv.getStatus() == null ? 0 : inv.getStatus();
            double amount = inv.getAmount() == null ? 0.0 : inv.getAmount();
            double bonus  = inv.getBonus()  == null ? 0.0 : inv.getBonus();

            double principal = prevBalance + (status == 1 ? (amount + bonus) : 0.0);
            double earned    = round2(principal * rate);
            double balance   = round2(principal + earned);

            // Persist the computed earned and balance for EVERY row in the range,
            // so unpaid rows also get their earned/balance updated (as in your sample).
            // Keep the same integer rounding behavior as before to match your entity.
            inv.setEarned((int) Math.round(earned));
            inv.setBalance((int) Math.round(balance));
            inv.setUpdatedAt(now);
            toPersist.add(inv);

            prevBalance = balance;
            logger.debug("Recalc No={} status={} principal={} earned={} balance={} nextPrev={}",
                    no, status, principal, earned, balance, prevBalance);
        }

        if (!toPersist.isEmpty()) {
            investmentRepo.saveAll(toPersist);
            logger.info("Persisted recalculated values for {} installments (up to no={})", toPersist.size(), lastPaidNo);
        } else {
            logger.info("Nothing to persist after recalculation");
        }
    }

    // -------------------------
    // Helper Methods
    // -------------------------

    private Investment loadInvestmentOrThrow(Long investmentId) {
        return investmentRepo.findById(investmentId)
                .orElseThrow(() -> {
                    logger.error("Investment not found: {}", investmentId);
                    return new EntityNotFoundException("Investment id not found: " + investmentId);
                });
    }

    private InvestmentType loadInvestmentTypeOrThrow(Integer typeId) {
        if (typeId == null) {
            logger.error("Type ID is null for investment");
            throw new IllegalStateException("Investment type_id is null");
        }
        return investmentTypeRepo.findById(typeId)
                .orElseThrow(() -> {
                    logger.error("InvestmentType not found: {}", typeId);
                    return new EntityNotFoundException("InvestmentType not found: " + typeId);
                });
    }

    private double parseInterestRate(InvestmentType type) {
        String raw = type.getInterestRate();
        if (raw == null || raw.trim().isEmpty()) {
            throw new IllegalStateException("Interest rate is null or empty for investment type: " + type.getId());
        }
        try {
            String cleaned = raw.replace("%", "").trim();
            return Double.parseDouble(cleaned) / 100.0;
        } catch (NumberFormatException e) {
            logger.error("Invalid interest rate format: {}", raw);
            throw new IllegalStateException("Invalid interest rate format: " + raw);
        }
    }

    private static double round2(double v) {
        return Math.round(v * 100.0) / 100.0;
    }

    public List<InvestmentTypeDto> getAllInvestments() {
        return investmentTypeRepo.findAllInvestmentTypes();
    }
    
    public List<Investment> getledger(){
    	return investmentRepo.fetchAllInvestments();
    }
    
}
