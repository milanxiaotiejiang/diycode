package com.gcssloop.diycode_sdk.api.news.event;

import android.support.annotation.NonNull;

import com.gcssloop.diycode_sdk.api.base.event.BaseEvent;
import com.gcssloop.diycode_sdk.api.news.bean.New;

import java.util.List;

/**
 * Created by zhangyuanyuan on 2017/12/13.
 */

public class GetNewsListEvent extends BaseEvent<List<New>> {
    public GetNewsListEvent(@NonNull String uuid) {
        super(uuid);
    }

    public GetNewsListEvent(@NonNull String uuid, @NonNull Integer code, @NonNull List<New> news) {
        super(uuid, code, news);
    }
}
