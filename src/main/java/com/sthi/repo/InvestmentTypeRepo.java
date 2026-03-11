package com.sthi.repo;



import com.sthi.dto.InvestmentTypeDto;
import com.sthi.model.InvestmentType;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentTypeRepo extends JpaRepository<InvestmentType, Long> {

	
	Optional<InvestmentType> findById(Integer typeId);
	
	@Query("SELECT i FROM Investment i")
    List<InvestmentType> fetchAllInvestments();
	
	@Query("SELECT new com.sthi.dto.InvestmentTypeDto(" +
		       "i.id, i.name, i.duration, i.startDate, i.maturityDate, " +
		       "i.totalValue, i.amount, i.interestRate, i.status) " +
		       "FROM InvestmentType i")
		List<InvestmentTypeDto> findAllInvestmentTypes();

}
