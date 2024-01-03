package com.zs.androidappfw.ui.lifecycle;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleFragmentActivity;

public class FragmentStaticAct extends BaseTitleFragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_fgm_static);
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_fragment_static;
    }
}
