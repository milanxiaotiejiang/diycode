package com.gcssloop.recyclerview.adapter.multitype;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyuanyuan on 2017/12/12.
 */

public class MultiTypePool implements TypePool {

    private final List<Class<?>> mContents;
    private final List<BaseViewProvider> mProviders;


    public MultiTypePool() {
        mContents = new ArrayList<>();
        mProviders = new ArrayList<>();
    }


    @Override
    public void register(@NonNull Class<?> clazz, @NonNull BaseViewProvider provider) {
        if (!mContents.contains(clazz)) {
            mContents.add(clazz);
            mProviders.add(provider);
        } else {
            int index = mContents.indexOf(clazz);
            mProviders.set(index, provider);
        }
    }

    @Override
    public int indexof(@NonNull Class<?> clazz) {
        int index = mContents.indexOf(clazz);
        if (index > 0) {
            return index;
        }
        for (int i = 0; i < mContents.size(); i++) {
            if (mContents.get(i).isAssignableFrom(clazz)) {
                return i;
            }
        }
        throw new ProviderNotFoundException(clazz);
    }

    @Override
    public List<BaseViewProvider> getProviders() {
        return mProviders;
    }

    @Override
    public BaseViewProvider getProviderByIndex(int index) {
        return mProviders.get(index);
    }

    @Override
    public <T extends BaseViewProvider> T getProviderByClass(@NonNull Class<?> clazz) {
        return (T) getProviderByIndex(indexof(clazz));
    }

    public class ProviderNotFoundException extends RuntimeException {

        public ProviderNotFoundException(@NonNull Class<?> clazz) {
            super("Do you have registered the provider for {className}.class in the adapter/pool?"
                    .replace("{className}", clazz.getSimpleName()));
        }
    }
}
