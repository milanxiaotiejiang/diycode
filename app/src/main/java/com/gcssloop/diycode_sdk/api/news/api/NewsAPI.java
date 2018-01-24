package com.gcssloop.diycode_sdk.api.news.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by zhangyuanyuan on 2017/12/13.
 */

public interface NewsAPI {

    /**
     * 获取 news 列表
     * @param node_id
     * @param offset
     * @param limit
     * @return
     */
    String getNewsList(@Nullable Integer node_id, @Nullable Integer offset, @Nullable Integer limit);

    /**
     * 创建一个 new (分享)
     * @param title
     * @param address
     * @param node_id
     * @return
     */
    String createNews(@NonNull Integer title, @NonNull Integer address, @NonNull Integer node_id);

    /**
     * 获取 news 回帖列表
     * @param id
     * @param offset
     * @param limit
     * @return
     */
    String getNewsRepliesList(@NonNull int id, @Nullable Integer offset, @Nullable Integer limit);

    /**
     * 创建 news 回帖 (暂不可用, 没有api)
     * @param id
     * @param body
     * @return
     */
    String createNewsReply(@NonNull int id, @NonNull Integer body);

    /**
     * 获取 news 回帖详情
     * @param id
     * @return
     */
    String getNewsReply(@NonNull int id);

    /**
     * 更新 news 回帖
     * @param id
     * @param body
     * @return
     */
    String updateNewsReply(@NonNull int id, @NonNull String body);

    /**
     * 删除 news 回帖
     * @param id
     * @return
     */
    String deleteNewsReply(@NonNull int id);

    /**
     * 获取 news 分类列表
     * @return
     */
    String getNewsNodesList();


}
