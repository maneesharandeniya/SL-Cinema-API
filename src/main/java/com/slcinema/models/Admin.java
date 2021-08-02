package com.slcinema.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Document("Admin")
public class Admin {

    @Id
    private String adminID;

    @NotNull(message = "User Name is mandatory")
    private String username;

    //@NotNull(message = "password is mandatory")
    //@ValidPassword
    private String password;

    @Email(message = "Email should be valid")
    private String email;

    private String role;

    public String getAdminID() {
        return adminID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
