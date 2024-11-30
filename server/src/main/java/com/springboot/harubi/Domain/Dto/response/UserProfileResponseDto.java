package com.springboot.harubi.Domain.Dto.response;

public class UserProfileResponseDto {
    private String userId;
    private String status;
    private String icon;
    private String bio;
    private String affiliation;

    public UserProfileResponseDto(String userId, String status, String icon, String bio, String affiliation) {
        this.userId = userId;
        this.status = status;
        this.icon = icon;
        this.bio = bio;
        this.affiliation = affiliation;
    }

    // Getter methods
    public String getUserId() {
        return userId;
    }

    public String getStatus() {
        return status;
    }

    public String getIcon() {
        return icon;
    }

    public String getBio() {
        return bio;
    }

    public String getAffiliation() {
        return affiliation;
    }
}
