package com.gcssloop.diycode_sdk.api.user.event;

import android.support.annotation.NonNull;

import com.gcssloop.diycode_sdk.api.base.bean.State;
import com.gcssloop.diycode_sdk.api.base.event.BaseEvent;

/**
 * Created by zhangyuanyuan on 2017/12/12.
 */

public class FollowUserEvent extends BaseEvent<State> {
    public FollowUserEvent(@NonNull String uuid) {
        super(uuid);
    }

    public FollowUserEvent(@NonNull String uuid, @NonNull Integer code, @NonNull State state) {
        super(uuid, code, state);
    }
}
