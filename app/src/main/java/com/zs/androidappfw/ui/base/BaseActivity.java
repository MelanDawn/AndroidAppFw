package com.zs.androidappfw.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cm.umeng.Push;
import cm.umeng.Statistics;


// Created by zhangs on 2018/10/29.

public abstract class BaseActivity extends AppCompatActivity {

    protected final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Push.onAppStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Statistics.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Statistics.onPause(this);
    }
}
