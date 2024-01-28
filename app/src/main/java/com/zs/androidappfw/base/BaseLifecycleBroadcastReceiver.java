package com.zs.androidappfw.base;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.zs.androidappfw.utils.LUtil;

public abstract class BaseLifecycleBroadcastReceiver extends BaseBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        LUtil.i(mTag, "Received: " + intent.getAction());
    }

    @Override
    public IBinder peekService(Context myContext, Intent service) {
        LUtil.i(mTag);
        return super.peekService(myContext, service);
    }
}
