package com.gcssloop.diycode_sdk.api.login.event;

import android.support.annotation.NonNull;

import com.gcssloop.diycode_sdk.api.base.bean.State;
import com.gcssloop.diycode_sdk.api.base.event.BaseEvent;

/**
 * Created by zhangyuanyuan on 2017/12/11.
 */

public class DeleteDevicesEvent extends BaseEvent<State> {

    public DeleteDevicesEvent(@NonNull String uuid) {
        super(uuid);
    }

    public DeleteDevicesEvent(@NonNull String uuid, @NonNull Integer code, @NonNull State state) {
        super(uuid, code, state);
    }
}
