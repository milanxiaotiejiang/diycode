package com.gcssloop.diycode.activity;

import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;

import com.gcssloop.diycode.R;
import com.gcssloop.diycode.base.app.BaseActivity;
import com.gcssloop.diycode.base.app.ViewHolder;
import com.gcssloop.diycode.utils.IntentUtil;
import com.gcssloop.diycode_sdk.api.login.event.LoginEvent;
import com.seabreeze.log.Print;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by zhangyuanyuan on 2017/12/12.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    EditText mUsername;
    EditText mPassword;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews(ViewHolder holder, View root) {
        mUsername = holder.get(R.id.username);
        mPassword = holder.get(R.id.password);

        holder.setOnClickListener(this, R.id.login, R.id.sign_up);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                String name = mUsername.getText().toString();
                String pswd = mPassword.getText().toString();
                if (name.isEmpty() || pswd.isEmpty()) {
                    toastShort("Email/用户名或密码不能为空");
                    return;
                }
                mDiycode.login(name, pswd);
                break;
            case R.id.sign_up:
                IntentUtil.openUrl(this, "https://www.diycode.cc/account/sign_up");
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerKeyboardListener();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterKeyboardListener();
    }

    private void registerKeyboardListener() {
        final View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (isKeyboardShown(rootView)) {
                    Print.e("软键盘弹起");
                    getViewHolder().get(R.id.span1).setVisibility(View.GONE);
                    getViewHolder().get(R.id.span2).setVisibility(View.GONE);
                } else {
                    Print.e("软键盘未弹起");
                    getViewHolder().get(R.id.span1).setVisibility(View.INVISIBLE);
                    getViewHolder().get(R.id.span2).setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void unRegisterKeyboardListener() {
        final View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(null);
    }

    private boolean isKeyboardShown(View rootView) {
        int softKeyboardHeight = 100;
        Rect r = new Rect();
        rootView.getWindowVisibleDisplayFrame(r);
        DisplayMetrics dm = rootView.getResources().getDisplayMetrics();
        int heightDiff = rootView.getBottom() - r.bottom;
        return heightDiff > softKeyboardHeight * dm.density;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogin(LoginEvent event) {
        if (event.isOk()) {
            toastShort("登录成功");
            mDiycode.getMe();   // 获取个人信息，交给 MainActivity 处理
            finish();
        } else {
            String msg = "请重试";
            switch (event.getCode()) {
                case -1:
                    msg = "请检查网络连接";
                    break;
                case 400:
                case 401:
                    msg = "请检查用户名和密码是否正确";
                    break;
            }
            toastShort("登录失败：" + msg);
        }
    }

}
