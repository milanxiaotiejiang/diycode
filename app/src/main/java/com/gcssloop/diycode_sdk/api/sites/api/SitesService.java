package com.gcssloop.diycode_sdk.api.sites.api;

import com.gcssloop.diycode_sdk.api.sites.bean.Sites;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by zhangyuanyuan on 2017/12/13.
 */

public interface SitesService {

    @GET("sites.json")
    Call<List<Sites>> getSites();

}
