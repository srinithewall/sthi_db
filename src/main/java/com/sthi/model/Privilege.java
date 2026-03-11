package com.sthi.model;


import jakarta.persistence.*;

@Entity
@Table(name = "privileges")
public class Privilege {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "privilege_name", nullable = false, length = 100)
    private String privilegeName;
    
    @Column(name = "description", length = 255)
    private String description;
    
    @Column(name = "access")
    private Boolean access;
    
    
    
    // Constructors
    public Privilege() {}
    
    public Privilege(String privilegeName, String description) {
        this.privilegeName = privilegeName;
        this.description = description;
        this.access=access; // 0-private, 1-public
    }
    
    // Getters and Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getPrivilegeName() {
        return privilegeName;
    }
    
    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
    public Boolean getAccess() {
		return access;
	}

	public void setAccess(Boolean access) {
		this.access = access;
	}

	@Override
    public String toString() {
        return "Privilege{" +
                "id=" + id +
                ", privilegeName='" + privilegeName + '\'' +
                ", description='" + description + '\'' +
                 ", access='" + access + '\'' +
                '}';
    }
}