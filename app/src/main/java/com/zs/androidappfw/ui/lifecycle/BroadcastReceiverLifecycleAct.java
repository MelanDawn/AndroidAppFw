package com.zs.androidappfw.ui.lifecycle;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;

public class BroadcastReceiverLifecycleAct extends BaseTitleActivity {

    private final BroadcastReceiverDynamic mReceiver = new BroadcastReceiverDynamic();

    private static final String ACTION_LIFECYCLE = "com.zs.androidappfw.BROADCAST_RECEIVER_LIFECYCLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_broadcast_receiver_lifecycle);
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_broadcast_receiver_lifecycle;
    }

    public void registerBroadcastReceiver(View view) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(ACTION_LIFECYCLE);
        registerReceiver(mReceiver, filter);
//        registerReceiver(mReceiver, filter, Context.RECEIVER_NOT_EXPORTED);
    }

    public void unregisterBroadcastReceiver(View view) {
        unregisterReceiver(mReceiver);
    }

    public void sendBroadcast(View view) {
        sendBroadcast(new Intent(ACTION_LIFECYCLE));
    }

    public void sendBroadcastWithComponent(View view) {
        Intent intent = new Intent(ACTION_LIFECYCLE);
        intent.setComponent(new ComponentName(getPackageName(),
                getPackageName() + ".ui.lifecycle.BroadcastReceiverStatic"));
        sendBroadcast(intent);
    }
}
