package com.zs.androidappfw.ui.fundamental.lang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;

public class JavaAct extends BaseTitleActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_java);
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_java;
    }

    public void toJavaLang(View view) {
        startActivity(new Intent(this, JavaLangAct.class));
    }

    public void toJavaLangAnnotation(View view) {
        startActivity(new Intent(this, JavaLangAnnotationAct.class));
    }

    public void toJavaLangInvoke(View view) {
        startActivity(new Intent(this, JavaLangInvokeAct.class));
    }

    public void toJavaLangRef(View view) {
        startActivity(new Intent(this, JavaLangRefAct.class));
    }

    public void toJavaLangReflect(View view) {
        startActivity(new Intent(this, JavaLangReflectAct.class));
    }
}
