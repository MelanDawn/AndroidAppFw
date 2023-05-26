package com.zs.androidappfw.ui.activity.fundamental.lifecycle;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import android.view.View;

import com.zs.androidappfw.R;
import com.zs.androidappfw.ui.base.BaseActivity;
import com.zs.androidappfw.ui.base.BaseLifecycleActivity;
import com.zs.androidappfw.utils.LUtil;

public class ActivitySingleInstanceAct extends BaseLifecycleActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_activity_lifecycle);
    }

    public void toActivityStandard(View v) {
        startActivity(new Intent(this, ActivityStandardAct.class));
    }
    public void toActivitySingleTop(View v) {
        startActivity(new Intent(this, ActivitySingleTopAct.class));
    }
    public void toActivitySingleTask(View v) {
        startActivity(new Intent(this, ActivitySingleTaskAct.class));
    }
    public void toActivitySingleTaskAffinity(View v) {
        startActivity(new Intent(this, ActivitySingleTaskAffinityAct.class));
    }
    public void toActivitySingleInstance(View v) {
        startActivity(new Intent(this, ActivitySingleInstanceAct.class));
    }

    public void toActivityStandardConfigChanges(View v) {
        startActivity(new Intent(this, ActivityStandardConfigChangesAct.class));
    }
}
