package com.gcssloop.diycode_sdk.api.base.bean;

import java.io.Serializable;

/**
 * Created by zhangyuanyuan on 2017/12/12.
 */

public class Abilities implements Serializable{

    private boolean update;

    private boolean destroy;

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public boolean getUpdate() {
        return this.update;
    }

    public void setDestroy(boolean destroy) {
        this.destroy = destroy;
    }

    public boolean getDestroy() {
        return this.destroy;
    }

}
