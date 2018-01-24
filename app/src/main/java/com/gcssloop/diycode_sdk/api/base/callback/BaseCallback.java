package com.gcssloop.diycode_sdk.api.base.callback;

import android.support.annotation.Nullable;

import com.gcssloop.diycode_sdk.api.base.event.BaseEvent;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by zhangyuanyuan on 2017/12/11.
 */

public class BaseCallback<T> implements Callback<T> {

    protected BaseEvent<T> event;

    public <Event extends BaseEvent<T>> BaseCallback(@Nullable Event event) {
        this.event = event;
    }


    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            EventBus.getDefault().post(event.setEvent(response.code(), response.body()));
        } else {
            EventBus.getDefault().post(event.setEvent(response.code(), null));
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        EventBus.getDefault().post(event.setEvent(-1, null));
    }
}
