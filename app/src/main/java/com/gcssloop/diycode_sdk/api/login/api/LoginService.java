package com.gcssloop.diycode_sdk.api.login.api;

import com.gcssloop.diycode_sdk.api.base.bean.State;
import com.gcssloop.diycode_sdk.api.login.bean.Token;
import com.gcssloop.diycode_sdk.utils.Constant;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by zhangyuanyuan on 2017/12/11.
 */

public interface LoginService {

    @POST(Constant.OAUTH_URL)
    @FormUrlEncoded
    Call<Token> getToken(@Field("client_id") String client_id, @Field("client_secret") String client_secret,
                         @Field("grant_type") String grant_type, @Field("username") String username, @Field("password") String password);

    @POST(Constant.OAUTH_URL)
    @FormUrlEncoded
    Call<Token> refreshToken(@Field("client_id") String client_id, @Field("client_secret") String client_secret,
                             @Field("grant_type") String grant_type, @Field("refresh_token") String refresh_token);

    @POST("devices.json")
    @FormUrlEncoded
    Call<State> updateDevices(@Field("platform") String platform, @Field("token") String token);

    @DELETE("devices.json")
    Call<State> deleteDevices(@Field("platform") String platform, @Field("token") String token);
}
