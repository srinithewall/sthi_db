package com.sthi.repo;

import com.sthi.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {
    
    Optional<Privilege> findByPrivilegeName(String privilegeName);
    
    @Query("SELECT p FROM Privilege p WHERE p.id IN :privilegeIds")
    List<Privilege> findPrivilegesByIds(@Param("privilegeIds") List<Integer> privilegeIds);
}