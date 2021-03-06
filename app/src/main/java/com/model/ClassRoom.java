
package com.model;

import cn.bmob.v3.BmobObject;

/**
 * 作者 ： Created by zjr on 2017/11/10 16:17.
 */

public class ClassRoom extends BmobObject {

    private int number;
    private int building;
    private int size;
    private int floor;
    private int state12;
    private int state34;
    private int state56;
    private int state78;
    private int state910;
    private int date;

    public static final int UNOCCUPIED = 0;
    public static final int OCCUPIED = 1;

    public ClassRoom() {

    }


    public ClassRoom(int number, int building, int size, int floor, int state12, int state34, int state56, int state78, int state910, int date) {
        this.number = number;
        this.size = size;
        this.floor = floor;
        this.state12 = state12;
        this.state34 = state34;
        this.state56 = state56;
        this.state78 = state78;
        this.state910 = state910;
        this.building = building;
        this.date = date;
    }

    public ClassRoom(int number, int building, int size, int floor, int state12, int state34, int state56, int state78, int state910) {
        this.number = number;
        this.size = size;
        this.floor = floor;
        this.state12 = state12;
        this.state34 = state34;
        this.state56 = state56;
        this.state78 = state78;
        this.state910 = state910;
        this.building = building;
    }

    public int getNumber() {
        return number;
    }

    public int getBuilding() {
        return building;
    }

    public int getSize() {
        return size;
    }

    public int getFloor() {
        return floor;
    }

    public int getState12() {
        return state12;
    }

    public int getState34() {
        return state34;
    }

    public int getState56() {
        return state56;
    }

    public int getState78() {
        return state78;
    }

    public int getState910() {
        return state910;
    }

    public int getDate() {
        return date;
    }

    public void setBuilding(int building) {
        this.building = building;
    }

    public void setState12(int state12) {
        this.state12 = state12;
    }

    public void setState34(int state34) {
        this.state34 = state34;
    }

    public void setState56(int state56) {
        this.state56 = state56;
    }

    public void setState78(int state78) {
        this.state78 = state78;
    }

    public void setState910(int state910) {
        this.state910 = state910;
    }
}
