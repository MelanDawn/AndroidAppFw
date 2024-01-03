package com.zs.androidappfw.ui.advanced;

import android.os.Bundle;
import android.view.MotionEvent;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;
import com.zs.androidappfw.utils.LUtil;

public class DispatchTouchEventAct extends BaseTitleActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_dispatch_touch_event);
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_dispatch_touch_event;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LUtil.d(mTag, "dispatchTouchEvent start, " + ev.toString());
        boolean result = super.dispatchTouchEvent(ev);
        LUtil.d(mTag, "dispatchTouchEvent end: " + result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        LUtil.d(mTag, "onTouchEvent start, " + ev.toString());
        boolean result = super.onTouchEvent(ev);
        LUtil.d(mTag, "onTouchEvent end: " + result);
        return result;
    }
}





