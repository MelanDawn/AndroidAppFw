package com.zs.androidappfw.ui.activity.fundamental.drawable;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.zs.androidappfw.R;
import com.zs.androidappfw.ui.base.BaseActivity;
//import com.zs.androidappfw.utils.LUtil;

public class LayerDrawableAct extends BaseActivity {

//    private ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_drawable_layer);

//        imageView = findViewById(R.id.drawable_layer_iv);
//        LUtil.d(TAG, "height=" + imageView.getHeight() + " width=" + imageView.getWidth());
    }

    @Override
    protected void onResume() {
        super.onResume();
//        LUtil.d(TAG, "height=" + imageView.getHeight() + " width=" + imageView.getWidth());
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
//        LUtil.d(TAG, "height=" + imageView.getHeight() + " width=" + imageView.getWidth());
    }
}
