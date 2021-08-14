package com.slcinema.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Document("User")
public class User {
    @Id
    private String id;

    @NotNull(message = "username cannot be Null")
    private String username;

    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "password cannot be Null")
    private String password;

    private ArrayList<String> wishlist = new ArrayList<>();

    private ArrayList<String> ratedList = new ArrayList<>();

    private ArrayList<String> reviewedList = new ArrayList<>();

    private AuthProvider provider;

    private String verificationCode;

    private boolean enabled;

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public AuthProvider getProvider() {
        return provider;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getWishlist() {
        return wishlist;
    }

    public void setWishlist(ArrayList<String> wishlist) {
        this.wishlist = wishlist;
    }

    public ArrayList<String> getRatedList() {
        return ratedList;
    }

    public void setRatedList(ArrayList<String> ratedList) {
        this.ratedList = ratedList;
    }

    public ArrayList<String> getReviewedList() {
        return reviewedList;
    }

    public void setReviewedList(ArrayList<String> reviewedList) {
        this.reviewedList = reviewedList;
    }
}
