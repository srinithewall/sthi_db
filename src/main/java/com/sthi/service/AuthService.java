package com.sthi.service;

import com.sthi.model.Users;
import com.sthi.repo.UsersRepository;
import com.sthi.security.JwtUtils;
import com.sthi.dto.JwtResponse;
import com.sthi.dto.LoginRequest;
import com.sthi.dto.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class AuthService {
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    UsersRepository usersRepository;
    
    @Autowired
    PasswordEncoder encoder;
    
    @Autowired
    JwtUtils jwtUtils;
    
    @Autowired
    PrivilegeService privilegeService;
    
    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication.getName());
        
        // Get user details
        Users user = usersRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        // Get user privilege IDs (instead of names)
        List<Integer> privilegeIds = privilegeService.getPrivilegeIdsByUserId(user.getId());
        
        String userName = (user.getFirstName() != null ? user.getFirstName() : "") + 
                          (user.getLastName() != null ? " " + user.getLastName() : "");
        
        return new JwtResponse(jwt, user.getEmail(), user.getFirstName(), 
                              user.getLastName(), userName.trim(), user.getPhoneNumber(),
                              user.getId(), user.getUserTypeId() , privilegeIds);
        
        
    }
    
    public Users registerUser(RegisterRequest registerRequest) {
        if (usersRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Error: Email is already in use!");
        }
        
        // Create new user
        Users user = new Users();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(encoder.encode(registerRequest.getPassword()));
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setPosition(registerRequest.getPosition());
        
        // Set default values
        user.setUserTypeId(1);
        user.setFirstTimeLogin(1);
        user.setIsActive(1);
        user.setAstro(0);
        user.setShopping(0);
        user.setJournel(0);
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        
        return usersRepository.save(user);
    }
}