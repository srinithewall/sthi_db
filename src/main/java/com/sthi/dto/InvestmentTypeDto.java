package com.sthi.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

public class InvestmentTypeDto {
    private Integer id;
    private String name;
    private Integer duration;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate maturityDate;

    private Integer totalValue;
    private Integer amount;
    private String interestRate;
    private Integer status;

    // ✅ Note: we ignore DB startDate/maturityDate and calculate fresh ones
    public InvestmentTypeDto(Integer id, String name, Integer duration,
                             LocalDateTime dbStartDate, LocalDateTime dbMaturityDate, // present for JPQL compatibility
                             Integer totalValue, Integer amount,
                             String interestRate, Integer status) {
        this.id = id;
        this.name = name;
        this.duration = duration;

        // Always calculate from "today"
        this.startDate = LocalDate.now();
        this.maturityDate = (duration != null) ? this.startDate.plusMonths(duration) : null;

        this.totalValue = totalValue;
        this.amount = amount;
        this.interestRate = interestRate;
        this.status = status;
    }

    // Getters
    public Integer getId() { return id; }
    public String getName() { return name; }
    public Integer getDuration() { return duration; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getMaturityDate() { return maturityDate; }
    public Integer getTotalValue() { return totalValue; }
    public Integer getAmount() { return amount; }
    public String getInterestRate() { return interestRate; }
    public Integer getStatus() { return status; }
}
