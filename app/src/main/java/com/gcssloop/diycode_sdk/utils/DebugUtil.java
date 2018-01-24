package com.gcssloop.diycode_sdk.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;

/**
 * Created by zhangyuanyuan on 2017/12/11.
 */

public class DebugUtil {

    public static boolean isInDebug(Context context){
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        }catch (Exception e){
            return false;
        }
    }

}
