package com.zs.androidappfw.base;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.ui.lifecycle.ActivitySingleInstanceAct;
import com.zs.androidappfw.ui.lifecycle.ActivitySingleTaskAct;
import com.zs.androidappfw.ui.lifecycle.ActivitySingleTaskAffinityAct;
import com.zs.androidappfw.ui.lifecycle.ActivitySingleTopAct;
import com.zs.androidappfw.ui.lifecycle.ActivityStandardAct;
import com.zs.androidappfw.ui.lifecycle.ActivityStandardConfigChangesAct;
import com.zs.androidappfw.utils.LUtil;

import java.util.List;

public abstract class BaseLifecycleActivity extends BaseTitleActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_activity_lifecycle);
        LUtil.i(mTag);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LUtil.i(mTag);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LUtil.i(mTag);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LUtil.i(mTag);
    }

    @Override
    protected void onStop() {
        super.onStop();
        LUtil.i(mTag);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LUtil.i(mTag);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LUtil.i(mTag);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        LUtil.i(mTag, outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        LUtil.i(mTag, savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LUtil.i(mTag, newConfig);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LUtil.i(mTag, intent.toString());
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        LUtil.i(mTag);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        LUtil.i(mTag);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LUtil.i(mTag, "requestCode=", requestCode, "resultCode=", resultCode);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
        LUtil.i(mTag);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        LUtil.i(mTag);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        LUtil.i(mTag);
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        LUtil.i(mTag);
    }

    @Override
    protected void onApplyThemeResource(Resources.Theme theme, int resid, boolean first) {
        super.onApplyThemeResource(theme, resid, first);
        LUtil.i(mTag);
    }

    @Override
    protected void onChildTitleChanged(Activity childActivity, CharSequence title) {
        super.onChildTitleChanged(childActivity, title);
        LUtil.i(mTag);
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        LUtil.i(mTag);
    }

    @Nullable
    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        LUtil.i(mTag);
        return super.onWindowStartingActionMode(callback);
    }

    @Nullable
    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int type) {
        LUtil.i(mTag);
        return super.onWindowStartingActionMode(callback, type);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        LUtil.i(mTag);
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode, Configuration newConfig) {
        super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig);
        LUtil.i(mTag);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        LUtil.i(mTag);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
        LUtil.i(mTag);
        return super.onKeyMultiple(keyCode, repeatCount, event);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        LUtil.i(mTag);
        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    public boolean onKeyShortcut(int keyCode, KeyEvent event) {
        LUtil.i(mTag);
        return super.onKeyShortcut(keyCode, event);
    }

    @Override
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> data, Menu menu, int deviceId) {
        LUtil.i(mTag);
        super.onProvideKeyboardShortcuts(data, menu, deviceId);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        LUtil.i(mTag);
        return super.onKeyUp(keyCode, event);
    }

    public void toActivityStandard(View v) {
        LUtil.i(mTag);
        startActivity(new Intent(this, ActivityStandardAct.class));
    }
    public void toActivitySingleTop(View v) {
        LUtil.i(mTag);
        startActivity(new Intent(this, ActivitySingleTopAct.class));
    }
    public void toActivitySingleTask(View v) {
        LUtil.i(mTag);
        startActivity(new Intent(this, ActivitySingleTaskAct.class));
    }
    public void toActivitySingleTaskAffinity(View v) {
        LUtil.i(mTag);
        startActivity(new Intent(this, ActivitySingleTaskAffinityAct.class));
    }
    public void toActivitySingleInstance(View v) {
        LUtil.i(mTag);
        startActivity(new Intent(this, ActivitySingleInstanceAct.class));
    }

    public void toActivityStandardConfigChanges(View v) {
        LUtil.i(mTag);
        startActivity(new Intent(this, ActivityStandardConfigChangesAct.class));
    }
}
