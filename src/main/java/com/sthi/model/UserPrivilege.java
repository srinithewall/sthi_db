package com.sthi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_privileges")
public class UserPrivilege {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    
    @Column(name = "privilege_id", nullable = false)
    private Integer privilegeId;
    
    // Many-to-one relationship with Users (optional)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private Users user;
    
    // Many-to-one relationship with Privilege (optional)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "privilege_id", insertable = false, updatable = false)
    private Privilege privilege;
    
    // Constructors
    public UserPrivilege() {}
    
    public UserPrivilege(Integer userId, Integer privilegeId) {
        this.userId = userId;
        this.privilegeId = privilegeId;
    }
    
    // Getters and Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getUserId() {
        return userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public Integer getPrivilegeId() {
        return privilegeId;
    }
    
    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
    }
    
    public Users getUser() {
        return user;
    }
    
    public void setUser(Users user) {
        this.user = user;
    }
    
    public Privilege getPrivilege() {
        return privilege;
    }
    
    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }
    
    @Override
    public String toString() {
        return "UserPrivilege{" +
                "id=" + id +
                ", userId=" + userId +
                ", privilegeId=" + privilegeId +
                '}';
    }
}
