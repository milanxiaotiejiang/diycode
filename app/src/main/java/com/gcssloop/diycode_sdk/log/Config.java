package com.gcssloop.diycode_sdk.log;

import android.support.annotation.NonNull;

/**
 * Created by zhangyuanyuan on 2017/12/11.
 */

public class Config {

    public static final int LEVEL_NONE = 0;
    public static final int LEVEL_FULL = 1;

    public static final int LEVEL_VERBOSE = 2;
    public static final int LEVEL_DEBUG = 3;
    public static final int LEVEL_INFO = 4;
    public static final int LEVEL_WARN = 5;
    public static final int LEVEL_ERROR = 6;
    public static final int LEVEL_ASSERT = 7;

    private String tag;
    private int level;

    public Config(String tag) {
        this.tag = tag;
        level = LEVEL_FULL;
    }

    public Config setLevel(@NonNull int level){
        this.level = level;
        return this;
    }

    public int getLevel() {
        return level;
    }

    public String getTag() {
        return tag;
    }

}
