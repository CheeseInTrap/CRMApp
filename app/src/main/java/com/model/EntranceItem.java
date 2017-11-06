package com.model;

import com.section.Section;

/**
 * ckb on 2017/5/22.
 */

public class EntranceItem implements Section {

    private String name;

    public EntranceItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getSectionType() {
        return ENTRANCE;
    }

}
