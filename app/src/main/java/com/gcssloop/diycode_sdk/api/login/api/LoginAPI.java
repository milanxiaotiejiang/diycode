package com.gcssloop.diycode_sdk.api.login.api;

import android.support.annotation.NonNull;

import com.gcssloop.diycode_sdk.api.login.bean.Token;

/**
 * Created by zhangyuanyuan on 2017/12/11.
 */

public interface LoginAPI {

    String login(@NonNull String user_name, @NonNull String password);

    void logout();

    boolean isLogin();

    String refreshToken();

    Token getCacheToken();

    String updateDevices();

    String deleteDevices();

}
