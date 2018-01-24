package com.gcssloop.diycode.fragment.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gcssloop.diycode.R;
import com.gcssloop.diycode.base.app.ViewHolder;
import com.gcssloop.diycode.fragment.bean.Footer;
import com.gcssloop.diycode.fragment.provider.FooterProvider;
import com.gcssloop.diycode_sdk.api.base.event.BaseEvent;
import com.gcssloop.recyclerview.adapter.multitype.HeaderFooterAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by zhangyuanyuan on 2017/12/12.
 */

public abstract class RefreshRecyclerFragment<T, Event extends BaseEvent<List<T>>> extends BaseFragment {

    public static final String POST_LOAD_MORE = "load_more";
    public static final String POST_REFRESH = "refresh";
    private ArrayMap<String, String> mPostTypes = new ArrayMap<>();    // 请求类型

    // 当前状态
    private static final int STATE_NORMAL = 0;      // 正常
    private static final int STATE_NO_MORE = 1;     // 正在
    private static final int STATE_LOADING = 2;     // 加载
    private static final int STATE_REFRESH = 3;     // 刷新
    private int mState = STATE_NORMAL;

    // 分页加载
    protected int pageIndex = 0;                      // 当面页码
    protected int pageCount = 20;                     // 每页个数

    // View
    private SwipeRefreshLayout mRefreshLayout;
    protected RecyclerView mRecyclerView;

    // 状态
    private boolean refreshEnable = true;           // 是否允许刷新
    private boolean loadMoreEnable = true;          // 是否允许加载

    protected HeaderFooterAdapter mAdapter;
    private FooterProvider mFooterProvider;

    protected boolean isFirstAddFooter = true;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_refresh_recycler;
    }

    @Override
    protected void initViews(ViewHolder holder, View root) {
        mAdapter = new HeaderFooterAdapter();
        mFooterProvider = new FooterProvider(getContext()) {
            @Override
            public void needLoadMore() {
                if (isFirstAddFooter) {
                    isFirstAddFooter = false;
                    return;
                }
                loadMore();
            }
        };
        mFooterProvider.setFooterNormal();
        mAdapter.registerFooter(new Footer(), mFooterProvider);

        mRefreshLayout = holder.get(R.id.refresh_layout);
        mRefreshLayout.setProgressViewOffset(false, -20, 80);
        mRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.diy_red));
        mRefreshLayout.setEnabled(true);

        mRecyclerView = holder.get(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(getRecyclerViewLayoutManager());
        setAdapterRegister(getContext(), mRecyclerView, mAdapter);

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });

        initData(mAdapter);
    }

    protected void refresh() {
        if (!refreshEnable)
            return;
        pageIndex = 0;
        String uuid = request(pageIndex * pageCount, pageCount);
        mPostTypes.put(uuid, POST_REFRESH);
        pageIndex++;
        mState = STATE_REFRESH;
    }

    protected void loadMore() {
        if (!refreshEnable)
            return;
        if (mState == STATE_NO_MORE)
            return;
        String uuid = request(pageIndex * pageCount, pageCount);
        mPostTypes.put(uuid, POST_LOAD_MORE);
        pageIndex++;
        mState = STATE_LOADING;
        mFooterProvider.setFooterLoading();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onResuitEvent(Event event) {
        String postType = mPostTypes.get(event.getUUID());
        if (event.isOk()) {
            if (postType.equals(POST_LOAD_MORE)) {
                onLoadMore(event);
            } else if (postType.equals(POST_REFRESH)) {
                onRefresh(event);
            }
        } else {
            onError(event);
        }
        mPostTypes.remove(event.getUUID());
    }

    protected void onRefresh(Event event) {
        mState = STATE_NORMAL;
        mRefreshLayout.setRefreshing(false);
        onRefresh(event, mAdapter);
    }

    protected void onLoadMore(Event event) {
        if (event.getBean().size() < pageCount) {
            mState = STATE_NO_MORE;
            mFooterProvider.setFooterNormal();
        } else {
            mState = STATE_NORMAL;
            mFooterProvider.setFooterNormal();
        }
        onLoadMore(event, mAdapter);
    }

    protected void onError(Event event) {
        mState = STATE_NO_MORE;
        String postType = mPostTypes.get(event.getUUID());
        if (postType.equals(POST_LOAD_MORE)) {
            mFooterProvider.setFooterError(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pageIndex--;
                    loadMore();
                }
            });
        } else if (postType.equals(POST_REFRESH)) {
            mRefreshLayout.setRefreshing(false);
            mFooterProvider.setFooterNormal();
        }
        onError(event, postType);
    }

    public void setRefreshEnable(boolean refreshEnable) {
        this.refreshEnable = refreshEnable;
        mRefreshLayout.setEnabled(refreshEnable);
    }

    public void setLoadMoreEnable(boolean loadMoreEnable) {
        this.loadMoreEnable = loadMoreEnable;
    }

    public void quickToTop() {
        mRecyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    public abstract void initData(HeaderFooterAdapter adapter);

    protected abstract void setAdapterRegister(Context context, RecyclerView recyclerView,
                                               HeaderFooterAdapter adapter);

    @NonNull
    protected abstract RecyclerView.LayoutManager getRecyclerViewLayoutManager();

    @NonNull
    protected abstract String request(int offset, int limit);

    protected abstract void onRefresh(Event event, HeaderFooterAdapter adapter);

    protected abstract void onLoadMore(Event event, HeaderFooterAdapter adapter);

    protected abstract void onError(Event event, String postType);
}
