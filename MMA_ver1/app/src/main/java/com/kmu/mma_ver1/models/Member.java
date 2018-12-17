package com.kmu.mma_ver1.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Member {

    public String username;
    public String email;
    public String userId;

    public Member() {
    }

    public Member(String userId,String username, String email) {
        this.username = username;
        this.email = email;
        this.userId = userId;

    }
}
