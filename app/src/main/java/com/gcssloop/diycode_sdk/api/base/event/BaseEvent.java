package com.gcssloop.diycode_sdk.api.base.event;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by zhangyuanyuan on 2017/12/11.
 */

public class BaseEvent<T> {

    protected String uuid = "";
    protected boolean ok = false;
    protected Integer code = -1;
    protected T t;

    public BaseEvent(@NonNull String uuid) {
        this.ok = false;
        this.uuid = uuid;
    }

    public BaseEvent(@NonNull String uuid, @NonNull Integer code, @NonNull T t) {
        this.ok = null != t;
        this.uuid = uuid;
        this.code = code;
        this.t = t;
    }

    public BaseEvent setEvent(@NonNull Integer code, @Nullable T t) {
        this.ok = null != t;
        this.code = code;
        this.t = t;
        return this;
    }

    public boolean isOk() {
        return ok;
    }

    public T getBean() {
        return t;
    }

    public String getUUID() {
        return uuid;
    }

    public Integer getCode() {
        return code;
    }

    public String getCodeDescribe() {
        switch (code) {
            case -1:
                return "可能是网络未连接，或者数据转化失败";
            case 200:
            case 201:
                return "请求成功，或执行成功。";
            case 400:
                return "参数不符合 API 的要求、或者数据格式验证没有通过";
            case 401:
                return "用户认证失败，或缺少认证信息，比如 access_token 过期，或没传，可以尝试用 refresh_token 方式获得新的 access_token";
            case 403:
                return "当前用户对资源没有操作权限";
            case 404:
                return "资源不存在";
            case 500:
                return "服务器异常";
            case 402:
                return "用户尚未登录";
            default:
                return "未知异常(" + code + ")";
        }
    }
}
