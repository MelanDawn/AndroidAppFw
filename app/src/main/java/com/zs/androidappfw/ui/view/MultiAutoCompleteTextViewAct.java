package com.zs.androidappfw.ui.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseActivity;


// Created by zhangs on 2019/2/24.

public class MultiAutoCompleteTextViewAct extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_multi_auto_complete_tv);

        String[] resource = getResources().getStringArray(R.array.multi_auto_complete_tv_res);
        ArrayAdapter<String> adapter =  new ArrayAdapter<> (this, android.R.layout.simple_list_item_1, resource);

        MultiAutoCompleteTextView tv1 = findViewById(R.id.multi_auto_complete_tv_1);
        tv1.setAdapter(adapter);
        // 设置关键词之间分隔符：逗号
        tv1.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        MultiAutoCompleteTextView tv2 = findViewById(R.id.multi_auto_complete_tv_2);
        tv2.setAdapter(adapter);
        tv2.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        MultiAutoCompleteTextView tv3 = findViewById(R.id.multi_auto_complete_tv_3);
        tv3.setAdapter(adapter);
        tv3.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }
}
