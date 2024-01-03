package com.zs.androidappfw.ui.advanced;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;

public class CustomizationPermissionAct extends BaseTitleActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_customization_permission);
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_diy_permission;
    }
}
