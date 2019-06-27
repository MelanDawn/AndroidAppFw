package com.zs.androidappfw.ui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.zs.androidappfw.utils.LUtil;

public class ViewGroupInner extends LinearLayout {

    private static final String TAG = "ViewGroupInner";

    public ViewGroupInner(Context context) {
        this(context, null);
    }

    public ViewGroupInner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewGroupInner(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public ViewGroupInner(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LUtil.d(TAG, "onInterceptTouchEvent start, " + ev.toString());
        boolean result = super.onInterceptTouchEvent(ev);
        LUtil.d(TAG, "onInterceptTouchEvent end: " + result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        LUtil.d(TAG, "onTouchEvent start, " + ev.toString());
        boolean result = super.onTouchEvent(ev);
        LUtil.d(TAG, "onTouchEvent end: " + result);
        return result;
    }
}
