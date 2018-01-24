package com.gcssloop.diycode_sdk.api.sites.api;

import android.content.Context;
import android.support.annotation.NonNull;

import com.gcssloop.diycode_sdk.api.base.callback.BaseCallback;
import com.gcssloop.diycode_sdk.api.base.impl.BaseImpl;
import com.gcssloop.diycode_sdk.api.sites.event.GetSitesEvent;
import com.gcssloop.diycode_sdk.utils.UUIDGenerator;

/**
 * Created by zhangyuanyuan on 2017/12/13.
 */

public class SitesImpl extends BaseImpl<SitesService> implements SitesAPI {

    public SitesImpl(@NonNull Context context) {
        super(context);
    }

    @Override
    public String getSites() {
        String uuid = UUIDGenerator.getUUID();
        mService.getSites().enqueue(new BaseCallback<>(new GetSitesEvent(uuid)));
        return uuid;
    }
}
