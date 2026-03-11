package com.sthi.re.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "re_project_advisors",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"project_id"})
    }
)
public class ProjectAdvisor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_advisor_id")
    private Long projectAdvisorId;

    @Column(name = "project_id", nullable = false)
    private Long projectId;

    @Column(name = "advisor_user_id", nullable = false)
    private Integer advisorUserId;

    /**
     * 1 = Active
     * 0 = Deleted (soft delete)
     */
    @Column(name = "status")
    private Integer status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // ---------- Getters & Setters ----------

    public Long getProjectAdvisorId() {
        return projectAdvisorId;
    }

    public void setProjectAdvisorId(Long projectAdvisorId) {
        this.projectAdvisorId = projectAdvisorId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Integer getAdvisorUserId() {
        return advisorUserId;
    }

    public void setAdvisorUserId(Integer advisorUserId) {
        this.advisorUserId = advisorUserId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
