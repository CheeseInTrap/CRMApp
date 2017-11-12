package com.model;

/**
 * 作者 ： Created by user on 2017/11/12 14:06.
 */

public class ReserveInfo {

    private int id;

    private int number;
    private int time;
    private int year;
    private int month;
    private int date;
    private String reason;

    private String email;
    private int state;

    public ReserveInfo(int id,int number,int time,int year,int month,int date,String reason,String email,int state){
        this.id = id;
        this.number = number;
        this.time = time;
        this.year = year;
        this.month = month;
        this.date = date;
        this.reason = reason;
        this.email = email;
        this.state = state;
    }

    public int getId() {
        return id;
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
}

