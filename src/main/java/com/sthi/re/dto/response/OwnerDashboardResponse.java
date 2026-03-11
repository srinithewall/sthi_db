package com.sthi.re.dto.response;

public class OwnerDashboardResponse {

    private Long totalProperties;
    private Long activeProperties;
    private Long inactiveProperties;
    private Long totalViews;
    private Long totalLeads;
	public Long getTotalProperties() {
		return totalProperties;
	}
	public void setTotalProperties(Long totalProperties) {
		this.totalProperties = totalProperties;
	}
	public Long getActiveProperties() {
		return activeProperties;
	}
	public void setActiveProperties(Long activeProperties) {
		this.activeProperties = activeProperties;
	}
	public Long getInactiveProperties() {
		return inactiveProperties;
	}
	public void setInactiveProperties(Long inactiveProperties) {
		this.inactiveProperties = inactiveProperties;
	}
	public Long getTotalViews() {
		return totalViews;
	}
	public void setTotalViews(Long totalViews) {
		this.totalViews = totalViews;
	}
	public Long getTotalLeads() {
		return totalLeads;
	}
	public void setTotalLeads(Long totalLeads) {
		this.totalLeads = totalLeads;
	}

    // getters & setters
}
