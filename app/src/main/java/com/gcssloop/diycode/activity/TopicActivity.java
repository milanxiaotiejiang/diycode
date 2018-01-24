package com.gcssloop.diycode.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.gcssloop.diycode.R;
import com.gcssloop.diycode.base.app.BaseActivity;
import com.gcssloop.diycode.base.app.ViewHolder;
import com.gcssloop.diycode.fragment.NodeTopicListFragment;

/**
 * Created by zhangyuanyuan on 2017/12/14.
 */

public class TopicActivity extends BaseActivity {

    private static String Key_Node_ID = "Key_Node_ID";
    private static String Key_Node_Name = "Key_Node_Name";

    public static void newInstance(Context context, int nodeId, String nodeName) {
        Intent intent = new Intent(context, TopicActivity.class);
        intent.putExtra(Key_Node_ID, nodeId);
        intent.putExtra(Key_Node_Name, nodeName);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment;
    }

    @Override
    protected void initViews(ViewHolder holder, View root) {
        Intent intent = getIntent();
        int NodeId = intent.getIntExtra(Key_Node_ID, 0);
        String NodeName = intent.getStringExtra(Key_Node_Name);
        setTitle(NodeName);

        NodeTopicListFragment fragment = NodeTopicListFragment.newInstance(NodeId);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment, fragment);
        transaction.commit();
    }
}
