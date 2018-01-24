package com.gcssloop.diycode.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.seabreeze.log.Print;

import java.util.List;

/**
 * Created by zhangyuanyuan on 2017/12/13.
 */

public class AppUtil {

    public static String getPackageName(Context context) {
        return context.getPackageName();
    }


    public static String getVersionName(Context context) {
        String versionName = null;
        String pkName = context.getPackageName();
        try {
            versionName = context.getPackageManager().getPackageInfo(pkName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }


    public static int getVersionCode(Context context) {
        int versionCode = -1;
        String pkName = context.getPackageName();
        try {
            versionCode = context.getPackageManager().getPackageInfo(pkName, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }


    public static boolean isAvailable(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();//获取packagemanager
        List<PackageInfo> pInfo = packageManager.getInstalledPackages(0);//获取所有已安装程序的包信息
        //从pinfo中将包名字逐一取出，压入pName list中
        if (pInfo != null) {
            for (int i = 0; i < pInfo.size(); i++) {
                String pn = pInfo.get(i).packageName;
                if (pn.equals(packageName))
                    return true;
            }
        }
        return false;
    }


}
