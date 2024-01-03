package com.zs.androidappfw.ui.view;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;


// Created by zhangs on 2019/2/22.

public class ImageViewAct extends BaseTitleActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_iv);
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_view_imageview;
    }
}
