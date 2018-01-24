package com.gcssloop.diycode.utils;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gcssloop.recyclerview.adapter.singletype.SingleTypeAdapter;

/**
 * Created by zhangyuanyuan on 2017/12/13.
 */

public class RecyclerViewUtil {

    public static <T extends SingleTypeAdapter> void init(Context context, RecyclerView recyclerView, T adapter) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
    }

}
