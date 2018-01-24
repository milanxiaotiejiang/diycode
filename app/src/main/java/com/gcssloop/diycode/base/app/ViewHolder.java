package com.gcssloop.diycode.base.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.seabreeze.log.Print;

/**
 * Created by zhangyuanyuan on 2017/12/11.
 */

public class ViewHolder {

    private SparseArray<View> mViews;
    private View mRootView;

    public ViewHolder(LayoutInflater inflater, ViewGroup parent, int layoutId) {
        this.mViews = new SparseArray<View>();
        mRootView = inflater.inflate(layoutId, parent, false);
    }

    public <T extends View> T get(int resId) {
        View view = mViews.get(resId);
        if (view == null) {
            view = mRootView.findViewById(resId);
            mViews.put(resId, view);
        }
        if (view == null) {
            Print.e("view == null");
        }
        return (T) view;
    }

    public View getRootView() {
        return mRootView;
    }

    public boolean setText(CharSequence text, @Nullable int res_id) {
        try {

            TextView textView = get(res_id);
            textView.setText(text);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setText(@NonNull int res_id, CharSequence text) {
        return setText(text, res_id);
    }


    public void loadImage(Context context, String url, int res_id) {
        ImageView imageView = get(res_id);
        String url2 = url;
        if (url.contains("diycode"))
            url2 = url.replace("large_avatar", "avatar");
        Glide.with(context).load(url2).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
    }

    public void setOnClickListener(View.OnClickListener l, int... ids) {
        if (ids == null) {
            return;
        }
        for (int id : ids) {
            get(id).setOnClickListener(l);
        }
    }
}
