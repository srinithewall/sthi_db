package com.sthi.repo;




import com.sthi.model.Users;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByEmail(String email);

    Boolean existsByEmail(String email);

    List<UserSummary> findAllProjectedBy();

    interface UserSummary {
        int getId();
        String getFirstName();
        String getLastName();
        String getEmail();
        String getPosition();
        String getPhoneNumber();
        Integer getUserTypeId();
        Integer getIsActive();
        String getProfilePicUrl(); // ✅ Added
    }

}