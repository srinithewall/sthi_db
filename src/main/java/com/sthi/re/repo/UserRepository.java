package com.sthi.re.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sthi.model.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

    // Get active user by ID
    Optional<Users> findByIdAndIsActive(Integer advisorId, Integer isActive);

    // Get all active advisors (user_type_id = 2)
    List<Users> findByUserTypeIdAndIsActive(Long userTypeId, Integer isActive);

    // Get any one active advisor (default)
    Optional<Users> findFirstByUserTypeIdAndIsActiveOrderByIdAsc(
            Long userTypeId,
            Integer isActive
    );

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByPhoneNumber(String phoneNumber);
    
    Optional<Users> findByIdAndUserTypeIdAndIsActive(
            Integer id,
            Long userTypeId,
            Integer isActive
    );
}
