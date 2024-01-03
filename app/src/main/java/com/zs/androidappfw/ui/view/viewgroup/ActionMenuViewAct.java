package com.zs.androidappfw.ui.view.viewgroup;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;


// Created by zhangs on 2019/2/28.

public class ActionMenuViewAct extends BaseTitleActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_action_menu_view);
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_vg_action_menu_view;
    }
}
