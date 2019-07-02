package com.zs.androidappfw.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.zs.androidappfw.R;
import com.zs.androidappfw.utils.LUtil;

public class CustomizedView extends View {

    private static final String TAG = "CustomizedView";

    public CustomizedView(Context context) {
        super(context);
        LUtil.d(TAG, "MusicSwitchView 1 param");
    }

    public CustomizedView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        LUtil.d(TAG, "MusicSwitchView 2 params");
    }

    public CustomizedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
        LUtil.d(TAG, "MusicSwitchView 3 params");
    }

    public CustomizedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        /* 由于 View 的四参数构造函数只能在API 21之后使用，因此这里不调用super四参数构造函数，自己实现*/
        super(context, attrs, defStyleAttr);

        LUtil.d(TAG, "MusicSwitchView 4 params");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        LUtil.d(TAG, "onLayout");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LUtil.d(TAG, "onMeasure");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LUtil.d(TAG, "onDraw");
    }

    @Override
    public void invalidate() {
        super.invalidate();
        LUtil.d(TAG, "invalidate");
    }

    @Override
    public void postInvalidate() {
        super.postInvalidate();
        LUtil.d(TAG, "postInvalidate");
    }

    @Override
    public void requestLayout() {
        super.requestLayout();
        LUtil.d(TAG, "requestLayout");
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
        return result;
    }
}