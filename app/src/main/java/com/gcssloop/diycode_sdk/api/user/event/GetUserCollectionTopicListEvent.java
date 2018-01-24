package com.gcssloop.diycode_sdk.api.user.event;

import android.support.annotation.NonNull;

import com.gcssloop.diycode_sdk.api.base.event.BaseEvent;
import com.gcssloop.diycode_sdk.api.topic.bean.Topic;

import java.util.List;

/**
 * Created by zhangyuanyuan on 2017/12/12.
 */

public class GetUserCollectionTopicListEvent extends BaseEvent<List<Topic>> {
    public GetUserCollectionTopicListEvent(@NonNull String uuid) {
        super(uuid);
    }

    public GetUserCollectionTopicListEvent(@NonNull String uuid, @NonNull Integer code, @NonNull List<Topic> topics) {
        super(uuid, code, topics);
    }
}
