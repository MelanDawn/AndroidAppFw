package com.zs.androidappfw.ui.lifecycle;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseLifecycleActivity;

public class ActivitySingleInstanceAct extends BaseLifecycleActivity {
    @Override
    protected int getTitleResId() {
        return R.string.title_activity_single_instance;
    }
}
