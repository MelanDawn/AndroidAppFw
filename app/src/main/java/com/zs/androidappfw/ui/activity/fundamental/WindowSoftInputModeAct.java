package com.zs.androidappfw.ui.activity.fundamental;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.zs.androidappfw.R;
import com.zs.androidappfw.ui.base.BaseActivity;
import com.zs.androidappfw.utils.LUtil;

public class WindowSoftInputModeAct extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_window_input_soft_mode);
        LUtil.d(TAG, ""+this.isChild());
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

    private void toActivity(Class<? extends BaseActivity> cls) {
        startActivity(new Intent(this, cls));
    }
}
