package com.zs.androidappfw.ui.fundamental.drawable;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;

public class ShapeDrawableAct extends BaseTitleActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_drawable_shape);
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_drawable_shape;
    }
}
