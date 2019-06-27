package com.zs.androidappfw.ui.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.zs.androidappfw.utils.LUtil;

public class CustomizedView extends View {

    private static final String TAG = "CustomizedView";

    public CustomizedView(Context context) {
        super(context);
    }

    public CustomizedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomizedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomizedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        LUtil.d(TAG, "onLayout start");
        super.onLayout(changed, l, t, r, b);
        LUtil.d(TAG, "onLayout end");
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LUtil.d(TAG, "dispatchTouchEvent start, " + ev.toString());
        boolean result = super.dispatchTouchEvent(ev);
        LUtil.d(TAG, "dispatchTouchEvent end: " + result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        LUtil.d(TAG, "onTouchEvent start, " + ev.toString());
        boolean result = super.onTouchEvent(ev);
        LUtil.d(TAG, "onTouchEvent end: " + result);
        return true;
    }
}