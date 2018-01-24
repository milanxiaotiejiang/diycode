package com.gcssloop.diycode_sdk.api;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gcssloop.diycode_sdk.api.base.bean.OAuth;
import com.gcssloop.diycode_sdk.api.login.api.LoginAPI;
import com.gcssloop.diycode_sdk.api.login.api.LoginImpl;
import com.gcssloop.diycode_sdk.api.login.bean.Token;
import com.gcssloop.diycode_sdk.api.news.api.NewsAPI;
import com.gcssloop.diycode_sdk.api.news.api.NewsImpl;
import com.gcssloop.diycode_sdk.api.sites.api.SitesAPI;
import com.gcssloop.diycode_sdk.api.sites.api.SitesImpl;
import com.gcssloop.diycode_sdk.api.topic.api.TopicAPI;
import com.gcssloop.diycode_sdk.api.topic.api.TopicImpl;
import com.gcssloop.diycode_sdk.api.user.api.UserAPI;
import com.gcssloop.diycode_sdk.api.user.api.UserImpl;
import com.gcssloop.diycode_sdk.api.user.bean.UserDetail;
import com.gcssloop.diycode_sdk.log.Config;
import com.gcssloop.diycode_sdk.log.Logger;
import com.gcssloop.diycode_sdk.utils.DebugUtil;

import java.io.IOException;

/**
 * Created by zhangyuanyuan on 2017/12/11.
 */

public class Diycode implements LoginAPI, UserAPI, TopicAPI, NewsAPI ,SitesAPI {

    private static LoginImpl sLoginImpl;
    private static UserImpl sUserImpl;
    private static TopicImpl sTopicImplement;
    private static NewsImpl sNewsImpl;
    private static SitesImpl sSitesImpl;

    private volatile static Diycode mDiycode;

    private Diycode() {
    }


    public static Diycode getSingleInstance() {
        if (null == mDiycode) {
            synchronized (Diycode.class) {
                if (null == mDiycode) {
                    mDiycode = new Diycode();
                }
            }
        }
        return mDiycode;
    }

    public static Diycode init(@NonNull Context context, @NonNull final String client_id,
                               @NonNull final String client_secret) {
        initLogger(context);

        OAuth.client_id = client_id;
        OAuth.client_secret = client_secret;

        initImplement(context);

        return getSingleInstance();
    }

    private static void initLogger(@NonNull Context context) {
        if (DebugUtil.isInDebug(context)) {
            Logger.init("Diycode").setLevel(Config.LEVEL_FULL);
        } else {
            Logger.init("Diycode").setLevel(Config.LEVEL_NONE);
        }
    }

    private static void initImplement(Context context) {
        sLoginImpl = new LoginImpl(context);
        sUserImpl = new UserImpl(context);
        sTopicImplement = new TopicImpl(context);
        sNewsImpl = new NewsImpl(context);
        sSitesImpl = new SitesImpl(context);
    }

    //--- login ------------------------------------------------------------------------------------
    @Override
    public String login(@NonNull String user_name, @NonNull String password) {
        return sLoginImpl.login(user_name, password);
    }

    @Override
    public void logout() {
        sLoginImpl.logout();
    }

    @Override
    public boolean isLogin() {
        return sLoginImpl.isLogin();
    }

    @Override
    public String refreshToken() {
        return sLoginImpl.refreshToken();
    }

    @Override
    public Token getCacheToken() {
        return sLoginImpl.getCacheToken();
    }

    @Override
    public String updateDevices() {
        return sLoginImpl.updateDevices();
    }

    @Override
    public String deleteDevices() {
        return sLoginImpl.deleteDevices();
    }

    //--- user info --------------------------------------------------------------------------------
    @Override
    public String getUsersList(@Nullable Integer limit) {
        return sUserImpl.getUsersList(limit);
    }

    @Override
    public String getUser(@NonNull String login_name) {
        return sUserImpl.getUser(login_name);
    }

    @Override
    public String getMe() {
        return sUserImpl.getMe();
    }

    @Override
    public UserDetail getMeNow() throws IOException {
        return sUserImpl.getMeNow();
    }

    @Override
    public String blockUser(@NonNull String login_name) {
        return sUserImpl.blockUser(login_name);
    }

    @Override
    public String unBlockUser(@NonNull String login_name) {
        return sUserImpl.unBlockUser(login_name);
    }

    @Override
    public String getUserBlockedList(@NonNull String login_name, @Nullable Integer offset, @Nullable Integer limit) {
        return sUserImpl.getUserBlockedList(login_name, offset, limit);
    }

    @Override
    public String followUser(@NonNull String login_name) {
        return sUserImpl.followUser(login_name);
    }

    @Override
    public String unFollowUser(@NonNull String login_name) {
        return sUserImpl.unFollowUser(login_name);
    }

    @Override
    public String getUserFollowingList(@NonNull String login_name, @Nullable Integer offset, @Nullable Integer limit) {
        return sUserImpl.getUserFollowingList(login_name, offset, limit);
    }

    @Override
    public String getUserFollowerList(@NonNull String login_name, @Nullable Integer offset, @Nullable Integer limit) {
        return sUserImpl.getUserFollowerList(login_name, offset, limit);
    }

    @Override
    public String getUserCollectionTopicList(@NonNull String login_name, @Nullable Integer offset, @Nullable Integer limit) {
        return sUserImpl.getUserCollectionTopicList(login_name, offset, limit);
    }

    @Override
    public String getUserCreateTopicList(@NonNull String login_name, @Nullable String order, @Nullable Integer offset, @Nullable Integer limit) {
        return sUserImpl.getUserCreateTopicList(login_name, order, offset, limit);
    }

    @Override
    public String getUserReplyTopicList(@NonNull String login_name, @Nullable String order, @Nullable Integer offset, @Nullable Integer limit) {
        return sUserImpl.getUserReplyTopicList(login_name, order, offset, limit);
    }

    //--- topic ------------------------------------------------------------------------------------
    @Override
    public String getTopicsList(String type, @Nullable Integer node_id, @Nullable Integer offset, @Nullable Integer limit) {
        return sTopicImplement.getTopicsList(type, node_id, offset, limit);
    }

    @Override
    public String createTopic(@NonNull String title, @NonNull String body, @NonNull Integer node_id) {
        return sTopicImplement.createTopic(title, body, node_id);
    }

    @Override
    public String getTopic(@NonNull int id) {
        return sTopicImplement.getTopic(id);
    }

    @Override
    public String updateTopic(@NonNull int id, @NonNull String title, @NonNull String body, @NonNull Integer node_id) {
        return sTopicImplement.updateTopic(id, title, body, node_id);
    }

    @Override
    public String deleteTopic(@NonNull int id) {
        return sTopicImplement.deleteTopic(id);
    }

    @Override
    public String collectionTopic(@NonNull int id) {
        return sTopicImplement.collectionTopic(id);
    }

    @Override
    public String unCollectionTopic(@NonNull int id) {
        return sTopicImplement.unCollectionTopic(id);
    }

    @Override
    public String watchTopic(@NonNull int id) {
        return sTopicImplement.watchTopic(id);
    }

    @Override
    public String unWatchTopic(@NonNull int id) {
        return sTopicImplement.unWatchTopic(id);
    }

    @Override
    public String getTopicRepliesList(@NonNull int id, @Nullable Integer offset, @Nullable Integer limit) {
        return sTopicImplement.getTopicRepliesList(id, offset, limit);
    }

    @Override
    public String createTopicReply(@NonNull int id, @NonNull String body) {
        return sTopicImplement.createTopicReply(id, body);
    }

    @Override
    public String getTopicReply(@NonNull int id) {
        return sTopicImplement.getTopicReply(id);
    }

    @Override
    public String updateTopicReply(@NonNull int id, @NonNull String body) {
        return sTopicImplement.updateTopicReply(id, body);
    }

    @Override
    public String deleteTopicReply(@NonNull int id) {
        return sTopicImplement.deleteTopicReply(id);
    }

    @Override
    public String banTopic(@NonNull int id) {
        return sTopicImplement.banTopic(id);
    }

    //--- news -------------------------------------------------------------------------------------
    @Override
    public String getNewsList(@Nullable Integer node_id, @Nullable Integer offset,
                              @Nullable Integer limit) {
        return sNewsImpl.getNewsList(node_id, offset, limit);
    }

    @Override
    public String createNews(@NonNull Integer title, @NonNull Integer address,
                             @NonNull Integer node_id) {
        return sNewsImpl.createNews(title, address, node_id);
    }

    @Override
    public String getNewsRepliesList(@NonNull int id, @Nullable Integer offset,
                                     @Nullable Integer limit) {
        return sNewsImpl.getNewsRepliesList(id, offset, limit);
    }

    @Override
    public String createNewsReply(@NonNull int id, @NonNull Integer body) {
        return sNewsImpl.createNewsReply(id, body);
    }

    @Override
    public String getNewsReply(@NonNull int id) {
        return sNewsImpl.getNewsReply(id);
    }

    @Override
    public String updateNewsReply(@NonNull int id, @NonNull String body) {
        return sNewsImpl.updateNewsReply(id, body);
    }

    @Override
    public String deleteNewsReply(@NonNull int id) {
        return sNewsImpl.deleteNewsReply(id);
    }

    @Override
    public String getNewsNodesList() {
        return sNewsImpl.getNewsNodesList();
    }


    @Override
    public String getSites() {
        return sSitesImpl.getSites();
    }
}
