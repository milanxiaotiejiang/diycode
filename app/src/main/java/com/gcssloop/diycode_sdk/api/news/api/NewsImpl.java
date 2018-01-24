package com.gcssloop.diycode_sdk.api.news.api;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gcssloop.diycode_sdk.api.base.callback.BaseCallback;
import com.gcssloop.diycode_sdk.api.base.impl.BaseImpl;
import com.gcssloop.diycode_sdk.api.news.event.CreateNewsEvent;
import com.gcssloop.diycode_sdk.api.news.event.CreateNewsReplyEvent;
import com.gcssloop.diycode_sdk.api.news.event.DeleteNewsReplyEvent;
import com.gcssloop.diycode_sdk.api.news.event.GetNewsListEvent;
import com.gcssloop.diycode_sdk.api.news.event.GetNewsNodesListEvent;
import com.gcssloop.diycode_sdk.api.news.event.GetNewsRepliesListEvent;
import com.gcssloop.diycode_sdk.api.news.event.GetNewsReplyEvent;
import com.gcssloop.diycode_sdk.api.news.event.UpdateNewsReplyEvent;
import com.gcssloop.diycode_sdk.utils.UUIDGenerator;

/**
 * Created by zhangyuanyuan on 2017/12/13.
 */

public class NewsImpl extends BaseImpl<NewsService> implements NewsAPI {

    public NewsImpl(@NonNull Context context) {
        super(context);
    }

    @Override
    public String getNewsList(@Nullable Integer node_id, @Nullable Integer offset, @Nullable Integer limit) {
        String uuid = UUIDGenerator.getUUID();
        mService.getNewsList(node_id, offset, limit).enqueue(new BaseCallback<>(new GetNewsListEvent(uuid)));
        return uuid;
    }

    @Override
    public String createNews(@NonNull Integer title, @NonNull Integer address, @NonNull Integer node_id) {
        String uuid = UUIDGenerator.getUUID();
        mService.createNews(title, address, node_id).enqueue(new BaseCallback<>(new CreateNewsEvent(uuid)));
        return uuid;
    }

    @Override
    public String getNewsRepliesList(@NonNull int id, @Nullable Integer offset, @Nullable Integer limit) {
        String uuid = UUIDGenerator.getUUID();
        mService.getNewsRepliesList(id, offset, limit).enqueue(new BaseCallback<>(new GetNewsRepliesListEvent(uuid)));
        return uuid;
    }

    @Override
    public String createNewsReply(@NonNull int id, @NonNull Integer body) {
        String uuid = UUIDGenerator.getUUID();
        mService.createNewsReply(id, body).enqueue(new BaseCallback<>(new CreateNewsReplyEvent(uuid)));
        return uuid;
    }

    @Override
    public String getNewsReply(@NonNull int id) {
        String uuid = UUIDGenerator.getUUID();
        mService.getNewsReply(id).enqueue(new BaseCallback<>(new GetNewsReplyEvent(uuid)));
        return uuid;
    }

    @Override
    public String updateNewsReply(@NonNull int id, @NonNull String body) {
        String uuid = UUIDGenerator.getUUID();
        mService.updateNewsReply(id, body).enqueue(new BaseCallback<>(new UpdateNewsReplyEvent(uuid)));
        return uuid;
    }

    @Override
    public String deleteNewsReply(@NonNull int id) {
        String uuid = UUIDGenerator.getUUID();
        mService.deleteNewsReply(id).enqueue(new BaseCallback<>(new DeleteNewsReplyEvent(uuid)));
        return uuid;
    }

    @Override
    public String getNewsNodesList() {
        String uuid = UUIDGenerator.getUUID();
        mService.getNewsNodesList().enqueue(new BaseCallback<>(new GetNewsNodesListEvent(uuid)));
        return uuid;
    }
}
