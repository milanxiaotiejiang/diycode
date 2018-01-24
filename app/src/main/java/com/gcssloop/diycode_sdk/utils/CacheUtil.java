package com.gcssloop.diycode_sdk.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import com.gcssloop.diycode_sdk.api.login.bean.Token;

/**
 * Created by zhangyuanyuan on 2017/12/11.
 */

public class CacheUtil {

    ACache cache;

    public CacheUtil(Context context){
        cache = ACache.get(context);
    }

    public void saveToken(@NonNull Token token){
        cache.put("token", token);
    }

    public Token getToken() {
        return (Token) cache.getAsObject("token");
    }

    public void clearToken(){
        cache.remove("token");
    }
}
