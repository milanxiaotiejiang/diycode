package com.gcssloop.diycode_sdk.api.sites.event;

import android.support.annotation.NonNull;

import com.gcssloop.diycode_sdk.api.base.event.BaseEvent;
import com.gcssloop.diycode_sdk.api.sites.bean.Sites;

import java.util.List;

/**
 * Created by zhangyuanyuan on 2017/12/13.
 */

public class GetSitesEvent extends BaseEvent<List<Sites>> {
    public GetSitesEvent(@NonNull String uuid) {
        super(uuid);
    }

    public GetSitesEvent(@NonNull String uuid, @NonNull Integer code, @NonNull List<Sites> sites) {
        super(uuid, code, sites);
    }
}
