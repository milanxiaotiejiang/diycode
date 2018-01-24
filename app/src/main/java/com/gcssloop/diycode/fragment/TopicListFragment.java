package com.gcssloop.diycode.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gcssloop.diycode.fragment.base.SimpleRefreshRecyclerFragment;
import com.gcssloop.diycode.fragment.provider.TopicProvider;
import com.gcssloop.diycode_sdk.api.topic.bean.Topic;
import com.gcssloop.diycode_sdk.api.topic.event.GetTopicsListEvent;
import com.gcssloop.recyclerview.adapter.multitype.HeaderFooterAdapter;

import java.util.List;

/**
 * Created by zhangyuanyuan on 2017/12/12.
 */

public class TopicListFragment extends SimpleRefreshRecyclerFragment<Topic, GetTopicsListEvent> {

    private boolean isFirstLaunch = true;

    public static TopicListFragment newInstance() {
        Bundle bundle = new Bundle();
        TopicListFragment fragment = new TopicListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initData(HeaderFooterAdapter adapter) {
        List<Object> topics = mDataCache.getTopicsListObj();
        if (null != topics && topics.size() > 0) {
            pageIndex = mConfig.getTopicListPageIndex();
            adapter.addDatas(topics);
            if (isFirstLaunch) {
                int lastPostion = mConfig.getTopicListLastPosition();
                mRecyclerView.getLayoutManager().scrollToPosition(lastPostion);
                isFirstAddFooter = false;
                isFirstLaunch = false;
            }
        } else {
            loadMore();
        }

    }

    @Override
    protected void setAdapterRegister(Context context, RecyclerView recyclerView, HeaderFooterAdapter adapter) {
        adapter.register(Topic.class, new TopicProvider(getContext()));
    }

    @NonNull
    @Override
    protected String request(int offset, int limit) {
        return mDiycode.getTopicsList(null, null, offset, limit);
    }


    @Override
    protected void onRefresh(GetTopicsListEvent event, HeaderFooterAdapter adapter) {
        super.onRefresh(event, adapter);
        mDataCache.saveTopicsListObj(adapter.getDatas());
    }

    @Override
    protected void onLoadMore(GetTopicsListEvent event, HeaderFooterAdapter adapter) {
        super.onLoadMore(event, adapter);
        mDataCache.saveTopicsListObj(adapter.getDatas());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mConfig.saveTopicListPageIndex(pageIndex);

        View view = mRecyclerView.getLayoutManager().getChildAt(0);
        int lastPosition = mRecyclerView.getLayoutManager().getPosition(view);
        mConfig.saveTopicListState(lastPosition, 0);
    }
}
