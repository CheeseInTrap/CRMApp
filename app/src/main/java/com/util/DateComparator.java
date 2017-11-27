package com.util;

import com.model.ClassRoom;

import java.util.Comparator;

/**
 * Created by zjr on 2017/11/27.
 */

public class DateComparator implements Comparator<ClassRoom> {
    @Override
    public int compare(ClassRoom classRoom, ClassRoom t1) {
        int date1 = classRoom.getDate();
        int date2 = t1.getDate();

        return (date1-date2);
    }
}
