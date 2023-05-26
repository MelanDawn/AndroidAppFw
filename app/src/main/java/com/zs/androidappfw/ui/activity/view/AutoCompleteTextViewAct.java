package com.zs.androidappfw.ui.activity.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.zs.androidappfw.R;
import com.zs.androidappfw.ui.base.BaseActivity;
import com.zs.androidappfw.utils.LUtil;


// Created by zhangs on 2019/2/24.

public class AutoCompleteTextViewAct extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_auto_complete_tv);

        String[] resource = getResources().getStringArray(R.array.auto_complete_tv_res);
        ArrayAdapter<String> adapter =  new ArrayAdapter<> (this, android.R.layout.simple_list_item_1, resource);

        AutoCompleteTextView tv1 = findViewById(R.id.auto_complete_tv_1);
        tv1.setAdapter(adapter);
        LUtil.d(TAG, "默认的提示必要输入字符数量： " + tv1.getThreshold());
        AutoCompleteTextView tv2 = findViewById(R.id.auto_complete_tv_2);
        tv2.setAdapter(adapter);
        AutoCompleteTextView tv3 = findViewById(R.id.auto_complete_tv_3);
        tv3.setAdapter(adapter);
    }
}
