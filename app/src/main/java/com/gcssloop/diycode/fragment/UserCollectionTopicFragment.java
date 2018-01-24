package com.gcssloop.diycode.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.gcssloop.diycode.fragment.base.SimpleRefreshRecyclerFragment;
import com.gcssloop.diycode.fragment.provider.TopicProvider;
import com.gcssloop.diycode_sdk.api.topic.bean.Topic;
import com.gcssloop.diycode_sdk.api.user.event.GetUserCollectionTopicListEvent;
import com.gcssloop.recyclerview.adapter.multitype.HeaderFooterAdapter;

/**
 * Created by zhangyuanyuan on 2017/12/14.
 */

public class UserCollectionTopicFragment extends SimpleRefreshRecyclerFragment<Topic,
        GetUserCollectionTopicListEvent> {

    private static String Key_User_Login_Name = "Key_User_Login_Name";
    private String loginName;

    public static UserCollectionTopicFragment newInstance(String user_login_name) {
        Bundle args = new Bundle();
        args.putString(Key_User_Login_Name, user_login_name);
        UserCollectionTopicFragment fragment = new UserCollectionTopicFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void initData(HeaderFooterAdapter adapter) {
        Bundle args = getArguments();
        loginName = args.getString(Key_User_Login_Name);
        loadMore();
    }

    @Override
    protected void setAdapterRegister(Context context, RecyclerView recyclerView, HeaderFooterAdapter adapter) {
        adapter.register(Topic.class, new TopicProvider(context));
    }

    @NonNull
    @Override
    protected String request(int offset, int limit) {
        return mDiycode.getUserCollectionTopicList(loginName, offset, limit);
    }
}
