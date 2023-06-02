package com.zs.androidappfw.ui.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import android.view.View;
import android.widget.Chronometer;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseActivity;


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
