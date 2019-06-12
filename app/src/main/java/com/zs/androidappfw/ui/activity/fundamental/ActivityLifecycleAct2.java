package com.zs.androidappfw.ui.activity.fundamental;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.zs.androidappfw.R;
import com.zs.androidappfw.ui.base.BaseActivity;
import com.zs.androidappfw.utils.LUtil;

public class ActivityLifecycleAct2 extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_activity_lifecycle2);
        LUtil.d(TAG, "onCreate");
    }

    public void toActivityLifecycle1(View view) {
        startActivity(new Intent(this, ActivityLifecycleAct.class));
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
        LUtil.d(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        LUtil.d(TAG, "onRestoreInstanceState");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LUtil.d(TAG, "onConfigurationChanged");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LUtil.d(TAG, "onNewIntent");
    }
}
