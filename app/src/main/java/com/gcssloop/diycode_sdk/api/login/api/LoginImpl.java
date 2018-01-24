package com.gcssloop.diycode_sdk.api.login.api;

import android.content.Context;
import android.support.annotation.NonNull;

import com.gcssloop.diycode_sdk.api.base.bean.OAuth;
import com.gcssloop.diycode_sdk.api.base.bean.State;
import com.gcssloop.diycode_sdk.api.base.callback.BaseCallback;
import com.gcssloop.diycode_sdk.api.base.callback.TokenCallback;
import com.gcssloop.diycode_sdk.api.base.impl.BaseImpl;
import com.gcssloop.diycode_sdk.api.login.bean.Token;
import com.gcssloop.diycode_sdk.api.login.event.DeleteDevicesEvent;
import com.gcssloop.diycode_sdk.api.login.event.LoginEvent;
import com.gcssloop.diycode_sdk.api.login.event.LogoutEvent;
import com.gcssloop.diycode_sdk.api.login.event.RefreshTokenEvent;
import com.gcssloop.diycode_sdk.api.login.event.UpdateDevicesEvent;
import com.gcssloop.diycode_sdk.utils.UUIDGenerator;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;

/**
 * Created by zhangyuanyuan on 2017/12/11.
 */

public class LoginImpl extends BaseImpl<LoginService> implements LoginAPI {

    public LoginImpl(@NonNull Context context) {
        super(context);
    }

    @Override
    public String login(@NonNull String user_name, @NonNull String password) {
        final String uuid = UUIDGenerator.getUUID();
        mService.getToken(OAuth.client_id, OAuth.client_secret, OAuth.GRANT_TYPE_LOGIN, user_name, password)
                .enqueue(new TokenCallback(mCacheUtil, new LoginEvent(uuid)));
        return uuid;
    }

    @Override
    public void logout() {
        String uuid = UUIDGenerator.getUUID();
        mCacheUtil.clearToken();
        EventBus.getDefault().post(new LogoutEvent(uuid, 0, "用户登出"));
    }

    @Override
    public boolean isLogin() {
        return !(mCacheUtil.getToken() == null);
    }

    @Override
    public String refreshToken() {
        final String uuid = UUIDGenerator.getUUID();
        if (null == mCacheUtil.getToken()) {
            EventBus.getDefault().post(new RefreshTokenEvent(uuid, 401, null));
            return null;
        }

        Call<Token> call = mService.refreshToken(OAuth.client_id, OAuth.client_secret,
                OAuth.GRANT_TYPE_REFRESH, mCacheUtil.getToken().getRefresh_token());
        call.enqueue(new TokenCallback(mCacheUtil, new RefreshTokenEvent(uuid)));
        return uuid;
    }

    @Override
    public Token getCacheToken() {
        return mCacheUtil.getToken();
    }

    @Override
    public String updateDevices() {
        String uuid = UUIDGenerator.getUUID();
        mService.updateDevices("android", mCacheUtil.getToken().getAccess_token())
                .enqueue(new BaseCallback<>(new UpdateDevicesEvent(uuid)));
        return uuid;
    }

    @Override
    public String deleteDevices() {
        String uuid = UUIDGenerator.getUUID();
        mService.deleteDevices("android", mCacheUtil.getToken().getAccess_token())
                .enqueue(new BaseCallback<>(new DeleteDevicesEvent(uuid)));
        return uuid;
    }
}
