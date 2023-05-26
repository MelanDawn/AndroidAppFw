package com.zs.androidappfw.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.ui.activity.fundamental.DeviceInfoAct;
import com.zs.androidappfw.ui.activity.fundamental.FileSystemAct;
import com.zs.androidappfw.ui.activity.fundamental.WindowSoftInputModeAct;
import com.zs.androidappfw.ui.activity.fundamental.drawable.DrawableAct;
import com.zs.androidappfw.ui.activity.fundamental.font.FontActivity;
import com.zs.androidappfw.ui.activity.fundamental.lifecycle.ActivityStandardAct;
import com.zs.androidappfw.ui.base.BaseFragment;

public class BaseFgm extends BaseFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fgm_main_base, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        initAndSetClickListener(view, R.id.base_to_activity_lifecycle);
        initAndSetClickListener(view, R.id.base_to_wsim);
        initAndSetClickListener(view, R.id.base_to_drawable);
        initAndSetClickListener(view, R.id.base_to_font);
        initAndSetClickListener(view, R.id.base_to_file_system);
        initAndSetClickListener(view, R.id.base_to_device_info);
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
            case R.id.base_to_file_system:
                startActivity(FileSystemAct.class);
                break;
            case R.id.base_to_device_info:
                startActivity(DeviceInfoAct.class);
                break;
        }
    }
}
