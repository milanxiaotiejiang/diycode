package com.gcssloop.diycode_sdk.api.login.event;

import android.support.annotation.NonNull;

import com.gcssloop.diycode_sdk.api.base.event.BaseEvent;

/**
 * Created by zhangyuanyuan on 2017/12/11.
 */

public class LogoutEvent extends BaseEvent<String> {

    public LogoutEvent(@NonNull String uuid) {
        super(uuid);
    }

    public LogoutEvent(@NonNull String uuid, @NonNull Integer code, @NonNull String s) {
        super(uuid, code, s);
    }
}
