package com.gcssloop.diycode_sdk.api.user.event;

import android.support.annotation.NonNull;

import com.gcssloop.diycode_sdk.api.base.event.BaseEvent;
import com.gcssloop.diycode_sdk.api.user.bean.UserDetail;

/**
 * Created by zhangyuanyuan on 2017/12/12.
 */

public class GetUserEvent extends BaseEvent<UserDetail> {
    public GetUserEvent(@NonNull String uuid) {
        super(uuid);
    }

    public GetUserEvent(@NonNull String uuid, @NonNull Integer code, @NonNull UserDetail userDetail) {
        super(uuid, code, userDetail);
    }
}
