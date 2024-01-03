package com.zs.androidappfw.ui.view.viewgroup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;


// Created by zhangs on 2019/3/9.

public class TabHostAct extends BaseTitleActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_tab_host);
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_vg_tab_host;
    }

    public void toTabWidget(View view) {
        startActivity(new Intent(TabHostAct.this, TabWidgetAct.class));
    }
}
