package com.zs.androidappfw.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zs.androidappfw.R;
import com.zs.androidappfw.ui.activity.fundamental.ActivityLifecycleAct;
import com.zs.androidappfw.ui.activity.view.ProgressBarAct;
import com.zs.androidappfw.ui.base.BaseFragment;

public class BaseFrgm extends BaseFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frgm_main_base, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        initAndSetClickListener(view, R.id.base_to_activity_lifecycle);
    }

    private void initAndSetClickListener(View view, int id) {
        view.findViewById(id).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.base_to_activity_lifecycle:
                startActivity(ActivityLifecycleAct.class);
                break;
            case R.id.ui_to_progress_bar:
                startActivity(ProgressBarAct.class);
                break;
        }
    }
}
