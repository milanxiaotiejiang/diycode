package com.gcssloop.diycode.fragment.provider;

import android.content.Context;
import android.support.annotation.NonNull;

import com.gcssloop.diycode.R;
import com.gcssloop.diycode.fragment.bean.SitesItem;
import com.gcssloop.recyclerview.adapter.base.RecyclerViewHolder;
import com.gcssloop.recyclerview.adapter.multitype.BaseViewProvider;

/**
 * Created by zhangyuanyuan on 2017/12/13.
 */

public class SitesProvider extends BaseViewProvider<SitesItem> {

    public SitesProvider(@NonNull Context context) {
        super(context, R.layout.item_sites);
    }

    /**
     * 在绑定数据时调用，需要用户自己处理
     *
     * @param holder ViewHolder
     * @param bean   数据
     */
    @Override
    public void onBindView(RecyclerViewHolder holder, SitesItem bean) {
        holder.setText(R.id.name, bean.getName());
    }
}
