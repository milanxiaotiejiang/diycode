package com.gcssloop.recyclerview.adapter.multitype;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.gcssloop.recyclerview.adapter.base.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyuanyuan on 2017/12/12.
 */

public class HeaderFooterAdapter extends RecyclerView.Adapter<RecyclerViewHolder> implements TypePool {

    private MultiTypePool mTypePool;

    private List<Object> mItems = new ArrayList<>();

    private boolean hasHeader = false;
    private boolean hasFooter = false;

    public HeaderFooterAdapter() {
        mTypePool = new MultiTypePool();
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewProvider provider = getProviderByIndex(viewType);
        return provider.onCreateViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        assert mItems != null;
        Object item = mItems.get(position);
        BaseViewProvider provider = getProviderByClass(item.getClass());
        provider.onBindView(holder, item);
    }

    @Override
    public int getItemCount() {
        assert mItems != null;
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        assert mItems != null;
        Object item = mItems.get(position);
        int index = mTypePool.indexof(item.getClass());
        if (index >= 0) {
            return index;
        }
        return mTypePool.indexof(item.getClass());
    }

    @Override
    public void register(@NonNull Class<?> clazz, @NonNull BaseViewProvider provider) {
        mTypePool.register(clazz, provider);
    }

    @Override
    public int indexof(@NonNull Class<?> clazz) {
        return mTypePool.indexof(clazz);
    }

    @Override
    public List<BaseViewProvider> getProviders() {
        return mTypePool.getProviders();
    }

    @Override
    public BaseViewProvider getProviderByIndex(int index) {
        return mTypePool.getProviderByIndex(index);
    }

    @Override
    public <T extends BaseViewProvider> T getProviderByClass(@NonNull Class<?> clazz) {
        return mTypePool.getProviderByClass(clazz);
    }

    public void registerHeader(@NonNull Object object, @NonNull BaseViewProvider provider) {
        if (hasHeader)
            return;
        mTypePool.register(object.getClass(), provider);
        mItems.add(0, object);
        hasHeader = true;
        notifyDataSetChanged();
    }

    public void unRegisterHeader() {
        if (!hasHeader)
            return;
        mItems.remove(0);
        hasHeader = false;
        notifyDataSetChanged();
    }

    public void registerFooter(@NonNull Object object, @NonNull BaseViewProvider provider) {
        if (hasFooter)
            return;
        mTypePool.register(object.getClass(), provider);
        mItems.add(object);
        hasFooter = true;
        notifyDataSetChanged();
    }

    public void unRegisterFooter() {
        if (!hasFooter)
            return;
        mItems.remove(mItems.size() - 1);
        hasFooter = false;
        notifyDataSetChanged();
    }


    public void addDatas(List<?> items) {
        if (hasFooter) {
            mItems.addAll(mItems.size() - 1, items);
        } else {
            mItems.addAll(items);
        }
        notifyDataSetChanged();
    }

    public List<Object> getDatas() {
        int startIndex = 0;
        int endIndex = mItems.size();
        if (hasHeader) {
            startIndex++;
        }
        if (hasFooter) {
            endIndex--;
        }
        return mItems.subList(startIndex, endIndex);
    }

    public void clearDatas() {
        int startIndex = 0;
        int endIndex = mItems.size();
        if (hasHeader) {
            startIndex++;
        }
        if (hasFooter) {
            endIndex--;
        }
        for (int i = endIndex - 1; i >= startIndex; i++) {
            mItems.remove(i);
        }
        notifyDataSetChanged();
    }

    public List<Object> getFullDatas() {
        return mItems;
    }
}
