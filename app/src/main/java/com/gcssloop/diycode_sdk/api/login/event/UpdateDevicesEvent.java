package com.gcssloop.diycode_sdk.api.login.event;

import android.support.annotation.NonNull;

import com.gcssloop.diycode_sdk.api.base.bean.State;
import com.gcssloop.diycode_sdk.api.base.event.BaseEvent;

/**
 * Created by zhangyuanyuan on 2017/12/11.
 */

public class UpdateDevicesEvent extends BaseEvent<State> {

    public UpdateDevicesEvent(@NonNull String uuid) {
        super(uuid);
    }

    public UpdateDevicesEvent(@NonNull String uuid, @NonNull Integer code, @NonNull State state) {
        super(uuid, code, state);
    }
}
