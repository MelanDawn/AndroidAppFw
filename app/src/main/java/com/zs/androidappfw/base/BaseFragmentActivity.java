package com.zs.androidappfw.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;

import androidx.fragment.app.FragmentActivity;

public class BaseFragmentActivity extends FragmentActivity {

    public static final String KEY_FRAGMENT_CLASS = "KEY_FRAGMENT_CLASS";

    protected final String mTag = this.getClass().getSimpleName();

    protected void registerReceiverDelegate(BroadcastReceiver receiver, IntentFilter filter) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(receiver, filter, Context.RECEIVER_EXPORTED);
        } else {
            registerReceiver(receiver, filter);
        }
    }
}
