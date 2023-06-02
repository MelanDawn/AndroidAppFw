package com.zs.androidappfw.ui.view.viewgroup;

import android.os.Bundle;

import androidx.annotation.Nullable;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseActivity;


// Created by zhangs on 2019/2/28.

public class RadioGroupAct extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_radio_group);

        test();
    }

    private void test() {
        final RadioGroup rg1 = findViewById(R.id.radio_group_demo_1);
        final TextView rt1 = findViewById(R.id.radio_group_demo_1_result);
        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                // i 即是被RadioGroup控制的被选择的RadioButton的id；
                String result = "selected button = " + i + " rg1 = " + rg1.getCheckedRadioButtonId();
                rt1.setText(result);
            }
        });

        final RadioGroup rg2 = findViewById(R.id.radio_group_demo_2);
        final TextView rt2 = findViewById(R.id.radio_group_demo_2_result);
        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                String result = "selected button = " + i + " rg2 = " + rg2.getCheckedRadioButtonId();
                rt2.setText(result);
            }
        });

        final RadioGroup rg3 = findViewById(R.id.radio_group_demo_3);
        final TextView rt3 = findViewById(R.id.radio_group_demo_3_result);
        rg3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                String result = "selected button = " + i + " rg3 = " + rg3.getCheckedRadioButtonId();
                rt3.setText(result);
            }
        });
    }
}
