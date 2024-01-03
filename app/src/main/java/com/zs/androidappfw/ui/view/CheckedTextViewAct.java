package com.zs.androidappfw.ui.view;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckedTextView;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;
import com.zs.androidappfw.utils.LUtil;


// Created by zhangs on 2019/2/23.

public class CheckedTextViewAct extends BaseTitleActivity {

    CheckedTextView multipleTv1, multipleTv2, singleTv1, singleTv2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_checked_tv);

        multipleTv1 = findViewById(R.id.checked_tv_multiple_1);
        multipleTv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                multipleTv1.toggle();
                LUtil.d(mTag, "multipleTv1 checked after clicked : "+multipleTv1.isChecked());
            }
        });
        multipleTv2 = findViewById(R.id.checked_tv_multiple_2);
        multipleTv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                multipleTv2.toggle();
                LUtil.d(mTag, "multipleTv2 checked after clicked : "+multipleTv2.isChecked());
            }
        });
        singleTv1 = findViewById(R.id.checked_tv_single_1);
        singleTv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                singleTv1.toggle();
                LUtil.d(mTag, "singleTv1 checked after clicked : "+singleTv1.isChecked());
            }
        });
        singleTv2 = findViewById(R.id.checked_tv_single_2);
        singleTv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                singleTv2.toggle();
                LUtil.d(mTag, "singleTv2 checked after clicked : "+singleTv2.isChecked());
            }
        });
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_view_checked_textview;
    }
}
