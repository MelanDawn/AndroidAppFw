package com.zs.androidappfw.ui.view;

// Created by zhangs on 2019/2/23.

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;

public class ButtonAct extends BaseTitleActivity {

    Button diyBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_btn);

        diyBtn = findViewById(R.id.btn_diy_shape);
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_view_button;
    }

    public void controlDiyBtn(View view) {
        diyBtn.setEnabled(!diyBtn.isEnabled());
    }
}
