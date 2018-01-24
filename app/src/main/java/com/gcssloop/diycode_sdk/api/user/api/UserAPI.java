package com.gcssloop.diycode_sdk.api.user.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gcssloop.diycode_sdk.api.user.bean.UserDetail;

import java.io.IOException;

/**
 * Created by zhangyuanyuan on 2017/12/12.
 */

public interface UserAPI {

    String getUsersList(@Nullable Integer limit);

    String getUser(@NonNull String login_name);

    String getMe();

    UserDetail getMeNow() throws IOException;

    @Deprecated
    String blockUser(@NonNull String login_name);

    @Deprecated
    String unBlockUser(@NonNull String login_name);

    String getUserBlockedList(@NonNull String login_name,
                              @Nullable Integer offset, @Nullable Integer limit);

    @Deprecated
    String followUser(@NonNull String login_name);

    @Deprecated
    String unFollowUser(@NonNull String login_name);

    String getUserFollowingList(@NonNull String login_name,
                                @Nullable Integer offset, @Nullable Integer limit);

    String getUserFollowerList(@NonNull String login_name,
                               @Nullable Integer offset, @Nullable Integer limit);


    String getUserCollectionTopicList(@NonNull String login_name,
                                      @Nullable Integer offset, @Nullable Integer limit);

    String getUserCreateTopicList(@NonNull String login_name, @Nullable String order,
                                  @Nullable Integer offset, @Nullable Integer limit);

    String getUserReplyTopicList(@NonNull String login_name, @Nullable String order,
                                 @Nullable Integer offset, @Nullable Integer limit);
}
