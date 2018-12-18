package com.kmu.mma_ver1.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class LoginUser {

    private String Loginname;
    private String ID;
    private String Password;
    private String key;


    public LoginUser() {
    }

    public LoginUser(String Loginname, String ID, String password, String key) {
        this.Loginname = Loginname;
        this.ID = ID;
        this.Password = password;
        this.key = key;

    }
    public String getLoginname(){
        return Loginname;
    }
    public String getPassword() {
        return Password;
    }
    public String getID(){
        return ID;
    }
    public String getUIDKey(){
        return key;
    }
    public void setLoginname(String name){
        this.Loginname = name;
    }
    public void setUIDkey(String key){
        this.key = key;
    }
    public void setID(String ID){
        this.ID = ID;
    }
    public void setPassword(String password){
        this.Password = password;
    }
}
