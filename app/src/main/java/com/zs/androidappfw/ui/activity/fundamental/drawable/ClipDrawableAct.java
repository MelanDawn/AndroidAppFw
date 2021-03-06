package com.zs.androidappfw.ui.activity.fundamental.drawable;

import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.zs.androidappfw.R;
import com.zs.androidappfw.ui.base.BaseActivity;

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
