package com.zs.androidappfw.ui.fundamental.lang;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;
import com.zs.androidappfw.utils.ClassInfoUtil;

public class JavaLangReflectAct extends BaseTitleActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_common_list);
        LinearLayout linearLayout = findViewById(R.id.common_list_ll);
        ClassInfoUtil.displayPackage(this, linearLayout, Utils.JAVA_LANG_REFLECT_CLASSES_ALL);
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_java_lang_reflect;
    }
}
