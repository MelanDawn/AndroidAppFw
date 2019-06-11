package com.zs.androidappfw.ui.activity.view.viewgroup;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.ZoomControls;

import com.zs.androidappfw.R;
import com.zs.androidappfw.ui.base.BaseActivity;
import com.zs.androidappfw.utils.LUtil;


// Created by zhangs on 2019/2/28.

public class ZoomControlsAct extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_zoom_controls);

        test();
    }

    private void test() {
        final TextView tv = findViewById(R.id.zoom_controls_demo_1_tv);
        ZoomControls zc = findViewById(R.id.zoom_controls_demo_1);
        zc.setIsZoomInEnabled(true);
        zc.setIsZoomOutEnabled(true);
        zc.setZoomSpeed(1000); // 设置触发间隔为1秒
        zc.setOnZoomOutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LUtil.d(TAG, "setOnZoomOutClickListener " + tv.getTextSize());
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, px2sp(ZoomControlsAct.this, tv.getTextSize()) - 2);
            }
        });
        zc.setOnZoomInClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LUtil.d(TAG, "setOnZoomInClickListener " + tv.getTextSize());
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, px2sp(ZoomControlsAct.this, tv.getTextSize()) + 2);
            }
        });
    }
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }
}
