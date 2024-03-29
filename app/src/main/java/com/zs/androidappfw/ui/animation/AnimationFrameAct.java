package com.zs.androidappfw.ui.animation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;

public class AnimationFrameAct extends BaseTitleActivity {

    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_animation_frame);

        ImageView imageView = findViewById(R.id.animation_frame_iv_1);
        imageView.setImageResource(R.drawable.animation_frame);
        animationDrawable = (AnimationDrawable) imageView.getDrawable();
        animationDrawable.setOneShot(false);
        animationDrawable.start();
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_animation_frame;
    }

    @Override
    protected void onDestroy() {
        animationDrawable.stop();
        animationDrawable = null;
        super.onDestroy();
    }
}
