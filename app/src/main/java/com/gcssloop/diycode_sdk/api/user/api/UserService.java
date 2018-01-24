package com.gcssloop.diycode_sdk.api.user.api;

import com.gcssloop.diycode_sdk.api.base.bean.State;
import com.gcssloop.diycode_sdk.api.topic.bean.Topic;
import com.gcssloop.diycode_sdk.api.user.bean.User;
import com.gcssloop.diycode_sdk.api.user.bean.UserDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by zhangyuanyuan on 2017/12/12.
 */

public interface UserService {

    @GET("users.json")
    Call<List<User>> getUsersList(@Query("limit") Integer limit);

    @GET("users/{login}.json")
    Call<UserDetail> getUser(@Path("login") String login_name);

    @GET("users/me.json")
    Call<UserDetail> getMe();

    @Deprecated
    @POST("users/{login}/block.json")
    Call<State> blockUser(@Path("login") String login_name);

    @Deprecated
    @POST("users/{login}/unblock.json")
    Call<State> unBlockUser(@Path("login") String login_name);

    @GET("users/{login}/blocked.json")
    Call<List<User>> getUserBlockedList(@Path("login") String login_name,
                                        @Query("offset") Integer offset, @Query("limit") Integer limit);

    @Deprecated
    @POST("users/{login}/follow.json")
    Call<State> followUser(@Path("login") String login_name);

    @Deprecated
    @POST("users/{login}/unfollow.json")
    Call<State> unFollowUser(@Path("login") String login_name);

    @GET("users/{login}/following.json")
    Call<List<User>> getUserFollowingList(@Path("login") String login_name,
                                          @Query("offset") Integer offset, @Query("limit") Integer limit);

    @GET("users/{login}/followers.json")
    Call<List<User>> getUserFollowerList(@Path("login") String login_name,
                                         @Query("offset") Integer offset, @Query("limit") Integer limit);

    @GET("users/{login}/favorites.json")
    Call<List<Topic>> getUserCollectionTopicList(@Path("login") String login_name,
                                                 @Query("offset") Integer offset, @Query("limit") Integer limit);

    @GET("users/{login}/topics.json")
    Call<List<Topic>> getUserCreateTopicList(@Path("login") String login_name, @Query("order") String order,
                                             @Query("offset") Integer offset, @Query("limit") Integer limit);

    @GET("users/{login}/replies.json")
    Call<List<Topic>> getUserReplyTopicList(@Path("login") String login_name, @Query("order") String order,
                                            @Query("offset") Integer offset, @Query("limit") Integer limit);
}
