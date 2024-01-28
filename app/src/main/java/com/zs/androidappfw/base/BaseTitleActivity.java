package com.zs.androidappfw.base;

import android.graphics.Typeface;
import android.text.TextUtils;
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
        mTitleTv.setText(getCustomizedTitle());
        Typeface bond = Typeface.create("font/timesbd.ttf", Typeface.BOLD);
        mTitleTv.setTypeface(bond);
        mTitleBackBtn.setOnClickListener(this::onBackClicked);
        mTitleMoreBtn.setOnClickListener(this::onMoreClicked);
    }

    private String getCustomizedTitle() {
        int titleResId = getTitleResId();
        if (titleResId > 0) {
            return getString(titleResId);
        }

        String title = getTitleText();
        if (!TextUtils.isEmpty(title)) return title;

        return getString(R.string.app_name);
    }
    protected abstract int getTitleResId();

    protected String getTitleText() {
        return "";
    }

    protected void onBackClicked(View view) {
        finish();
    }

    protected void onMoreClicked(View view) {

    }
}
