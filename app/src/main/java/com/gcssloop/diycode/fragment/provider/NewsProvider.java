package com.gcssloop.diycode.fragment.provider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gcssloop.diycode.R;
import com.gcssloop.diycode.activity.UserActivity;
import com.gcssloop.diycode.utils.IntentUtil;
import com.gcssloop.diycode.utils.TimeUtil;
import com.gcssloop.diycode.utils.UrlUtil;
import com.gcssloop.diycode_sdk.api.news.bean.New;
import com.gcssloop.diycode_sdk.api.user.bean.User;
import com.gcssloop.recyclerview.adapter.base.RecyclerViewHolder;
import com.gcssloop.recyclerview.adapter.multitype.BaseViewProvider;

/**
 * Created by zhangyuanyuan on 2017/12/13.
 */

public class NewsProvider extends BaseViewProvider<New> {
    public NewsProvider(@NonNull Context context) {
        super(context, R.layout.item_news);
    }

    @Override
    public void onBindView(RecyclerViewHolder holder, final New bean) {
        final User user = bean.getUser();
        holder.setText(R.id.username, user.getLogin());
        holder.setText(R.id.node_name, bean.getNode_name());
        holder.setText(R.id.time, TimeUtil.computePastTime(bean.getUpdated_at()));
        holder.setText(R.id.title, bean.getTitle());
        holder.setText(R.id.host_name, UrlUtil.getHost(bean.getAddress()));

        // 加载头像
        ImageView imageView = holder.get(R.id.avatar);
        String url = user.getAvatar_url();
        String url2 = url;
        if (url.contains("diycode"))    // 添加判断，防止替换掉其他网站掉图片
            url2 = url.replace("large_avatar", "avatar");
        Glide.with(mContext).load(url2).diskCacheStrategy(DiskCacheStrategy.RESULT).into(imageView);

        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserActivity.newInstance(mContext, user);
            }
        }, R.id.avatar, R.id.username);

        holder.get(R.id.item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtil.openUrl(mContext, bean.getAddress());
            }
        });
    }
}
