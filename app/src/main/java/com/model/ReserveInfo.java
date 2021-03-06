package com.model;

import cn.bmob.v3.BmobObject;

/**
 * 作者 ： Created by user on 2017/11/12 14:06.
 */

public class ReserveInfo extends BmobObject{

    private int number;
    private int build;
    private int time;
    private int date;
    private String reason;

    private String email;
    private int state;

    public ReserveInfo(){

    }

    public ReserveInfo(int number,int build,int time,int date,String reason,String email,int state){

        this.number = number;
        this.build = build;
        this.time = time;
        this.date = date;
        this.reason = reason;
        this.email = email;
        this.state = state;
    }



    public int getNumber() {
        return number;
    }

    public int getBuild() {
        return build;
    }

    public int getTime() {
        return time;
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

