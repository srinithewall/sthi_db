package com.sthi.service;

import com.sthi.model.Privilege;
import com.sthi.model.UserPrivilege;
import com.sthi.repo.PrivilegeRepository;
import com.sthi.repo.UserPrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PrivilegeService {
    
    @Autowired
    private PrivilegeRepository privilegeRepository;
    
    @Autowired
    private UserPrivilegeRepository userPrivilegeRepository;
    
    // Get all privileges for a user
    public List<Privilege> getPrivilegesByUserId(Integer userId) {
        List<Integer> privilegeIds = userPrivilegeRepository.findPrivilegeIdsByUserId(userId);
        return privilegeRepository.findPrivilegesByIds(privilegeIds);
    }
    
    // Get privilege IDs for a user (NEW METHOD)
    public List<Integer> getPrivilegeIdsByUserId(Integer userId) {
        return userPrivilegeRepository.findPrivilegeIdsByUserId(userId);
    }
    
    // Get privilege names for a user (keep this if needed elsewhere)
    public List<String> getPrivilegeNamesByUserId(Integer userId) {
        List<Privilege> privileges = getPrivilegesByUserId(userId);
        return privileges.stream()
                .map(Privilege::getPrivilegeName)
                .collect(java.util.stream.Collectors.toList());
    }
    
    // Add privilege to user
    public void addPrivilegeToUser(Integer userId, Integer privilegeId) {
        if (!userPrivilegeRepository.existsByUserIdAndPrivilegeId(userId, privilegeId)) {
            UserPrivilege userPrivilege = new UserPrivilege(userId, privilegeId);
            userPrivilegeRepository.save(userPrivilege);
        }
    }
    
    // Remove privilege from user
    public void removePrivilegeFromUser(Integer userId, Integer privilegeId) {
        userPrivilegeRepository.deleteByUserIdAndPrivilegeId(userId, privilegeId);
    }
    
    // Check if user has specific privilege by ID
    public boolean hasPrivilege(Integer userId, Integer privilegeId) {
        List<Integer> userPrivilegeIds = getPrivilegeIdsByUserId(userId);
        return userPrivilegeIds.contains(privilegeId);
    }
    
    // Check if user has specific privilege by name
    public boolean hasPrivilegeByName(Integer userId, String privilegeName) {
        List<String> userPrivileges = getPrivilegeNamesByUserId(userId);
        return userPrivileges.contains(privilegeName);
    }
    
    // Get all available privileges
    public List<Privilege> getAllPrivileges() {
        return privilegeRepository.findAll();
    }
    
    // Create a new privilege
    public Privilege createPrivilege(String privilegeName, String description) {
        Privilege privilege = new Privilege(privilegeName, description);
        return privilegeRepository.save(privilege);
    }
    
    // Get privilege by ID
    public Privilege getPrivilegeById(Integer privilegeId) {
        return privilegeRepository.findById(privilegeId).orElse(null);
    }
}