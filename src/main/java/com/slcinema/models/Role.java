package com.slcinema.models;

public class Role {
    private String role;
    private String starID;
    private String imageUrl;

    public Role(String role, String starID) {
        this.role = role;
        this.starID = starID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStarID() {
        return starID;
    }

    public void setStarID(String starID) {
        this.starID = starID;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
