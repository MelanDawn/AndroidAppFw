package com.zs.androidappfw.ui.activity.view;

// Created by zhangs on 2019/2/23.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.zs.androidappfw.R;
import com.zs.androidappfw.ui.base.BaseActivity;

public class ButtonAct extends BaseActivity {

    Button diyBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_btn);

        diyBtn = findViewById(R.id.btn_diy_shape);
    }

    public void controlDiyBtn(View view) {
        diyBtn.setEnabled(!diyBtn.isEnabled());
    }
}
