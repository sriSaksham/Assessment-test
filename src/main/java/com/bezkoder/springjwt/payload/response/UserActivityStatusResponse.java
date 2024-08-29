package com.bezkoder.springjwt.payload.response;

public class UserActivityStatusResponse {
    private Long userId;
    private String activityStatus;

    public UserActivityStatusResponse(Long userId, String activityStatus) {
        this.userId = userId;
        this.activityStatus = activityStatus;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus;
    }
}