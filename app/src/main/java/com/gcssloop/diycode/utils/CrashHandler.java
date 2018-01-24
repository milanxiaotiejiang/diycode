package com.gcssloop.diycode.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Process;

import com.seabreeze.log.Print;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhangyuanyuan on 2017/12/11.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static final boolean DEBUG = true;

    private static final String PATH = Environment.getExternalStorageDirectory().getPath() + "/ryg_test/log/";

    private static final String FILE_NAME = "crash";
    //log文件的后缀名
    private static final String FILE_NAME_SUFFIX = ".trace";

    private static CrashHandler sInstance = new CrashHandler();

    //系统默认的异常处理（默认情况下，系统会终止当前的异常程序）
    private Thread.UncaughtExceptionHandler mDefaultCrashHandler;

    private Context mContext;

    public CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return sInstance;
    }

    public void init(Context context) {
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        mContext = context.getApplicationContext();
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        dumpExceptionToSDCard(throwable);

        uploadExceptionToServer();

        throwable.printStackTrace();

        if (mDefaultCrashHandler != null) {
            mDefaultCrashHandler.uncaughtException(thread, throwable);
        } else {
            Process.killProcess(Process.myPid());
        }
    }

    private void dumpExceptionToSDCard(Throwable throwable) {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            if (DEBUG) {
                Print.e("sdcard unmounted, skip dump exception");
                return;
            }
        }
        File dir = new File(PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        long current = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(current));
        File file = new File(PATH + FILE_NAME + time + FILE_NAME_SUFFIX);

        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            pw.println(time);
            dumpPhoneInfo(pw);
            pw.println();
            throwable.printStackTrace(pw);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
            Print.e("dump crash info failed");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Print.e("dump model info failed");
        }

    }

    private void dumpPhoneInfo(PrintWriter pw) throws PackageManager.NameNotFoundException {
        PackageManager pm = mContext.getPackageManager();
        PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
        pw.print("App version : ");
        pw.print(pi.versionName);
        pw.print("_");
        pw.println(pi.versionCode);

        pw.print("OS Version : ");
        pw.print(Build.VERSION.RELEASE);
        pw.print("_");
        pw.println(Build.VERSION.SDK_INT);

        pw.print("Ventor : ");
        pw.println(Build.MANUFACTURER);

        pw.print("Model : ");
        pw.println(Build.MODEL);

        pw.print("CPU ABI : ");
        pw.println(Build.CPU_ABI);
    }

    private void uploadExceptionToServer() {

    }


}
