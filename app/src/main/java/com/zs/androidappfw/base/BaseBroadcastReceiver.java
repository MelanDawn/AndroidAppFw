package com.zs.androidappfw.base;

import android.content.BroadcastReceiver;

public abstract class BaseBroadcastReceiver extends BroadcastReceiver {
    protected final String mTag = this.getClass().getSimpleName();
}
