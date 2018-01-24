package com.gcssloop.diycode_sdk.api.topic.api;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gcssloop.diycode_sdk.api.base.callback.BaseCallback;
import com.gcssloop.diycode_sdk.api.base.impl.BaseImpl;
import com.gcssloop.diycode_sdk.api.topic.event.BanTopicEvent;
import com.gcssloop.diycode_sdk.api.topic.event.CollectionTopicEvent;
import com.gcssloop.diycode_sdk.api.topic.event.CreateTopicEvent;
import com.gcssloop.diycode_sdk.api.topic.event.CreateTopicReplyEvent;
import com.gcssloop.diycode_sdk.api.topic.event.DeleteTopicEvent;
import com.gcssloop.diycode_sdk.api.topic.event.DeleteTopicReplyEvent;
import com.gcssloop.diycode_sdk.api.topic.event.GetTopicEvent;
import com.gcssloop.diycode_sdk.api.topic.event.GetTopicRepliesListEvent;
import com.gcssloop.diycode_sdk.api.topic.event.GetTopicReplyEvent;
import com.gcssloop.diycode_sdk.api.topic.event.GetTopicsListEvent;
import com.gcssloop.diycode_sdk.api.topic.event.UnCollectionTopicEvent;
import com.gcssloop.diycode_sdk.api.topic.event.UnWatchTopicEvent;
import com.gcssloop.diycode_sdk.api.topic.event.UpdateTopicEvent;
import com.gcssloop.diycode_sdk.api.topic.event.UpdateTopicReplyEvent;
import com.gcssloop.diycode_sdk.api.topic.event.WatchTopicEvent;
import com.gcssloop.diycode_sdk.utils.UUIDGenerator;

/**
 * Created by zhangyuanyuan on 2017/12/12.
 */

public class TopicImpl extends BaseImpl<TopicService> implements TopicAPI {

    public TopicImpl(@NonNull Context context) {
        super(context);
    }

    /**
     * 获取 topic 列表
     * @param type
     * @param node_id
     * @param offset
     * @param limit
     * @return
     */
    @Override
    public String getTopicsList(String type, @Nullable Integer node_id, @Nullable Integer offset, @Nullable Integer limit) {
        final String uuid = UUIDGenerator.getUUID();
        mService.getTopicsList(type, node_id, offset, limit)
                .enqueue(new BaseCallback<>(new GetTopicsListEvent(uuid)));
        return uuid;
    }

    /**
     * 创建一个新的 topic
     * @param title
     * @param body
     * @param node_id
     * @return
     */
    @Override
    public String createTopic(@NonNull String title, @NonNull String body, @NonNull Integer node_id) {
        final String uuid = UUIDGenerator.getUUID();
        mService.createTopic(title, body, node_id).enqueue(new BaseCallback<>(new CreateTopicEvent(uuid)));
        return uuid;
    }

    /**
     * 获取 topic 内容
     * @param id
     * @return
     */
    @Override
    public String getTopic(@NonNull int id) {
        final String uuid = UUIDGenerator.getUUID();
        mService.getTopic(id).enqueue(new BaseCallback<>(new GetTopicEvent(uuid)));
        return uuid;
    }

    /**
     * 更新(修改) topic
     * @param id
     * @param title
     * @param body
     * @param node_id
     * @return
     */
    @Override
    public String updateTopic(@NonNull int id, @NonNull String title, @NonNull String body, @NonNull Integer node_id) {
        final String uuid = UUIDGenerator.getUUID();
        mService.updateTopic(id, title, body, node_id).enqueue(new BaseCallback<>(new UpdateTopicEvent(uuid)));
        return uuid;
    }

    /**
     * 删除一个话题
     * @param id
     * @return
     */
    @Override
    public String deleteTopic(@NonNull int id) {
        final String uuid = UUIDGenerator.getUUID();
        mService.deleteTopic(id).enqueue(new BaseCallback<>(new DeleteTopicEvent(uuid)));
        return uuid;
    }

    /**
     * 收藏话题
     * @param id
     * @return
     */
    @Override
    public String collectionTopic(@NonNull int id) {
        final String uuid = UUIDGenerator.getUUID();
        mService.collectionTopic(id).enqueue(new BaseCallback<>(new CollectionTopicEvent(uuid)));
        return uuid;
    }

    /**
     * 取消收藏话题
     * @param id
     * @return
     */
    @Override
    public String unCollectionTopic(@NonNull int id) {
        final String uuid = UUIDGenerator.getUUID();
        mService.unCollectionTopic(id).enqueue(new BaseCallback<>(new UnCollectionTopicEvent(uuid)));
        return uuid;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public String watchTopic(@NonNull int id) {
        final String uuid = UUIDGenerator.getUUID();
        mService.watchTopic(id).enqueue(new BaseCallback<>(new WatchTopicEvent(uuid)));
        return uuid;
    }

    /**
     * 取消关注话题
     * @param id
     * @return
     */
    @Override
    public String unWatchTopic(@NonNull int id) {
        final String uuid = UUIDGenerator.getUUID();
        mService.unWatchTopic(id).enqueue(new BaseCallback<>(new UnWatchTopicEvent(uuid)));
        return uuid;
    }

    /**
     * 获取 topic 回复列表
     * @param id
     * @param offset
     * @param limit
     * @return
     */
    @Override
    public String getTopicRepliesList(@NonNull int id, @Nullable Integer offset, @Nullable Integer limit) {
        final String uuid = UUIDGenerator.getUUID();
        mService.getTopicRepliesList(id, offset, limit).enqueue(new BaseCallback<>(new GetTopicRepliesListEvent(uuid)));
        return uuid;
    }

    /**
     * 创建 topic 回帖(回复，评论)
     * @param id
     * @param body
     * @return
     */
    @Override
    public String createTopicReply(@NonNull int id, @NonNull String body) {
        final String uuid = UUIDGenerator.getUUID();
        mService.createTopicReply(id, body).enqueue(new BaseCallback<>(new CreateTopicReplyEvent(uuid)));
        return uuid;
    }

    /**
     * 获取回帖的详细内容（一般用于编辑回帖的时候）
     * @param id
     * @return
     */
    @Override
    public String getTopicReply(@NonNull int id) {
        final String uuid = UUIDGenerator.getUUID();
        mService.getTopicReply(id).enqueue(new BaseCallback<>(new GetTopicReplyEvent(uuid)));
        return uuid;
    }

    /**
     * 更新回帖
     * @param id
     * @param body
     * @return
     */
    @Override
    public String updateTopicReply(@NonNull int id, @NonNull String body) {
        final String uuid = UUIDGenerator.getUUID();
        mService.updateTopicReply(id, body).enqueue(new BaseCallback<>(new UpdateTopicReplyEvent(uuid)));
        return uuid;
    }

    /**
     * 删除回帖
     * @param id
     * @return
     */
    @Override
    public String deleteTopicReply(@NonNull int id) {
        final String uuid = UUIDGenerator.getUUID();
        mService.deleteTopicReply(id).enqueue(new BaseCallback<>(new DeleteTopicReplyEvent(uuid)));
        return uuid;
    }

    /**
     * 屏蔽话题，移到 NoPoint 节点 (管理员限定)
     * @param id
     * @return
     */
    @Override
    public String banTopic(@NonNull int id) {
        final String uuid = UUIDGenerator.getUUID();
        mService.banTopic(id).enqueue(new BaseCallback<>(new BanTopicEvent(uuid)));
        return uuid;
    }
}
