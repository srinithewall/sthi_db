package com.sthi.controller;

import com.sthi.dto.InvestmentTypeDto;
import com.sthi.model.Investment;
import com.sthi.service.InvestmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/investments")
public class InvestmentController {
	private static final Logger logger = LoggerFactory.getLogger(InvestmentController.class);

	private final InvestmentService investmentService;

	public InvestmentController(InvestmentService investmentService) {
		this.investmentService = investmentService;
	}

	@PatchMapping("/{investmentId}/status")
	public ResponseEntity<Investment> updateInvestmentStatus(@PathVariable Long investmentId,
			@RequestBody Map<String, Integer> requestBody) {
		try {
			logger.info("Received PATCH request for investment ID: {}", investmentId);
			logger.debug("Request body: {}", requestBody);

			// Validate request
			if (requestBody.get("status") == null) {
				logger.warn("Status field is missing in request");
				throw new IllegalArgumentException("Status field is required");
			}

			Integer status = requestBody.get("status");
			if (status != 0 && status != 1) {
				logger.warn("Invalid status value: {}", status);
				throw new IllegalArgumentException("Status must be 0 (Unpaid) or 1 (Paid)");
			}

			Investment updatedInvestment = investmentService.updateInvestmentStatus(investmentId, status);
			logger.info("Successfully updated investment ID: {}", investmentId);

			return ResponseEntity.ok(updatedInvestment);

		} catch (IllegalArgumentException ex) {
			logger.error("Client error: {}", ex.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
		} catch (IllegalStateException ex) {
			logger.error("Business rule violation: {}", ex.getMessage());
			throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage(), ex);
		} catch (Exception ex) {
			logger.error("Internal server error: {}", ex.getMessage(), ex);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Error updating investment status: " + ex.getMessage(), ex);
		}
	}

	@PostMapping("/assign")
	public ResponseEntity<Map<String, String>> assignInvestment(@RequestBody Map<String, Integer> request) {
		try {
			Integer investmentTypeId = request.get("investmentTypeId");
			Integer userId = request.get("userId");

			investmentService.assignInvestment(investmentTypeId, userId);

			return ResponseEntity.ok(Map.of("message", "Investment assigned successfully"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
		}
	}
	
	 @GetMapping("/getTypes")
	    public List<InvestmentTypeDto> getInvestments() {
	        return investmentService.getAllInvestments();
	    }
	 

	
}