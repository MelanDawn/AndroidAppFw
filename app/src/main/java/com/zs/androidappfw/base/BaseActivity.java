package com.zs.androidappfw.base;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;

import cm.umeng.Push;
import cm.umeng.Statistics;


// Created by zhangs on 2018/10/29.

public abstract class BaseActivity extends Activity {

    protected final String mTag = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Push.onAppStart();
    }

    @Override
    protected void onStart() {
        super.onStart();
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

    protected void registerReceiverDelegate(BroadcastReceiver receiver, IntentFilter filter) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(receiver, filter, Context.RECEIVER_EXPORTED);
        } else {
            registerReceiver(receiver, filter);
        }
    }
}
