package com.sthi.re.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "re_project_tags",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"project_id", "tag_id"})
    }
)
public class ProjectTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_tag_id")
    private Long projectTagId;

    @Column(name = "project_id", nullable = false)
    private Long projectId;

    @Column(name = "tag_id", nullable = false)
    private Long tagId;

    /**
     * 1 = Active
     * 0 = Deleted
     */
    @Column(name = "status")
    private Integer status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // ---------- Getters & Setters ----------

    public Long getProjectTagId() {
        return projectTagId;
    }

    public void setProjectTagId(Long projectTagId) {
        this.projectTagId = projectTagId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
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
