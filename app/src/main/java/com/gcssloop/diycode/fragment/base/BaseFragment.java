package com.gcssloop.diycode.fragment.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gcssloop.diycode.base.app.ViewHolder;
import com.gcssloop.diycode.utils.Config;
import com.gcssloop.diycode.utils.DataCache;
import com.gcssloop.diycode_sdk.api.Diycode;

/**
 * Created by zhangyuanyuan on 2017/12/12.
 */

public abstract class BaseFragment extends Fragment{

    private ViewHolder mViewHolder;

    protected Config mConfig;
    protected Diycode mDiycode;
    protected DataCache mDataCache;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mConfig = Config.getSingleInstance();
        mDiycode = Diycode.getSingleInstance();
        mDataCache = new DataCache(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewHolder = new ViewHolder(inflater, container, getLayoutId());
        return mViewHolder.getRootView();
    }

    @LayoutRes
    protected abstract int getLayoutId();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews(mViewHolder, mViewHolder.getRootView());
    }

    protected abstract void initViews(ViewHolder holder, View root);

    protected void toast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    public ViewHolder getViewHolder() {
        return mViewHolder;
    }
}
