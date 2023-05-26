package com.zs.androidappfw.ui.activity.fundamental.drawable;

import android.graphics.drawable.ScaleDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.ui.base.BaseActivity;

public class ScaleDrawableAct extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_drawable_scale);

        ImageView imageView = findViewById(R.id.drawable_scale_iv);
        ScaleDrawable scaleDrawable = (ScaleDrawable) imageView.getDrawable();
        scaleDrawable.setLevel(1);
    }
}
