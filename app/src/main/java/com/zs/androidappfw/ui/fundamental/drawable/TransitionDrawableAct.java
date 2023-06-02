package com.zs.androidappfw.ui.fundamental.drawable;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseActivity;

public class TransitionDrawableAct extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_drawable_transition);

        ImageView imageView = findViewById(R.id.drawable_transition_iv);
        TransitionDrawable transitionDrawable = (TransitionDrawable) imageView.getDrawable();
        transitionDrawable.startTransition(500);
    }
}
