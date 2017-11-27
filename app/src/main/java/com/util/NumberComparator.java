package com.util;

import com.model.ClassRoom;

import java.util.Comparator;

/**
 * Created by zjr on 2017/11/27.
 */

public class NumberComparator implements Comparator<ClassRoom> {
    @Override
    public int compare(ClassRoom classRoom, ClassRoom t1) {
        int num1 = classRoom.getNumber();
        int num2 = t1.getNumber();
        return (num1-num2);
    }
}
