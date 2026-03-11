package com.sthi.service;

import com.sthi.model.Users;
import com.sthi.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersRepository userRepository;

    public List<UsersRepository.UserSummary> getAllUsers() {
        List<UsersRepository.UserSummary> users = userRepository.findAllProjectedBy();
        System.out.println("Retrieved " + users.size() + " user summaries");
        return users;
    }

    @Override
    public Users getUserById(int id) {
        Optional<Users> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        throw new RuntimeException("User not found with id: " + id);
    }

    @Override
    public Users createUser(Users user) {
        if (user.getEmail() != null && userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email ID is already registered.");
        }

        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        return userRepository.save(user);
    }

    @Override
    public Users updateUser(int id, Users userDetails) {
        Users existingUser = getUserById(id);

        if (userDetails.getFirstName() != null)
            existingUser.setFirstName(userDetails.getFirstName());
        if (userDetails.getLastName() != null)
            existingUser.setLastName(userDetails.getLastName());
        if (userDetails.getPosition() != null)
            existingUser.setPosition(userDetails.getPosition());
        if (userDetails.getEmail() != null)
            existingUser.setEmail(userDetails.getEmail());
        if (userDetails.getPhoneNumber() != null)
            existingUser.setPhoneNumber(userDetails.getPhoneNumber());
        if (userDetails.getUserTypeId() != null)
            existingUser.setUserTypeId(userDetails.getUserTypeId());
        if (userDetails.getIsActive() != null)
            existingUser.setIsActive(userDetails.getIsActive());
        if (userDetails.getProfilePicUrl() != null)
            existingUser.setProfilePicUrl(userDetails.getProfilePicUrl());

        // Ensure updatedAt is modified
        existingUser.setUpdatedAt(new Date());

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(int id) {
        Users existingUser = getUserById(id);
        userRepository.delete(existingUser);
    }
}