package com.gcssloop.diycode.base.webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.gcssloop.diycode.DiskImageCache;
import com.gcssloop.diycode.utils.IntentUtil;
import com.gcssloop.diycode_sdk.log.Logger;
import com.seabreeze.log.Print;

import java.io.FileInputStream;

import static com.gcssloop.diycode.utils.UrlUtil.getMimeType;
import static com.gcssloop.diycode.utils.UrlUtil.isGifSuffix;
import static com.gcssloop.diycode.utils.UrlUtil.isImageSuffix;

/**
 * Created by zhangyuanyuan on 2017/12/13.
 */

public class GcsMarkdownViewClient extends WebViewClient {

    public static final String URL = "url";
    private Context mContext;
    private DiskImageCache mCache;


    public GcsMarkdownViewClient(@NonNull Context context) {
        mContext = context;
        mCache = new DiskImageCache(context);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        addImageClickListener(view);
        super.onPageFinished(view, url);
    }

    @SuppressLint({"JavascriptInterface", "AddJavascriptInterface"})
    private <Web extends WebView> void addImageClickListener(Web webView) {
        // 遍历所有签过名的图片，并添加onclick函数，函数的功能是在图片点击的时候调用本地java接口并传递url过去
        webView.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByClassName(\"gcs-img-sign\"); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{" +
                "  window.listener.collectImage(objs[i].src); " +
                "  objs[i].onclick=function()  " +
                "  {  " +
                "    window.listener.onImageClicked(this.src);  " +
                "  }  " +
                "}" +
                "})()");
    }

    //--- html 链接打开方式 -----------------------------------------------------------------------


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        IntentUtil.openUrl(mContext, request.getUrl().toString());
        return true;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        IntentUtil.openUrl(mContext, url);
        return true;
    }

    @Override
    public void onLoadResource(WebView view, final String url) {
        Print.e(" //在这里可以监听到要加载的资源URL   " + url);
        // 是图片就缓存
        if (isImageSuffix(url)) {
            Glide.with(mContext).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    mCache.saveBitmap(url, resource);
                }
            });
        } else if (isGifSuffix(url)) {
            Glide.with(mContext).load(url).asGif().into(new SimpleTarget<GifDrawable>() {
                @Override
                public void onResourceReady(GifDrawable resource, GlideAnimation<? super GifDrawable> glideAnimation) {
                    mCache.saveBytes(url, resource.getData());
                }
            });
        }
    }
//--- 请求资源 -------------------------------------------------------------------------------

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        String url = request.getUrl().toString();
        WebResourceResponse response = getWebResourceResponse(url);
        if (response != null) return response;
        return super.shouldInterceptRequest(view, request);
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        WebResourceResponse response = getWebResourceResponse(url);
        if (response != null) return response;
        return super.shouldInterceptRequest(view, url);
    }

    /**
     * 获取本地资源
     */
    @Nullable
    private WebResourceResponse getWebResourceResponse(String url) {
        try {
            // 如果是图片且本地有缓存
            if (isImageSuffix(url) || isGifSuffix(url)) {
                FileInputStream inputStream = mCache.getStream(url);
                if (null != inputStream) {
                    return new WebResourceResponse(getMimeType(url), "base64", inputStream);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
