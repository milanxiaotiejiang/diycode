package com.gcssloop.diycode_sdk.api.user.api;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gcssloop.diycode_sdk.api.base.callback.BaseCallback;
import com.gcssloop.diycode_sdk.api.base.impl.BaseImpl;
import com.gcssloop.diycode_sdk.api.user.bean.User;
import com.gcssloop.diycode_sdk.api.user.bean.UserDetail;
import com.gcssloop.diycode_sdk.api.user.event.BlockUserEvent;
import com.gcssloop.diycode_sdk.api.user.event.FollowUserEvent;
import com.gcssloop.diycode_sdk.api.user.event.GetMeEvent;
import com.gcssloop.diycode_sdk.api.user.event.GetUserBlockedListEvent;
import com.gcssloop.diycode_sdk.api.user.event.GetUserCollectionTopicListEvent;
import com.gcssloop.diycode_sdk.api.user.event.GetUserCreateTopicListEvent;
import com.gcssloop.diycode_sdk.api.user.event.GetUserEvent;
import com.gcssloop.diycode_sdk.api.user.event.GetUserFollowerListEvent;
import com.gcssloop.diycode_sdk.api.user.event.GetUserFollowingListEvent;
import com.gcssloop.diycode_sdk.api.user.event.GetUserReplyTopicListEvent;
import com.gcssloop.diycode_sdk.api.user.event.GetUsersListEvent;
import com.gcssloop.diycode_sdk.api.user.event.UnBlockUserEvent;
import com.gcssloop.diycode_sdk.utils.UUIDGenerator;

import java.io.IOException;
import java.util.List;

/**
 * Created by zhangyuanyuan on 2017/12/12.
 */

public class UserImpl extends BaseImpl<UserService> implements UserAPI {

    public UserImpl(@NonNull Context context) {
        super(context);
    }

    @Override
    public String getUsersList(@Nullable Integer limit) {
        String uuid = UUIDGenerator.getUUID();
        mService.getUsersList(limit).enqueue(new BaseCallback<List<User>>(new GetUsersListEvent(uuid)));
        return uuid;
    }

    @Override
    public String getUser(@NonNull String login_name) {
        String uuid = UUIDGenerator.getUUID();
        mService.getUser(login_name).enqueue(new BaseCallback<UserDetail>(new GetUserEvent(uuid)));
        return uuid;
    }

    @Override
    public String getMe() {
        String uuid = UUIDGenerator.getUUID();
        mService.getMe().enqueue(new BaseCallback<>(new GetMeEvent(uuid)));
        return uuid;
    }

    @Override
    public UserDetail getMeNow() throws IOException {
        return mService.getMe().execute().body();
    }

    @Override
    public String blockUser(@NonNull String login_name) {
        String uuid = UUIDGenerator.getUUID();
        mService.blockUser(login_name).enqueue(new BaseCallback<>(new BlockUserEvent(uuid)));
        return uuid;
    }

    @Override
    public String unBlockUser(@NonNull String login_name) {
        String uuid = UUIDGenerator.getUUID();
        mService.unBlockUser(login_name).enqueue(new BaseCallback<>(new UnBlockUserEvent(uuid)));
        return uuid;
    }

    @Override
    public String getUserBlockedList(@NonNull String login_name, @Nullable Integer offset, @Nullable Integer limit) {
        String uuid = UUIDGenerator.getUUID();
        mService.getUserBlockedList(login_name, offset, limit).enqueue(new BaseCallback<List<User>>(new GetUserBlockedListEvent(uuid)));
        return uuid;
    }

    @Override
    public String followUser(@NonNull String login_name) {
        String uuid = UUIDGenerator.getUUID();
        mService.followUser(login_name).enqueue(new BaseCallback<>(new FollowUserEvent(uuid)));
        return uuid;
    }

    @Override
    public String unFollowUser(@NonNull String login_name) {
        String uuid = UUIDGenerator.getUUID();
        mService.followUser(login_name).enqueue(new BaseCallback<>(new FollowUserEvent(uuid)));
        return uuid;
    }

    @Override
    public String getUserFollowingList(@NonNull String login_name, @Nullable Integer offset, @Nullable Integer limit) {
        String uuid = UUIDGenerator.getUUID();
        mService.getUserFollowingList(login_name, offset, limit).enqueue(
                new BaseCallback<>(new GetUserFollowingListEvent(uuid)));
        return uuid;
    }

    @Override
    public String getUserFollowerList(@NonNull String login_name, @Nullable Integer offset, @Nullable Integer limit) {
        String uuid = UUIDGenerator.getUUID();
        mService.getUserFollowerList(login_name, offset, limit).enqueue(
                new BaseCallback<>(new GetUserFollowerListEvent(uuid)));
        return uuid;
    }

    @Override
    public String getUserCollectionTopicList(@NonNull String login_name, @Nullable Integer offset, @Nullable Integer limit) {
        String uuid = UUIDGenerator.getUUID();
        mService.getUserCollectionTopicList(login_name, offset, limit).enqueue(
                new BaseCallback<>(new GetUserCollectionTopicListEvent(uuid)));
        return uuid;
    }

    @Override
    public String getUserCreateTopicList(@NonNull String login_name, @Nullable String order, @Nullable Integer offset, @Nullable Integer limit) {
        String uuid = UUIDGenerator.getUUID();
        mService.getUserCreateTopicList(login_name, order, offset, limit).enqueue(
                new BaseCallback<>(new GetUserCreateTopicListEvent(uuid)));
        return uuid;
    }

    @Override
    public String getUserReplyTopicList(@NonNull String login_name, @Nullable String order, @Nullable Integer offset, @Nullable Integer limit) {
        String uuid = UUIDGenerator.getUUID();
        mService.getUserReplyTopicList(login_name, order, offset, limit).enqueue(
                new BaseCallback<>(new GetUserReplyTopicListEvent(uuid)));
        return uuid;
    }
}
