package com.gcssloop.diycode.fragment.bean;

import java.io.Serializable;

/**
 * Created by zhangyuanyuan on 2017/12/13.
 */

public class SitesItem implements Serializable {
    private String name;

    public SitesItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
