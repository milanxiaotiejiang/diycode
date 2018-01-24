package com.gcssloop.diycode.activity;

import android.view.View;

import com.gcssloop.diycode.R;
import com.gcssloop.diycode.base.app.BaseActivity;
import com.gcssloop.diycode.base.app.ViewHolder;
import com.gcssloop.diycode.utils.AppUtil;
import com.gcssloop.diycode.utils.Config;
import com.gcssloop.diycode.utils.DataCleanManager;
import com.gcssloop.diycode.utils.FileUtil;
import com.gcssloop.diycode.utils.IntentUtil;

import java.io.File;

/**
 * Created by zhangyuanyuan on 2017/12/14.
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private Config mConfig;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initViews(ViewHolder holder, View root) {
        setTitle("设置");
        mConfig = Config.getSingleInstance();
        showCacheSize(holder);

        String versionName = AppUtil.getVersionName(this);
        holder.setText(R.id.app_version, versionName);
        if (mDiycode.isLogin()) {
            holder.get(R.id.user).setVisibility(View.VISIBLE);
        } else {
            holder.get(R.id.user).setVisibility(View.GONE);
        }

        holder.setOnClickListener(this, R.id.clear_cache, R.id.logout, R.id.about, R.id.contribute);

    }

    private void showCacheSize(ViewHolder holder) {
        try {
            File cacheDir = new File(FileUtil.getExternalCacheDir(this));
            String cacheSize = DataCleanManager.getCacheSize(cacheDir);
            if (!cacheSize.isEmpty()) {
                holder.setText(R.id.cache_size, cacheSize);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logout:
                if (!mDiycode.isLogin())
                    return;
                mDiycode.logout();
                toastShort("退出成功");
                break;
            case R.id.clear_cache:
                DataCleanManager.deleteFolderFile(FileUtil.getExternalCacheDir(this), false);
                showCacheSize(getViewHolder());
                toastShort("清除缓存成功");
                break;
            case R.id.about:
                openActivity(AboutActivity.class);
                break;
            case R.id.contribute:
                IntentUtil.openAlipay(this);
                break;
        }
    }

}
