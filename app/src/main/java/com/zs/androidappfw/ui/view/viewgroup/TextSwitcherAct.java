package com.zs.androidappfw.ui.view.viewgroup;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseActivity;


// Created by zhangs on 2019/3/10.

public class TextSwitcherAct extends BaseActivity {

    private int mIndex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_text_switcher);

        test();
    }

    public void test() {
        final TextSwitcher switcher = findViewById(R.id.text_switcher_demo_1);
        Factory factory = new Factory(this);
        switcher.setFactory(factory);

        switcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left));
        switcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right));

        final String[] mTexts = getResources().getStringArray(R.array.auto_complete_tv_res);
        switcher.setText(mTexts[mIndex]);
        switcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIndex++;
                if (mIndex >= mTexts.length) {
                    mIndex = 0;
                }
                switcher.setText(mTexts[mIndex]);
            }
        });
    }

    private class Factory implements TextSwitcher.ViewFactory {

        private Context context;
        Factory(Context context) {
            this.context = context;
        }

        @Override
        public View makeView() {
            TextView textView = new TextView(context);
            textView.setGravity(Gravity.CENTER);
            textView.setLayoutParams(new ImageSwitcher.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            ));
            return textView;
        }
    }
}
