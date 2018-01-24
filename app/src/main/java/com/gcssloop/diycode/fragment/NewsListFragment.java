package com.gcssloop.diycode.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gcssloop.diycode.fragment.base.SimpleRefreshRecyclerFragment;
import com.gcssloop.diycode.fragment.provider.NewsProvider;
import com.gcssloop.diycode_sdk.api.news.bean.New;
import com.gcssloop.diycode_sdk.api.news.event.GetNewsListEvent;
import com.gcssloop.recyclerview.adapter.multitype.HeaderFooterAdapter;

import java.util.List;

/**
 * Created by zhangyuanyuan on 2017/12/12.
 */

public class NewsListFragment extends SimpleRefreshRecyclerFragment<New, GetNewsListEvent> {

    private boolean isFirstLaunch = true;

    public static NewsListFragment newInstance() {
        Bundle bundle = new Bundle();
        NewsListFragment fragment = new NewsListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initData(HeaderFooterAdapter adapter) {
        List<Object> news = mDataCache.getNewsListObj();
        if (null != news && news.size() > 0) {
            pageIndex = mConfig.getNewsListPageIndex();
            adapter.addDatas(news);
            if (isFirstLaunch) {
                int lastPosition = mConfig.getNewsListLastPosition();
                mRecyclerView.getLayoutManager().scrollToPosition(lastPosition);
                isFirstAddFooter = false;
                isFirstLaunch = false;
            }
        } else {
            loadMore();
        }

    }

    @Override
    protected void setAdapterRegister(Context context, RecyclerView recyclerView, HeaderFooterAdapter adapter) {
        adapter.register(New.class, new NewsProvider(getContext()));
    }

    @NonNull
    @Override
    protected String request(int offset, int limit) {
        return mDiycode.getNewsList(null, offset, limit);
    }

    @Override
    protected void onRefresh(GetNewsListEvent event, HeaderFooterAdapter adapter) {
        super.onRefresh(event, adapter);
        mDataCache.saveNewsListObj(adapter.getDatas());
    }

    @Override
    protected void onLoadMore(GetNewsListEvent event, HeaderFooterAdapter adapter) {
        super.onLoadMore(event, adapter);
        mDataCache.saveNewsListObj(adapter.getDatas());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // 存储 PageIndex
        mConfig.saveNewsListPageIndex(pageIndex);
        // 存储 RecyclerView 滚动位置
        View view = mRecyclerView.getLayoutManager().getChildAt(0);
        int lastPosition = mRecyclerView.getLayoutManager().getPosition(view);
        mConfig.saveNewsListPosition(lastPosition);
    }

}
