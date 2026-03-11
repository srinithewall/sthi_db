package com.sthi.controller;

import com.sthi.model.Privilege;
import com.sthi.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/privileges")
public class PrivilegeController {
    
    @Autowired
    private PrivilegeService privilegeService;
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Privilege>> getUserPrivileges(@PathVariable Integer userId) {
        List<Privilege> privileges = privilegeService.getPrivilegesByUserId(userId);
        return ResponseEntity.ok(privileges);
    }
    
    // Return privilege IDs for user
    @GetMapping("/user/{userId}/ids")
    public ResponseEntity<List<Integer>> getUserPrivilegeIds(@PathVariable Integer userId) {
        List<Integer> privilegeIds = privilegeService.getPrivilegeIdsByUserId(userId);
        return ResponseEntity.ok(privilegeIds);
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Privilege>> getAllPrivileges() {
        List<Privilege> privileges = privilegeService.getAllPrivileges();
        return ResponseEntity.ok(privileges);
    }
    
    @GetMapping("/{privilegeId}")
    public ResponseEntity<Privilege> getPrivilegeById(@PathVariable Integer privilegeId) {
        Privilege privilege = privilegeService.getPrivilegeById(privilegeId);
        if (privilege != null) {
            return ResponseEntity.ok(privilege);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/user/{userId}/add/{privilegeId}")
    public ResponseEntity<String> addPrivilegeToUser(@PathVariable Integer userId, @PathVariable Integer privilegeId) {
        privilegeService.addPrivilegeToUser(userId, privilegeId);
        return ResponseEntity.ok("Privilege added to user successfully");
    }
    
    @DeleteMapping("/user/{userId}/remove/{privilegeId}")
    public ResponseEntity<String> removePrivilegeFromUser(@PathVariable Integer userId, @PathVariable Integer privilegeId) {
        privilegeService.removePrivilegeFromUser(userId, privilegeId);
        return ResponseEntity.ok("Privilege removed from user successfully");
    }
    
    @GetMapping("/user/{userId}/has/{privilegeId}")
    public ResponseEntity<Boolean> userHasPrivilege(@PathVariable Integer userId, @PathVariable Integer privilegeId) {
        boolean hasPrivilege = privilegeService.hasPrivilege(userId, privilegeId);
        return ResponseEntity.ok(hasPrivilege);
    }
}