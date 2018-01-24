package com.gcssloop.diycode_sdk.api.topic.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by zhangyuanyuan on 2017/12/12.
 */

public interface TopicAPI {

    /**
     * 获取 topic 列表
     * @param type
     * @param node_id
     * @param offset
     * @param limit
     * @return
     */
    String getTopicsList(String type, @Nullable Integer node_id, @Nullable Integer offset, @Nullable Integer limit);

    /**
     * 创建一个新的 topic
     * @param title
     * @param body
     * @param node_id
     * @return
     */
    String createTopic(@NonNull String title, @NonNull String body, @NonNull Integer node_id);

    /**
     * 获取 topic 内容
     * @param id
     * @return
     */
    String getTopic(@NonNull int id);

    /**
     * 更新(修改) topic
     * @param id
     * @param title
     * @param body
     * @param node_id
     * @return
     */
    String updateTopic(@NonNull int id, @NonNull String title, @NonNull String body, @NonNull Integer node_id);

    /**
     * 删除一个话题
     * @param id
     * @return
     */
    String deleteTopic(@NonNull int id);

    /**
     * 收藏话题
     * @param id
     * @return
     */
    String collectionTopic(@NonNull int id);

    /**
     * 取消收藏话题
     * @param id
     * @return
     */
    String unCollectionTopic(@NonNull int id);

    /**
     * 关注话题
     * @param id
     * @return
     */
    String watchTopic(@NonNull int id);

    /**
     * 取消关注话题
     * @param id
     * @return
     */
    String unWatchTopic(@NonNull int id);

    /**
     * 获取 topic 回复列表
     * @param id
     * @param offset
     * @param limit
     * @return
     */
    String getTopicRepliesList(@NonNull int id, @Nullable Integer offset, @Nullable Integer limit);

    /**
     * 创建 topic 回帖(回复，评论)
     * @param id
     * @param body
     * @return
     */
    String createTopicReply(@NonNull int id, @NonNull String body);

    /**
     * 获取回帖的详细内容（一般用于编辑回帖的时候）
     * @param id
     * @return
     */
    String getTopicReply(@NonNull int id);

    /**
     * 更新回帖
     * @param id
     * @param body
     * @return
     */
    String updateTopicReply(@NonNull int id, @NonNull String body);

    /**
     * 删除回帖
     * @param id
     * @return
     */
    String deleteTopicReply(@NonNull int id);

    /**
     * 屏蔽话题，移到 NoPoint 节点 (管理员限定)
     * @param id
     * @return
     */
    String banTopic(@NonNull int id);


}
