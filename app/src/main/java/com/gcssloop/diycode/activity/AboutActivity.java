package com.gcssloop.diycode.activity;

import android.view.View;

import com.gcssloop.diycode.R;
import com.gcssloop.diycode.base.app.BaseActivity;
import com.gcssloop.diycode.base.app.ViewHolder;
import com.gcssloop.diycode.utils.IntentUtil;

/**
 * Created by zhangyuanyuan on 2017/12/14.
 */

public class AboutActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initViews(ViewHolder holder, View root) {
        setTitle("关于");
        holder.setOnClickListener(this, R.id.feed_back, R.id.github, R.id.contribute);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.feed_back:
                IntentUtil.openUrl(this,"https://github.com/GcsSloop/diycode/issues/1");
                break;
            case R.id.github:
                IntentUtil.openUrl(this,"https://github.com/GcsSloop");
                break;
            case R.id.contribute:
                IntentUtil.openAlipay(this);
                break;
        }
    }

}
