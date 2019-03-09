package com.zs.androidappfw.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.zs.androidappfw.R;
import com.zs.androidappfw.ui.base.BaseActivity;


// Created by zhangs on 2019/3/9.

public class TabHostAct extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_tab_host);
    }

    public void toTabWidget(View view) {
        startActivity(new Intent(TabHostAct.this, TabWidgetAct.class));
    }
}
