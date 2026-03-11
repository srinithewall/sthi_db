package com.sthi.repo;

import com.sthi.model.UserPrivilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserPrivilegeRepository extends JpaRepository<UserPrivilege, Integer> {
    
    List<UserPrivilege> findByUserId(Integer userId);
    
    // Much cleaner query with entity relationships
    @Query("SELECT up.privilege.id FROM UserPrivilege up WHERE up.privilege.access = true AND up.userId = :userId")
    List<Integer> findPrivilegeIdsByUserId(@Param("userId") Integer userId);
    
    // Alternative using derived query method
    List<UserPrivilege> findByUserIdAndPrivilegeAccessTrue(Integer userId);
    
    boolean existsByUserIdAndPrivilegeId(Integer userId, Integer privilegeId);
    
    void deleteByUserIdAndPrivilegeId(Integer userId, Integer privilegeId);
}