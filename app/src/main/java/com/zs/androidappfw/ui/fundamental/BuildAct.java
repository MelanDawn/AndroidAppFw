package com.zs.androidappfw.ui.fundamental;

import android.os.Build;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;
import com.zs.androidappfw.utils.ClassInfoUtil;

public class BuildAct extends BaseTitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_device_info);
        LinearLayout linearLayout = findViewById(R.id.device_info_ll);
        ClassInfoUtil.displayClassInfo(this, linearLayout, Build.class);
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_android_build;
    }
}
