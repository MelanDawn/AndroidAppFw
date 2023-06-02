package com.zs.androidappfw.ui.fundamental.drawable;

import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseActivity;

public class ClipDrawableAct extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_drawable_clip);

        ImageView imageView = findViewById(R.id.drawable_clip_iv);
        ClipDrawable drawable = (ClipDrawable) imageView.getDrawable();
        drawable.setLevel(drawable.getLevel() + 5000);
    }
}
