package com.example.bloodBank.dtos;



public class BloodReportDTO {
    private String bloodGroup;
    private Long approvedRequests;
    private Long rejectedRequests;
    private Long pendingRequests;
    private Long totalRequests;

    public BloodReportDTO(String bloodGroup, Long approvedRequests, Long rejectedRequests, Long pendingRequests, Long totalRequests) {
        this.bloodGroup = bloodGroup;
        this.approvedRequests = approvedRequests;
        this.rejectedRequests = rejectedRequests;
        this.pendingRequests = pendingRequests;
        this.totalRequests = totalRequests;
    }
    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Long getApprovedRequests() {
        return approvedRequests;
    }

    public void setApprovedRequests(Long approvedRequests) {
        this.approvedRequests = approvedRequests;
    }

    public Long getRejectedRequests() {
        return rejectedRequests;
    }

    public void setRejectedRequests(Long rejectedRequests) {
        this.rejectedRequests = rejectedRequests;
    }

    public Long getPendingRequests() {
        return pendingRequests;
    }

    public void setPendingRequests(Long pendingRequests) {
        this.pendingRequests = pendingRequests;
    }

    public Long getTotalRequests() {
        return totalRequests;
    }

    public void setTotalRequests(Long totalRequests) {
        this.totalRequests = totalRequests;
    }
}
