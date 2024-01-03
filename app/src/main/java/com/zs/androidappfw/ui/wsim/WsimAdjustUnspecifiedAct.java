package com.zs.androidappfw.ui.wsim;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;

public class WsimAdjustUnspecifiedAct extends BaseTitleActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_wsim);
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_adjust_unspecified;
    }
}
