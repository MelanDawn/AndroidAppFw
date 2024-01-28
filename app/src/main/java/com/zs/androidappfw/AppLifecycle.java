package com.zs.androidappfw;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zs.androidappfw.utils.BundleUtil;
import com.zs.androidappfw.utils.LUtil;

import cm.umeng.Umeng;

// Created by zhangs on 2018/8/1.

public class AppLifecycle extends Application {
    private final String mTag = this.getClass().getSimpleName();

    public static Context appContext;

    public AppLifecycle() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        LUtil.i(mTag);
        appContext = getApplicationContext();
        init();
    }

    private void init() {
        // init umeng
        Umeng.init(appContext);

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            private static final String TAG = "AppLifecycle.Callback";

            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                LUtil.i(TAG, activity.getClass().getSimpleName(), BundleUtil.getContent(savedInstanceState));
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
                LUtil.i(TAG, activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                LUtil.i(TAG, activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {
                LUtil.i(TAG, activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
                LUtil.i(TAG, activity.getClass().getSimpleName());
            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
                LUtil.i(TAG, activity.getClass().getSimpleName(), BundleUtil.getContent(outState));
            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                LUtil.i(TAG, activity.getClass().getSimpleName());
            }
        });
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        LUtil.i(mTag);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
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
    public void registerComponentCallbacks(ComponentCallbacks callback) {
        super.registerComponentCallbacks(callback);
        LUtil.i(mTag);
    }

    @Override
    public void unregisterComponentCallbacks(ComponentCallbacks callback) {
        super.unregisterComponentCallbacks(callback);
        LUtil.i(mTag);
    }

    @Override
    public void registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
        super.registerActivityLifecycleCallbacks(callback);
        LUtil.i(mTag);
    }

    @Override
    public void unregisterActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
        super.unregisterActivityLifecycleCallbacks(callback);
        LUtil.i(mTag);
    }

    @Override
    public void registerOnProvideAssistDataListener(OnProvideAssistDataListener callback) {
        super.registerOnProvideAssistDataListener(callback);
        LUtil.i(mTag);
    }

    @Override
    public void unregisterOnProvideAssistDataListener(OnProvideAssistDataListener callback) {
        super.unregisterOnProvideAssistDataListener(callback);
        LUtil.i(mTag);
    }
}
