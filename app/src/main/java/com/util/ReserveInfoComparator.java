package com.util;

import com.model.ReserveInfo;

import java.util.Comparator;

/**
 * Created by zjr on 2017/12/5.
 */

public class ReserveInfoComparator implements Comparator<ReserveInfo> {

    @Override
    public int compare(ReserveInfo reserveInfo, ReserveInfo t1) {
        int state1= reserveInfo.getState();
        int state2 = t1.getState();
        return (state1-state2);
    }
}
