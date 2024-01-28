package com.zs.androidappfw.ui.fundamental.lang;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;
import com.zs.androidappfw.utils.ClassInfoUtil;

public class ClassDetailsAct extends BaseTitleActivity {

    private Class<?> mClazz = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_common_list);
        LinearLayout linearLayout = findViewById(R.id.common_list_ll);

        String clazz = getIntent().getStringExtra(ClassInfoUtil.KEY_CLASS);
        if (!TextUtils.isEmpty(clazz)) {
            try {
                mClazz = Class.forName(clazz);
                ClassInfoUtil.displayClass(this, linearLayout, mClazz);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected int getTitleResId() {
        return 0;
    }

    @Override
    protected String getTitleText() {
        if (mClazz != null) return mClazz.getSimpleName();
        return super.getTitleText();
    }
}
