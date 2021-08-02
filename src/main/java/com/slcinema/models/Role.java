package com.slcinema.models;

public class Role {
    private String role;
    private String star;
    private String imageUrl;

    public Role(String role, String star, String imageUrl) {
        this.role = role;
        this.star = star;
        this.imageUrl = imageUrl;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
