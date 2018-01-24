package com.gcssloop.diycode.fragment.provider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gcssloop.diycode.R;
import com.gcssloop.diycode.fragment.bean.SiteItem;
import com.gcssloop.diycode.utils.IntentUtil;
import com.gcssloop.recyclerview.adapter.base.RecyclerViewHolder;
import com.gcssloop.recyclerview.adapter.multitype.BaseViewProvider;

/**
 * Created by zhangyuanyuan on 2017/12/13.
 */

public class SiteProvider extends BaseViewProvider<SiteItem> {

    private Context mContext;

    public SiteProvider(@NonNull Context context) {
        super(context, R.layout.item_site);
        mContext = context;
    }

    @Override
    public void onBindView(RecyclerViewHolder holder, final SiteItem bean) {
        if (bean.getName().isEmpty()) return;
        holder.setText(R.id.name, bean.getName());
        ImageView icon = holder.get(R.id.icon);
        Glide.with(mContext).load(bean.getAvatar_url()).into(icon);

        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtil.openUrl(mContext, bean.getUrl());
            }
        }, R.id.item);
    }
}
