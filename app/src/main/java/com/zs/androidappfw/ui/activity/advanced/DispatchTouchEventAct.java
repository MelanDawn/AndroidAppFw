package com.zs.androidappfw.ui.activity.advanced;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;

import com.zs.androidappfw.R;
import com.zs.androidappfw.ui.base.BaseActivity;
import com.zs.androidappfw.utils.LUtil;

public class DispatchTouchEventAct extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_dispatch_touch_event);
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





