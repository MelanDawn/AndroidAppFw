package com.zs.androidappfw;

import android.app.Application;
import android.content.Context;


// Created by zhangs on 2018/8/1.

public class App extends Application {

    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = getApplicationContext();
        init();
    }

    private void init() {

    }
}
