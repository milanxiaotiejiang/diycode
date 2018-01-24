package com.gcssloop.diycode.base.webview;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;

import com.gcssloop.diycode.base.app.BaseImageActivity;
import com.gcssloop.diycode.utils.UrlUtil;
import com.gcssloop.diycode_sdk.log.Logger;

import java.util.ArrayList;

/**
 * Created by zhangyuanyuan on 2017/12/13.
 */

public class WebImageListener {

    private Context mContext;
    private Class<? extends BaseImageActivity> mImageActivity;
    private ArrayList<String> mImages = new ArrayList<>();

    public WebImageListener(Context context, Class<? extends BaseImageActivity> imageActivity) {
        mContext = context;
        mImageActivity = imageActivity;
    }

    public ArrayList<String> getImages() {
        return mImages;
    }

    @JavascriptInterface
    public void collectImage(final String url) {
        Logger.e("collect:" + url);
        if (UrlUtil.isGifSuffix(url)) {
            return;
        }
        if (!mImages.contains(url))
            mImages.add(url);
    }

    @JavascriptInterface
    public void onImageClicked(String url) {
        Logger.e("clicked:" + url);
        if (mImageActivity != null) {
            Intent intent = new Intent(mContext, mImageActivity);
            intent.putExtra(BaseImageActivity.CURRENT_IMAGE_URL, url);
            if (!UrlUtil.isGifSuffix(url)) {
                intent.putExtra(BaseImageActivity.ALL_IMAGE_URLS, mImages);
            }
            mContext.startActivity(intent);
        }
    }
}
