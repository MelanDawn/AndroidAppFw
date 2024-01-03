package com.zs.androidappfw.ui.lifecycle;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;
import com.zs.androidappfw.utils.LUtil;

public class ServiceLifecycleAct extends BaseTitleActivity {

    private final ServiceConnection mServiceConnection = new ServiceConnectionImpl("BondService");
    private final ServiceConnection mStartedServiceConnection = new ServiceConnectionImpl("StartedService");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_service_lifecycle);
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_service_lifecycle;
    }

    public void bindService(View view) {
        bindService(new Intent(this, ServiceByBind.class), mServiceConnection,
                Context.BIND_AUTO_CREATE);
    }

    public void unbindService(View view) {
        unbindService(mServiceConnection);
    }

    public void startService(View view) {
        startService(new Intent(this, ServiceByStartService.class));
    }

    public void stopService(View view) {
        stopService(new Intent(this, ServiceByStartService.class));
    }

    public void bindStartedService(View view) {
        bindService(new Intent(this, ServiceByStartService.class), mStartedServiceConnection,
                Context.BIND_AUTO_CREATE);
    }

    public void unbindStartedService(View view) {
        unbindService(mStartedServiceConnection);
    }

    public void startBondService(View view) {
        startService(new Intent(this, ServiceByBind.class));
    }

    public void stopBondService(View view) {
        stopService(new Intent(this, ServiceByBind.class));
    }

    static class ServiceConnectionImpl implements ServiceConnection {
        private final String TAG;

        ServiceConnectionImpl(String tag) {
            TAG = tag;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LUtil.i(TAG, name, service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LUtil.i(TAG, name);
        }
    }
}
