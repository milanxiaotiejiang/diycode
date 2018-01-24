package com.gcssloop.diycode.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gcssloop.diycode.R;
import com.gcssloop.diycode.activity.UserActivity;
import com.gcssloop.diycode.base.glide.GlideImageGetter;
import com.gcssloop.diycode.utils.HtmlUtil;
import com.gcssloop.diycode.utils.ImageUtils;
import com.gcssloop.diycode.utils.TimeUtil;
import com.gcssloop.diycode_sdk.api.topic.bean.TopicReply;
import com.gcssloop.diycode_sdk.api.user.bean.User;
import com.gcssloop.recyclerview.adapter.base.RecyclerViewHolder;
import com.gcssloop.recyclerview.adapter.singletype.SingleTypeAdapter;

/**
 * Created by zhangyuanyuan on 2017/12/13.
 */

public class TopicReplyAdapter extends SingleTypeAdapter<TopicReply>{

    private Context mContext;

    public TopicReplyAdapter(@NonNull Context context) {
        super(context, R.layout.item_topic_reply);
        mContext = context;
    }

    @Override
    public void convert(int position, RecyclerViewHolder holder, TopicReply bean) {
        final User user = bean.getUser();
        holder.setText(R.id.username, user.getLogin());
        holder.setText(R.id.time, TimeUtil.computePastTime(bean.getUpdated_at()));

        ImageView avatar = holder.get(R.id.avatar);
        ImageUtils.loadImage(mContext, user.getAvatar_url(), avatar);
        TextView content = holder.get(R.id.content);
        // TODO 评论区代码问题
        content.setText(Html.fromHtml(HtmlUtil.removeP(bean.getBody_html()), new GlideImageGetter(mContext, content), null));

        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, UserActivity.class);
                intent.putExtra(UserActivity.USER, user);
                mContext.startActivity(intent);
            }
        }, R.id.avatar, R.id.username);
    }
}
