package com.kmu.mma_ver1.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public String username;
    public String email;


    public User() {
        this.email = null;
        this.username = null;
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;

    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}