package com.zs.androidappfw.ui.activity.animation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.zs.androidappfw.R;
import com.zs.androidappfw.ui.base.BaseActivity;

public class AnimationFrameAct extends BaseActivity {

    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_animation_frame);

        ImageView imageView = findViewById(R.id.animation_frame_iv_1);
        imageView.setImageResource(R.drawable.animation_frame);
        animationDrawable = (AnimationDrawable)imageView.getDrawable();
        animationDrawable.setOneShot(false);
        animationDrawable.start();
    }

    @Override
    protected void onDestroy() {
        animationDrawable.stop();
        animationDrawable = null;
        super.onDestroy();
    }
}
