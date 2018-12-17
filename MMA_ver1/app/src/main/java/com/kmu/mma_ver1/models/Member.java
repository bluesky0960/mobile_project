package com.kmu.mma_ver1.models;

import android.widget.EditText;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

@IgnoreExtraProperties
public class Member {

    public String username;
    public String phone;
    public ArrayList<String> classList;


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

}
