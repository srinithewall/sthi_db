package com.sthi.re.dto.request;

public class PropertyVideoRequest {

    // Existing video ID (used during update)
    private Long propertyVideoId;

    // Video URL (YouTube / Vimeo / Instagram / direct link)
    private String videoUrl;

    // Video type (YOUTUBE, VIMEO, INSTAGRAM, DIRECT)
    private String videoType;

    // Display order
    private Integer sortOrder;

    // Used during update to remove video
    private Boolean remove;

    // =========================
    // GETTERS & SETTERS
    // =========================

    public Long getPropertyVideoId() {
        return propertyVideoId;
    }

    public void setPropertyVideoId(Long propertyVideoId) {
        this.propertyVideoId = propertyVideoId;
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

    public Boolean getRemove() {
        return remove;
    }

    public void setRemove(Boolean remove) {
        this.remove = remove;
    }
}