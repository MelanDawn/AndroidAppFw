package com.zs.androidappfw.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zs.androidappfw.R;
import com.zs.androidappfw.ui.activity.fundamental.drawable.DrawableAct;
import com.zs.androidappfw.ui.activity.fundamental.font.FontActivity;
import com.zs.androidappfw.ui.activity.fundamental.lifecycle.ActivityStandardAct;
import com.zs.androidappfw.ui.activity.fundamental.WindowSoftInputModeAct;
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
        initAndSetClickListener(view, R.id.base_to_wsim);
        initAndSetClickListener(view, R.id.base_to_drawable);
        initAndSetClickListener(view, R.id.base_to_font);
    }

    private void initAndSetClickListener(View view, int id) {
        view.findViewById(id).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.base_to_activity_lifecycle:
                startActivity(ActivityStandardAct.class);
                break;
            case R.id.base_to_wsim:
                startActivity(WindowSoftInputModeAct.class);
                break;
            case R.id.base_to_drawable:
                startActivity(DrawableAct.class);
                break;
            case R.id.base_to_font:
                startActivity(FontActivity.class);
                break;
        }
    }
}
