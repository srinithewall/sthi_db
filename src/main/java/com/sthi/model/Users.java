package com.sthi.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", length = 200)
    private String firstName;

    @Column(name = "last_name", length = 200)
    private String lastName;

    @Column(length = 150)
    private String position;

    @Column(length = 50)
    private String email;

    @Column(length = 100)
    private String password;

    @Column(name = "phone_number", length = 50)
    private String phoneNumber;

    @Column(name = "user_type_id")
    private Integer userTypeId;

    // ✅ Store profile picture URL (R2 storage)
    @Column(name = "profile_pic_url", length = 500)
    private String profilePicUrl;

    @Column(name = "first_time_login")
    private Integer firstTimeLogin;

    @Column(name = "is_active")
    private Integer isActive;

    @Column(name = "astro")
    private Integer astro;

    @Column(name = "shopping")
    private Integer shopping;

    @Column(name = "journel")
    private Integer journel;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    // ========================
    // Getters & Setters
    // ========================

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public Integer getFirstTimeLogin() {
        return firstTimeLogin;
    }

    public void setFirstTimeLogin(Integer firstTimeLogin) {
        this.firstTimeLogin = firstTimeLogin;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getAstro() {
        return astro;
    }

    public void setAstro(Integer astro) {
        this.astro = astro;
    }

    public Integer getShopping() {
        return shopping;
    }

    public void setShopping(Integer shopping) {
        this.shopping = shopping;
    }

    public Integer getJournel() {
        return journel;
    }

    public void setJournel(Integer journel) {
        this.journel = journel;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    // ========================
    // Safe toString (No password)
    // ========================

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userTypeId=" + userTypeId +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}