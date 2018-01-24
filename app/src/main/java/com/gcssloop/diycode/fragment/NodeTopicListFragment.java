package com.gcssloop.diycode.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.gcssloop.diycode.fragment.base.SimpleRefreshRecyclerFragment;
import com.gcssloop.diycode.fragment.provider.TopicProvider;
import com.gcssloop.diycode_sdk.api.topic.bean.Topic;
import com.gcssloop.diycode_sdk.api.topic.event.GetTopicsListEvent;
import com.gcssloop.recyclerview.adapter.multitype.HeaderFooterAdapter;

/**
 * Created by zhangyuanyuan on 2017/12/14.
 */

public class NodeTopicListFragment extends SimpleRefreshRecyclerFragment<Topic, GetTopicsListEvent> {

    private static String Key_Node_ID = "Key_Node_ID";
    private int mNodeId = 0;

    public static NodeTopicListFragment newInstance(int nodeId) {
        Bundle args = new Bundle();
        args.putInt(Key_Node_ID, nodeId);
        NodeTopicListFragment fragment = new NodeTopicListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData(HeaderFooterAdapter adapter) {
        mNodeId = getArguments().getInt(Key_Node_ID, 0);
        loadMore();
    }

    @Override
    protected void setAdapterRegister(Context context, RecyclerView recyclerView, HeaderFooterAdapter adapter) {
        adapter.register(Topic.class, new TopicProvider(getContext()));
    }

    @NonNull
    @Override
    protected String request(int offset, int limit) {
        return mDiycode.getTopicsList(null, mNodeId, offset, limit);
    }
}
