package com.sthi.controller;

import com.sthi.service.UserService;
import com.sthi.model.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.sthi.repo.UsersRepository;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    // Existing getusers endpoint
    @GetMapping("/getusers")
    public List<UsersRepository.UserSummary> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get User by ID
    @GetMapping("/users/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable int id) {
        Users user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // Create a new User
    @PostMapping("/users")
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        Users createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    // Update User
    @PutMapping("/users/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable int id, @RequestBody Users userDetails) {
        Users updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    // Delete User
    @DeleteMapping("/users/{id}")
    public ResponseEntity<java.util.Map<String, String>> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);

        java.util.Map<String, String> response = new java.util.HashMap<>();
        response.put("message", "User deleted successfully");

        return ResponseEntity.ok(response);
    }
}
