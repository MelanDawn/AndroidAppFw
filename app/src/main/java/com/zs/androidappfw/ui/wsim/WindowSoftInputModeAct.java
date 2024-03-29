package com.zs.androidappfw.ui.wsim;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;
import com.zs.androidappfw.utils.LUtil;

public class WindowSoftInputModeAct extends BaseTitleActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_window_input_soft_mode);
        LUtil.d(mTag, "" + this.isChild());
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_window_soft_input_method;
    }

    public void toAdjustNothing(View view) {
        toActivity(WsimAdjustNothingAct.class);
    }
    public void toAdjustPan(View view) {
        toActivity(WsimAdjustPanAct.class);
    }
    public void toAdjustResize(View view) {
        toActivity(WsimAdjustResizeAct.class);
    }
    public void toAdjustUnspecified(View view) {
        toActivity(WsimAdjustUnspecifiedAct.class);
    }
    public void toStateAlwaysHidden(View view) {
        toActivity(WsimStateAlwaysHiddenAct.class);
    }
    public void toStateAlwaysVisible(View view) {
        toActivity(WsimStateAlwaysVisibleAct.class);
    }
    public void toStateHidden(View view) {
        toActivity(WsimStateHiddenAct.class);
    }
    public void toStateVisible(View view) {
        toActivity(WsimStateVisibleAct.class);
    }
    public void toStateUnchanged(View view) {
        toActivity(WsimStateUnchangedAct.class);
    }
    public void toStateUnspecified(View view) {
        toActivity(WsimStateUnspecifiedAct.class);
    }

    private void toActivity(Class<? extends BaseTitleActivity> cls) {
        startActivity(new Intent(this, cls));
    }
}
