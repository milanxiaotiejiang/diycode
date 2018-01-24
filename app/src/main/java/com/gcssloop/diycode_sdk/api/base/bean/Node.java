package com.gcssloop.diycode_sdk.api.base.bean;

import java.io.Serializable;

/**
 * Created by zhangyuanyuan on 2017/12/13.
 */

public class Node implements Serializable {

    /**
     * id : 1
     * name : Android
     */

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
