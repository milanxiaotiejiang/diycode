package com.gcssloop.diycode_sdk.api.user.event;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gcssloop.diycode_sdk.api.base.event.BaseEvent;
import com.gcssloop.diycode_sdk.api.user.bean.UserDetail;

/**
 * Created by zhangyuanyuan on 2017/12/12.
 */

public class GetMeEvent extends BaseEvent<UserDetail> {
    public GetMeEvent(@Nullable String uuid) {
        super(uuid);
    }

    public GetMeEvent(@Nullable String uuid, @NonNull Integer code, @Nullable UserDetail userDetail) {
        super(uuid, code, userDetail);
    }
}
