package com.gcssloop.diycode_sdk.api.user.event;

import android.support.annotation.NonNull;

import com.gcssloop.diycode_sdk.api.base.event.BaseEvent;
import com.gcssloop.diycode_sdk.api.user.bean.User;

import java.util.List;

/**
 * Created by zhangyuanyuan on 2017/12/12.
 */

public class GetUserFollowingListEvent extends BaseEvent<List<User>> {
    public GetUserFollowingListEvent(@NonNull String uuid) {
        super(uuid);
    }

    public GetUserFollowingListEvent(@NonNull String uuid, @NonNull Integer code, @NonNull List<User> users) {
        super(uuid, code, users);
    }
}
