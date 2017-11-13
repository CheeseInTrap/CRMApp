package com.model;

import cn.bmob.v3.BmobObject;

/**
 * 作者 ： Created by user on 2017/11/12 14:06.
 */

public class ReserveInfo extends BmobObject{

    private int number;
    private int time;
    private int year;
    private int month;
    private int date;
    private String reason;

    private String email;
    private int state;

    public ReserveInfo(){

    }

    public ReserveInfo(int number,int time,int year,int month,int date,String reason,String email,int state){

        this.number = number;
        this.time = time;
        this.year = year;
        this.month = month;
        this.date = date;
        this.reason = reason;
        this.email = email;
        this.state = state;
    }



    public int getNumber() {
        return number;
    }

    public int getTime() {
        return time;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDate() {
        return date;
    }

    public String getReason() {
        return reason;
    }

    public String getEmail() {
        return email;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}

