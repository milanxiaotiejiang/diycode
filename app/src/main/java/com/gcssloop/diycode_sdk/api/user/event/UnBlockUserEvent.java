package com.gcssloop.diycode_sdk.api.user.event;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gcssloop.diycode_sdk.api.base.bean.State;
import com.gcssloop.diycode_sdk.api.base.event.BaseEvent;

/**
 * Created by zhangyuanyuan on 2017/12/12.
 */

public class UnBlockUserEvent  extends BaseEvent<State> {
    public UnBlockUserEvent(@Nullable String uuid) {
        super(uuid);
    }

    public UnBlockUserEvent(@Nullable String uuid, @NonNull Integer code, @Nullable State state) {
        super(uuid, code, state);
    }
}
