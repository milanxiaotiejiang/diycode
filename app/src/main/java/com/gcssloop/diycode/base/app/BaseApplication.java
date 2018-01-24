package com.gcssloop.diycode.base.app;

import android.app.Application;

import com.gcssloop.diycode.BuildConfig;
import com.gcssloop.diycode.utils.Config;
import com.gcssloop.diycode.utils.CrashHandler;
import com.gcssloop.diycode_sdk.api.Diycode;
import com.seabreeze.log.Print;
import com.seabreeze.log.inner.ConsoleTree;
import com.seabreeze.log.inner.FileTree;
import com.seabreeze.log.inner.LogcatTree;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by zhangyuanyuan on 2017/12/11.
 */

public class BaseApplication extends Application {

    public static final String client_id = "7024a413";
    public static final String client_secret = "8404fa33ae48d3014cfa89deaa674e4cbe6ec894a57dbef4e40d083dbbaa5cf4";


    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

        CrashHandler.getInstance().init(this);

        Diycode.init(this, client_id, client_secret);

        Config.init(this);

        initPrint();
    }

    private void initPrint() {
        if (BuildConfig.DEBUG) {
            Print.getLogConfig().configAllowLog(true)
                    .configShowBorders(false);
            Print.plant(new FileTree(this, "Log"));
            Print.plant(new ConsoleTree());
            Print.plant(new LogcatTree());
        }
    }
}
