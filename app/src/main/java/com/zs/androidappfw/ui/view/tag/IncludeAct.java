package com.zs.androidappfw.ui.view.tag;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;

public class IncludeAct extends BaseTitleActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_tag_include);
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_tag_include;
    }
}
