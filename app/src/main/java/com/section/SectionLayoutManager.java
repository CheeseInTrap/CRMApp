package com.section;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

/**
 * created by zjr on 2017/5/21.
 */

public class SectionLayoutManager extends GridLayoutManager {

    public SectionLayoutManager(Context context) {
        super(context, 6, VERTICAL, false);
    }

}
