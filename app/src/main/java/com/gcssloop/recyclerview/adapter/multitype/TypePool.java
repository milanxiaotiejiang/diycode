package com.gcssloop.recyclerview.adapter.multitype;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created by zhangyuanyuan on 2017/12/12.
 */

public interface TypePool {

    void register(@NonNull Class<?> clazz, @NonNull BaseViewProvider provider);

    int indexof(@NonNull final Class<?> clazz);

    List<BaseViewProvider> getProviders();

    BaseViewProvider getProviderByIndex(int index);

    <T extends BaseViewProvider> T getProviderByClass(@NonNull final Class<?> clazz);
}
