package com.gcssloop.diycode_sdk.api.topic.api;

import com.gcssloop.diycode_sdk.api.base.bean.State;
import com.gcssloop.diycode_sdk.api.topic.bean.Topic;
import com.gcssloop.diycode_sdk.api.topic.bean.TopicContent;
import com.gcssloop.diycode_sdk.api.topic.bean.TopicReply;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by zhangyuanyuan on 2017/12/12.
 */

public interface TopicService {

    /**
     * 获取 topic 列表
     *
     * @param type
     * @param node_id
     * @param offset
     * @param limit
     * @return
     */
    @GET("topics.json")
    Call<List<Topic>> getTopicsList(@Query("type") String type, @Query("node_id") Integer node_id,
                                    @Query("offset") Integer offset, @Query("limit") Integer limit);

    /**
     * 创建一个新的 topic
     *
     * @param title
     * @param body
     * @param node_id
     * @return
     */
    @POST("topics.json")
    @FormUrlEncoded
    Call<TopicContent> createTopic(@Field("title") String title, @Field("body") String body,
                                   @Field("node_id") Integer node_id);

    /**
     * 获取 topic 内容
     *
     * @param id
     * @return
     */
    @GET("topics/{id}.json")
    Call<TopicContent> getTopic(@Path("id") int id);

    /**
     * 更新(修改) topic
     *
     * @param id
     * @param title
     * @param body
     * @param node_id
     * @return
     */
    @POST("topics/{id}.json")
    @FormUrlEncoded
    Call<TopicContent> updateTopic(@Path("id") int id, @Field("title") String title,
                                   @Field("body") String body, @Field("node_id") Integer node_id);

    /**
     * 删除一个话题
     *
     * @param id
     * @return
     */
    @DELETE("topics/{id}.json")
    Call<State> deleteTopic(@Path("id") int id);

    /**
     * 收藏话题
     *
     * @param id
     * @return
     */
    @POST("topics/{id}/favorite.json")
    @FormUrlEncoded
    Call<State> collectionTopic(@Path("id") int id);

    /**
     * 取消收藏话题
     *
     * @param id
     * @return
     */
    @POST("topics/{id}/unfavorite.json")
    @FormUrlEncoded
    Call<State> unCollectionTopic(@Path("id") int id);

    /**
     * 关注话题
     *
     * @param id
     * @return
     */
    @POST("topics/{id}/follow.json")
    @FormUrlEncoded
    Call<State> watchTopic(@Path("id") int id);

    /**
     * 取消关注话题
     *
     * @param id
     * @return
     */
    @POST("topics/{id}/unfollow.json")
    @FormUrlEncoded
    Call<State> unWatchTopic(@Path("id") int id);

    /**
     * 获取 topic 回复列表
     *
     * @param id
     * @param offset
     * @param limit
     * @return
     */
    @GET("topics/{id}/replies.json")
    Call<List<TopicReply>> getTopicRepliesList(@Path("id") int id, @Query("offset") Integer offset,
                                               @Query("limit") Integer limit);

    /**
     * 创建 topic 回帖(回复，评论)
     *
     * @param id
     * @param body
     * @return
     */
    @POST("topics/{id}/replies.json")
    @FormUrlEncoded
    Call<TopicReply> createTopicReply(@Path("id") int id, @Field("body") String body);

    /**
     * 获取回帖的详细内容（一般用于编辑回帖的时候）
     *
     * @param id
     * @return
     */
    @GET("replies/{id}.json")
    Call<TopicReply> getTopicReply(@Path("id") int id);

    /**
     * 更新回帖
     *
     * @param id
     * @param body
     * @return
     */
    @POST("replies/{id}.json")
    @FormUrlEncoded
    Call<TopicReply> updateTopicReply(@Path("id") int id, @Field("body") String body);

    /**
     * 删除回帖
     *
     * @param id
     * @return
     */
    @DELETE("replies/{id}.json")
    Call<State> deleteTopicReply(@Path("id") int id);

    /**
     * 屏蔽话题，移到 NoPoint 节点 (管理员限定)
     *
     * @param id
     * @return
     */
    @POST("topics/{id}/ban.json")
    @FormUrlEncoded
    Call<State> banTopic(@Path("id") int id);


}
