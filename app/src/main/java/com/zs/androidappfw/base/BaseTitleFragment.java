package com.zs.androidappfw.base;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zs.androidappfw.R;

public abstract class BaseTitleFragment extends BaseFragment {

    public static final String FRAGMENT_TITLE_RES_ID = "FRAGMENT_TITLE_RES_ID";

    protected ImageButton mTitleBackBtn, mTitleMoreBtn;
    protected TextView mTitleTv;
    protected void initView(View view) {
        initTitleView(view);
    }

    protected void initTitleView(View view) {
        mTitleBackBtn = view.findViewById(R.id.title_back_btn);
        mTitleMoreBtn = view.findViewById(R.id.title_more_btn);
        mTitleTv = view.findViewById(R.id.title_title_tv);
        mTitleBackBtn.setOnClickListener(v -> requireActivity().finish());
        mTitleMoreBtn.setOnClickListener(v -> onMoreTouched());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initTitleView(view);

        Bundle bundle = getArguments();
        if (bundle != null) {
            int resId = bundle.getInt(FRAGMENT_TITLE_RES_ID, -1);
            if (resId > 0) setTitleName(resId);
        }
    }

    protected void setTitleName(int resid) {
        mTitleTv.setText(resid);
    }

    protected void onMoreTouched() {

    }
}

