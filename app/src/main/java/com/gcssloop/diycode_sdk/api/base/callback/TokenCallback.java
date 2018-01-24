package com.gcssloop.diycode_sdk.api.base.callback;

import android.support.annotation.Nullable;

import com.gcssloop.diycode_sdk.api.base.event.BaseEvent;
import com.gcssloop.diycode_sdk.api.login.bean.Token;
import com.gcssloop.diycode_sdk.utils.CacheUtil;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by zhangyuanyuan on 2017/12/11.
 */

public class TokenCallback extends BaseCallback<Token> {


    private CacheUtil cacheUtil;

    public <Event extends BaseEvent<Token>> TokenCallback(@Nullable CacheUtil cacheUtil, @Nullable Event event) {
        super(event);
        this.cacheUtil = cacheUtil;
    }

    @Override
    public void onResponse(Call<Token> call, Response<Token> response) {
        if (response.isSuccessful()) {
            Token token = response.body();
            cacheUtil.saveToken(token);
            EventBus.getDefault().post(event.setEvent(response.code(), token));
        } else {
            EventBus.getDefault().post(event.setEvent(response.code(), null));
        }
    }
}
