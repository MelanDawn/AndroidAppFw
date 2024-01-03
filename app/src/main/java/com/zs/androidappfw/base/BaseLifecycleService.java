package com.zs.androidappfw.base;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.zs.androidappfw.utils.LUtil;

import java.io.FileDescriptor;
import java.io.PrintWriter;

public class BaseLifecycleService extends BaseService {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LUtil.i(mTag);
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LUtil.i(mTag);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        LUtil.i(mTag);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LUtil.i(mTag);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LUtil.i(mTag);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LUtil.i(mTag);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        LUtil.i(mTag);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        LUtil.i(mTag);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        LUtil.i(mTag);
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        LUtil.i(mTag);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        LUtil.i(mTag);
    }

    @Override
    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        super.dump(fd, writer, args);
        LUtil.i(mTag);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        LUtil.i(mTag);
    }
}
