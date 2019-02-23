package com.zs.androidappfw.ui.activity;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Chronometer;

import com.zs.androidappfw.R;
import com.zs.androidappfw.ui.base.BaseActivity;


// Created by zhangs on 2019/2/23.

public class ChronometerAct extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_chronometer);
    }

    public void c1Click(View view) {
        ((Chronometer) view).start();
    }

    public void c3Click(View view) {
        ((Chronometer) view).start();
    }
}
