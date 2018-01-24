package com.gcssloop.diycode_sdk.api.base.impl;

import android.content.Context;
import android.support.annotation.NonNull;

import com.gcssloop.diycode_sdk.api.base.bean.OAuth;
import com.gcssloop.diycode_sdk.api.login.bean.Token;
import com.gcssloop.diycode_sdk.utils.CacheUtil;
import com.gcssloop.diycode_sdk.utils.Constant;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.concurrent.TimeUnit;

import javax.crypto.spec.OAEPParameterSpec;

import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by zhangyuanyuan on 2017/12/11.
 */

public class BaseImpl<Service> {

    protected CacheUtil mCacheUtil;
    private static Retrofit mRetrofit;
    protected Service mService;
    private Class<Service> serviceClass;

    public BaseImpl(@NonNull Context context) {
        mCacheUtil = new CacheUtil(context.getApplicationContext());
        initRetrofit();
        this.mService = mRetrofit.create(getServiceClass());
    }

    private void initRetrofit() {

        if (null != mRetrofit)
            return;

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Interceptor mTokenInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                if (null == mCacheUtil.getToken() || alreadyHasAuthorizationHeader(originalRequest)) {
                    return chain.proceed(originalRequest);
                }
                String token = OAuth.TOKEN_PREFIX + mCacheUtil.getToken().getAccess_token();
                Request authorised = originalRequest.newBuilder()
                        .header(OAuth.KEY_TOKEN, token)
                        .build();
                return chain.proceed(authorised);
            }
        };

        Authenticator mAuthenticator = new Authenticator() {
            @Override
            public Request authenticate(Route route, Response response) throws IOException {
                TokenService tokenService = mRetrofit.create(TokenService.class);
                String accessToken = "";
                if (null != mCacheUtil.getToken()) {
                    Call<Token> call = tokenService.refreshToken(OAuth.client_id, OAuth.client_secret,
                            OAuth.GRANT_TYPE_REFRESH, mCacheUtil.getToken().getRefresh_token());
                    retrofit2.Response<Token> tokenResponse = call.execute();
                    Token token = tokenResponse.body();
                    if (null != token) {
                        mCacheUtil.saveToken(token);
                        accessToken = token.getAccess_token();
                    }
                }
                return response.request().newBuilder()
                        .addHeader(OAuth.KEY_TOKEN, OAuth.TOKEN_PREFIX + accessToken)
                        .build();
            }
        };

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .addNetworkInterceptor(mTokenInterceptor)
                .authenticator(mAuthenticator)
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    private boolean alreadyHasAuthorizationHeader(Request originalRequest) {
        if (OAuth.getRemoveAutoTokenState()) {
            return true;
        }
        String token = originalRequest.header(OAuth.KEY_TOKEN);
        return !(null == token || token.isEmpty() || originalRequest.url().toString().contains(Constant.OAUTH_URL));
    }

    public Class<Service> getServiceClass() {
        return (Class<Service>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void setServiceClass(Class<Service> serviceClass) {
        this.serviceClass = serviceClass;
    }

    interface TokenService {
        @POST(Constant.OAUTH_URL)
        @FormUrlEncoded
        Call<Token> refreshToken(@Field("client_id") String client_id, @Field("client_secret") String client_secret,
                                 @Field("grant_type") String frant_type, @Field("refresh_token") String refresh_token);
    }
}
