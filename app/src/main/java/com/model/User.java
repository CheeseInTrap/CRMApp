package com.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by user on 2017/11/10.
 */

public class User extends BmobObject{

    private String username;
    private String password;
    private String email;
    private int level;
    private int role;


    public User(String email, String UserName, String Password, int level, int role){
        this.email = email;
        this.username=UserName;
        this.password =Password;
        this.level = level;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }


}
