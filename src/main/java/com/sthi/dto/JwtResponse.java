package com.sthi.dto;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String email;
    private String firstName;
    private String lastName;
    private String userName;
    private String phoneNumber;
    private Integer userId;
    private Integer usertypeid;
    private List<Integer> privileges;
    
    public JwtResponse(String token, String email, String firstName, String lastName, String userName, String phoneNumber, Integer userId, Integer usertypeid, List<Integer> privilegeIds ){
        this.token = token;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.usertypeid = usertypeid;
        this.privileges = privilegeIds;
        
    }
    
    // Getters and Setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    
    public Integer getusertypeid() { return usertypeid; }
    public void setusertypeid(Integer usertypeid) { this.usertypeid = usertypeid; }
    
    public List<Integer> getPrivileges() { return privileges; }
    public void setPrivileges(List<Integer> privileges) { this.privileges = privileges; }
}