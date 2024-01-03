package com.zs.androidappfw.ui.view.viewgroup;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;

public class LinearLayoutAct extends BaseTitleActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_linear_layout);
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_vg_linear_layout;
    }
}
