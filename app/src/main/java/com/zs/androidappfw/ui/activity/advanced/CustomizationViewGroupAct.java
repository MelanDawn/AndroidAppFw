package com.zs.androidappfw.ui.activity.advanced;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ScrollView;

import com.zs.androidappfw.R;
import com.zs.androidappfw.ui.base.BaseActivity;
import com.zs.androidappfw.utils.LUtil;

public class CustomizationViewGroupAct extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_customization_view_group);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        LUtil.d(TAG, "onAttachedToWindow");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        LUtil.d(TAG, "onWindowFocusChanged");
        View view = getWindow().getDecorView();
        ViewParent viewParent = view.getParent();
        while(viewParent != null) {
            LUtil.d(TAG, "parent=" + viewParent.getParent() + " v=" + viewParent.toString());
            viewParent = viewParent.getParent();
        }

        printView(view);
    }

    private void printView(View view) {
        ViewGroup viewGroup = null;
        if (!(view instanceof ViewGroup)) {
            LUtil.d(TAG, "w=" + view.getWidth() + " h=" + view.getHeight() + " parent=" + view.getParent() + " v=" + view.toString());
            return;
        }
        viewGroup = (ViewGroup) view;
        LUtil.d(TAG,"w=" + view.getWidth() + " h=" + view.getHeight() + " parent=" + viewGroup.getParent() + " v=" + viewGroup.toString());
        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View v = viewGroup.getChildAt(i);
            printView(v);
        }
    }
}
