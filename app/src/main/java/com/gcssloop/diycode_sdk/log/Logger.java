package com.gcssloop.diycode_sdk.log;

import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by zhangyuanyuan on 2017/12/11.
 */

public class Logger {

    private static String DEFAULT_TAG = "GCS-LOG";

    private static Config mConfig;

    private Logger(){

    }

    private static Config init(){
        mConfig = new Config(DEFAULT_TAG);
        return mConfig;
    }

    public static Config init(@NonNull String tag) {
        mConfig = new Config(tag);
        return mConfig;
    }

    public static void d(String message) {
        log(Config.LEVEL_DEBUG, mConfig.getTag(), message);
    }

    public static void i(String message) {
        log(Config.LEVEL_INFO, mConfig.getTag(), message);
    }

    public static void w(String message) {
        log(Config.LEVEL_WARN, mConfig.getTag(), message);
    }

    public static void e(String message) {
        log(Config.LEVEL_ERROR, mConfig.getTag(), message);
    }

    public static void v(String tag, String message) {
        log(Config.LEVEL_VERBOSE, tag, message);
    }

    public static void d(String tag, String message) {
        log(Config.LEVEL_DEBUG, tag, message);
    }

    public static void i(String tag, String message) {
        log(Config.LEVEL_INFO, tag, message);
    }

    public static void w(String tag, String message) {
        log(Config.LEVEL_WARN, tag, message);
    }

    public static void e(String tag, String message) {
        log(Config.LEVEL_ERROR, tag, message);
    }

    private static void log(int level, String tag, String message) {
        if (mConfig.getLevel() == Config.LEVEL_NONE) {
            return;
        }

        if (level < mConfig.getLevel()) {
            return;
        }

        switch (level) {
            case Config.LEVEL_VERBOSE:
                Log.v(tag, message);
                break;
            case Config.LEVEL_DEBUG:
                Log.d(tag, message);
                break;
            case Config.LEVEL_INFO:
                Log.i(tag, message);
                break;
            case Config.LEVEL_WARN:
                Log.w(tag, message);
                break;
            case Config.LEVEL_ERROR:
                Log.e(tag, message);
                break;
        }

    }
}
