package com.zs.androidappfw.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.ui.base.BaseActivity;

public class HorizontalScrollViewAct extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_horizontal_scroll_view);
    }
}
