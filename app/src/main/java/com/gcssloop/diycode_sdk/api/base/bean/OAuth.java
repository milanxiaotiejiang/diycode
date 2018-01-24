package com.gcssloop.diycode_sdk.api.base.bean;

/**
 * Created by zhangyuanyuan on 2017/12/11.
 */

public class OAuth {

    public static final String GRANT_TYPE_LOGIN = "password";             // 密码
    public static final String GRANT_TYPE_REFRESH = "refresh_token";      // 刷新令牌

    public static final String KEY_TOKEN = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    public static String client_id = "";                            // 应用ID
    public static String client_secret = "";                        // 私钥

    private static boolean debug_remove_auto_token = false;

    public static boolean getRemoveAutoTokenState(){
        return debug_remove_auto_token;
    }
}
