package com.zs.androidappfw.ui.view.viewgroup;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;


// Created by zhangs on 2019/3/10.

public class ImageSwitcherAct extends BaseTitleActivity {

    private int mIndex = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_image_switcher);

        test();
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_vg_image_switcher;
    }

    public void test() {
        final ImageSwitcher switcher = findViewById(R.id.image_switcher_demo_1);
        Factory factory = new Factory(this);
        switcher.setFactory(factory);

        switcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left));
        switcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right));

        final int[] mImages = {R.drawable.act_iv_demo_h, R.drawable.act_iv_demo_v, R.drawable.act_iv_demo_mini};
        switcher.setImageResource(mImages[mIndex]);
        switcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIndex++;
                if (mIndex >= mImages.length) {
                    mIndex = 0;
                }
                switcher.setImageResource(mImages[mIndex]);
            }
        });
    }

    private class Factory implements ImageSwitcher.ViewFactory {

        private Context context;
        Factory(Context context) {
            this.context = context;
        }

        @Override
        public View makeView() {
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            ));
            return imageView;
        }
    }
}
