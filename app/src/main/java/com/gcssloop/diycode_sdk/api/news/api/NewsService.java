package com.gcssloop.diycode_sdk.api.news.api;

import com.gcssloop.diycode_sdk.api.base.bean.Node;
import com.gcssloop.diycode_sdk.api.base.bean.State;
import com.gcssloop.diycode_sdk.api.news.bean.New;
import com.gcssloop.diycode_sdk.api.news.bean.NewReply;

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
 * Created by zhangyuanyuan on 2017/12/13.
 */

public interface NewsService {

    /**
     * 获取 news 列表
     * @param node_id
     * @param offset
     * @param limit
     * @return
     */
    @GET("news.json")
    Call<List<New>> getNewsList(@Query("node_id") Integer node_id, @Query("offset") Integer offset,
                                @Query("limit") Integer limit);

    /**
     * 创建一个 new (分享)
     * @param title
     * @param address
     * @param node_id
     * @return
     */
    @POST("news.json")
    @FormUrlEncoded
    Call<New> createNews(@Field("title") Integer title, @Field("address") Integer address,
                         @Field("node_id") Integer node_id);

    /**
     * 获取 news 回帖列表
     * @param id
     * @param offset
     * @param limit
     * @return
     */
    @GET("news/{id}/replies.json")
    Call<List<NewReply>> getNewsRepliesList(@Path("id") int id, @Query("offset") Integer offset,
                                            @Query("limit") Integer limit);

    /**
     * 创建 news 回帖 (暂不可用, 没有api)
     * @param id
     * @param body
     * @return
     */
    @Deprecated
    @POST("news/{id}/replies.json")
    Call<NewReply> createNewsReply(@Path("id") int id, @Field("body") Integer body);

    /**
     * 获取 news 回帖详情
     * @param id
     * @return
     */
    @GET("news_replies/{id}.json")
    Call<NewReply> getNewsReply(@Path("id") int id);

    /**
     * 更新 news 回帖
     * @param id
     * @param body
     * @return
     */
    @POST("news_replies/{id}.json")
    Call<NewReply> updateNewsReply(@Path("id") int id, @Field("body") String body);

    /**
     * 删除 news 回帖
     * @param id
     * @return
     */
    @DELETE("news_replies/{id}.json")
    Call<State> deleteNewsReply(@Path("id") int id);

    /**
     * 获取 news 分类列表
     * @return
     */
    @GET("news/nodes.json")
    Call<List<Node>> getNewsNodesList();


}
