package com.kmu.mma_ver1.models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

@IgnoreExtraProperties
public class Member{

    private String username;
    private String phone;

    private ArrayList<String> classList;

    public Member() {
    }

    public Member(String username, String phone, ArrayList<String> classList) {
        this.username = username;
        this.phone = phone;
        this.classList = classList;
    }
    public String getPhone(){
        return phone;
    }
    public String getUsername(){ return username; }

    public ArrayList getClassList(){
        return classList;
    }
}
