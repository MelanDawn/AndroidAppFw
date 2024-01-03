package com.zs.androidappfw.ui.view.viewgroup;

import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;


// Created by zhangs on 2019/2/28.

public class NumberPickerAct extends BaseTitleActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_number_picker);

        test();
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_vg_number_picker;
    }

    private void test() {
        NumberPicker np1 = findViewById(R.id.number_picker_person_height_np);
        final TextView tv1 = findViewById(R.id.number_picker_person_height_tv);

        np1.setMaxValue(200);
        np1.setMinValue(100);
        np1.setValue(150);

        np1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                //numberPicker 与监听器关联的NumberPicker，这里应该是np1；
                String str = getString(R.string.number_picker_person_height) + newValue;
                tv1.setText(str);
            }
        });

        NumberPicker np2 = findViewById(R.id.number_picker_demo_2);
        String[] options = {"刘备", "关羽", "张飞", "赵云", "曹操", "曹丕", "曹叡", "孙坚", "孙策", "孙权"};
        np2.setDisplayedValues(options);
        np2.setMinValue(1);
        np2.setMaxValue(options.length);
        np2.setValue(options.length/2);
        // 不循环
        np2.setWrapSelectorWheel(false);
        // 不可编辑
        np2.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
    }
}
