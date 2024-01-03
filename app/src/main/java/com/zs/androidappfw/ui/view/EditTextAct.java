package com.zs.androidappfw.ui.view;

import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;


// Created by zhangs on 2019/2/21.

public class EditTextAct extends BaseTitleActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_et);

        //设置光标位置，测试发现：如果输入框内容太长且后续还有能获取焦点的输入框，那么光标移到下一个输入框首行
        EditText et = findViewById(R.id.et_content_focus);
        et.setSelection(et.getText().length());
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_view_edittext;
    }
}
