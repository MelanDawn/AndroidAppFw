package com.zs.androidappfw.ui.advanced;

import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;
import com.zs.androidappfw.widgets.TwoColumnLayout;
import com.zs.androidappfw.utils.LUtil;

public class CustomizationViewGroupAct extends BaseTitleActivity {

    private TwoColumnLayout twoColumnLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_customization_view_group);
        twoColumnLayout = findViewById(R.id.customization_view_group_tcl);
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_diy_view_group;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        LUtil.d(mTag, "onAttachedToWindow");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        LUtil.d(mTag, "onWindowFocusChanged");
        LUtil.d(mTag, "" + twoColumnLayout.getLayoutParams());
        View view = getWindow().getDecorView();
        ViewParent viewParent = view.getParent();
        while(viewParent != null) {
            LUtil.d(mTag, "parent=" + viewParent.getParent() + " v=" + viewParent.toString());
            viewParent = viewParent.getParent();
        }

        printView(view);
    }

    private void printView(View view) {
        ViewGroup viewGroup = null;
        if (!(view instanceof ViewGroup)) {
            LUtil.d(mTag, "w=" + view.getWidth() + " h=" + view.getHeight() + " parent=" + view.getParent() + " v=" + view.toString());
            return;
        }
        viewGroup = (ViewGroup) view;
        LUtil.d(mTag,"w=" + view.getWidth() + " h=" + view.getHeight() + " parent=" + viewGroup.getParent() + " v=" + viewGroup.toString());
        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View v = viewGroup.getChildAt(i);
            printView(v);
        }
    }
}
