package com.zs.androidappfw.base;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zs.androidappfw.R;


// Created by zhangs on 2018/10/29.

public abstract class BaseTitleActivity extends BaseActivity {

    protected ImageButton mTitleBackBtn, mTitleMoreBtn;
    protected TextView mTitleTv;

    @Override
    protected void onStart() {
        super.onStart();

        mTitleBackBtn = findViewById(R.id.title_back_btn);
        mTitleMoreBtn = findViewById(R.id.title_more_btn);
        mTitleTv = findViewById(R.id.title_title_tv);
        mTitleTv.setText(getTitleResId());
        mTitleBackBtn.setOnClickListener(this::onBackClicked);
        mTitleMoreBtn.setOnClickListener(this::onMoreClicked);
    }

    protected abstract int getTitleResId();

    protected void onBackClicked(View view) {
        finish();
    }

    protected void onMoreClicked(View view) {

    }
}
