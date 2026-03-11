package com.sthi.re.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "re_project_videos")
public class ProjectVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_video_id")
    private Long projectVideoId;

    @Column(name = "project_id", nullable = false)
    private Long projectId;

    @Column(name = "video_url", nullable = false)
    private String videoUrl;

    /**
     * YOUTUBE
     * VIMEO
     * OTHER
     */
    @Column(name = "video_type")
    private String videoType;

    @Column(name = "sort_order")
    private Integer sortOrder;

    /**
     * 1 = Active
     * 0 = Deleted
     */
    @Column(name = "status")
    private Integer status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // ---------- Getters & Setters ----------

    public Long getProjectVideoId() {
        return projectVideoId;
    }

    public void setProjectVideoId(Long projectVideoId) {
        this.projectVideoId = projectVideoId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
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
