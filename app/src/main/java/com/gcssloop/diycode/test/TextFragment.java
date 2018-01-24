package com.gcssloop.diycode.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

/**
 * Created by zhangyuanyuan on 2017/12/12.
 */

public class TextFragment  extends Fragment {

    private static final String TYPE = "type";

    public static TextFragment newInstance(@NonNull String type) {
        Bundle args = new Bundle();
        args.putString(TYPE, type);
        TextFragment fragment = new TextFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
