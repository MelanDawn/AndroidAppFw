package com.zs.androidappfw.ui.base;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zs.androidappfw.utils.LUtil;

public abstract class BaseLifecycleActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LUtil.d(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LUtil.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LUtil.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LUtil.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LUtil.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LUtil.d(TAG, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LUtil.d(TAG, "onRestart");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LUtil.d(TAG, "onSaveInstanceState" + " 参数："  +  outState.toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        LUtil.d(TAG, "onRestoreInstanceState" + " 参数："  +  savedInstanceState.toString());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LUtil.d(TAG, "onConfigurationChanged" + " 参数："  +  newConfig.toString());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LUtil.d(TAG, "onNewIntent" + " 参数："  +  intent.toString());
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        LUtil.d(TAG, "onAttachedToWindow");
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        LUtil.d(TAG, "onDetachedFromWindow");
    }
}
