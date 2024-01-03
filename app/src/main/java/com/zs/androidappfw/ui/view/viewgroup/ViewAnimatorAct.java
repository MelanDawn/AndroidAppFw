package com.zs.androidappfw.ui.view.viewgroup;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;


// Created by zhangs on 2019/3/10.

public class ViewAnimatorAct extends BaseTitleActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_view_animator);
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_vg_view_animator;
    }
}
