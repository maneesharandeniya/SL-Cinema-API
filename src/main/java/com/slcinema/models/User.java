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

    public User(String id, String username, String email, String password, ArrayList<String> wishlist, ArrayList<String> ratedList, ArrayList<String> reviewedList) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.wishlist = wishlist;
        this.ratedList = ratedList;
        this.reviewedList = reviewedList;
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
