package com.gcssloop.diycode_sdk.api.login.event;

import android.support.annotation.NonNull;

import com.gcssloop.diycode_sdk.api.base.event.BaseEvent;
import com.gcssloop.diycode_sdk.api.login.bean.Token;

/**
 * Created by zhangyuanyuan on 2017/12/11.
 */

public class LoginEvent extends BaseEvent<Token>{

    public LoginEvent(@NonNull String uuid) {
        super(uuid);
    }

    public LoginEvent(@NonNull String uuid, @NonNull Integer code, @NonNull Token token) {
        super(uuid, code, token);
    }
}
