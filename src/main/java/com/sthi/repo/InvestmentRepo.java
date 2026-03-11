package com.sthi.repo;

import com.sthi.model.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvestmentRepo extends JpaRepository<Investment, Long> {
	
    // Custom query to fetch all investments (if you want to add sorting/filtering later)
    @Query("SELECT i FROM Investment i")
    List<Investment> fetchAllInvestments();


    Optional<Investment> findById(Long id);
    List<Investment> findByUserIdAndTypeIdOrderByMonthAscCreatedAtAsc(Integer integer, Integer typeId);
    
    boolean existsByUserIdAndTypeId(Integer userId, Integer typeId);


	List<Investment> findByUserIdAndTypeIdAndStatusOrderByNoAsc(Integer userId, Integer typeId, int i);


	List<Investment> findByUserIdAndTypeIdOrderByNoAsc(Integer userId, Integer typeId);
	
    // Calculate total locked amount from active investments
    // Assuming status = 1 means active investments
    // @Query("SELECT COALESCE(SUM(i.amount), 0) FROM Investment i WHERE i.userId = :userId AND i.status = 1")
	
    @Query("SELECT COALESCE(SUM(i.amount), 0) + COALESCE(SUM(i.earned), 0) FROM Investment i WHERE i.userId = :userId AND i.status = 1")
    Integer sumActiveInvestmentAmountByUserId(@Param("userId") Integer userId);

}
