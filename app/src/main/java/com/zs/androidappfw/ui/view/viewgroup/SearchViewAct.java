package com.zs.androidappfw.ui.view.viewgroup;

// Created by zhangs on 2019/2/28.

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;

public class SearchViewAct extends BaseTitleActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_search_view);
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_vg_search_view;
    }
}
