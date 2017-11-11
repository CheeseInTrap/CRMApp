package com.model;

/**
 * Created by user on 2017/11/10.
 */

public class User {

    private String UserName;
    private String Password;
    private String EmailAddress;


    public User(String EmailAddress,String UserName,String Password,int Level){
        this.EmailAddress=EmailAddress;
        this.UserName=UserName;
        this.Password=Password;
        this.Level=Level;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }


    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        Level = level;
    }

    private int Level;

}
