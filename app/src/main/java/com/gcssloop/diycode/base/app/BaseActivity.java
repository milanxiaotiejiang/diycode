package com.gcssloop.diycode.base.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.gcssloop.diycode.R;
import com.gcssloop.diycode.hackpatch.IMMLeaks;
import com.gcssloop.diycode_sdk.api.Diycode;

import java.io.Serializable;

/**
 * Created by zhangyuanyuan on 2017/12/11.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected Diycode mDiycode;
    protected ViewHolder mViewHolder;
    private Toast mToast;
    private int layoutId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDiycode = Diycode.getSingleInstance();
        mViewHolder = new ViewHolder(getLayoutInflater(), null, getLayoutId());
        setContentView(mViewHolder.getRootView());
        IMMLeaks.fixFocusedViewLeak(this.getApplication());
        initActionBar(mViewHolder);
        initDatas();
        initViews(mViewHolder, mViewHolder.getRootView());
    }

    @LayoutRes
    protected abstract int getLayoutId();

    private void initActionBar(ViewHolder holder) {
        Toolbar toolbar = holder.get(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    protected void initDatas() {

    }

    protected abstract void initViews(ViewHolder holder, View root);


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public ViewHolder getViewHolder() {
        return mViewHolder;
    }

    public void toastShort(String text) {
        toast(text, Toast.LENGTH_SHORT);
    }

    public void toastLong(String text) {
        toast(text, Toast.LENGTH_LONG);
    }

    private void toast(final String text, final int duration) {
        if (!TextUtils.isEmpty(text)) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (mToast == null) {
                        mToast = Toast.makeText(getApplicationContext(), text, duration);
                    } else {
                        mToast.setText(text);
                        mToast.setDuration(duration);
                    }
                    mToast.show();
                }
            });
        }
    }


    protected void openActivity(Class<?> cls) {
        openActivity(this, cls);
    }


    public static void openActivity(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

    protected <V extends Serializable> void openActivity(Class<?> cls, String key, V value) {
        openActivity(this, cls, key, value);
    }

    public <V extends Serializable> void openActivity(Context context, Class<?> cls, String key, V value) {
        Intent intent = new Intent(context, cls);
        intent.putExtra(key, value);
        context.startActivity(intent);
    }

}
