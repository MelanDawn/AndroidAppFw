package com.zs.androidappfw.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.ui.fundamental.DeviceInfoAct;
import com.zs.androidappfw.ui.fundamental.FileSystemAct;
import com.zs.androidappfw.ui.lifecycle.BroadcastReceiverLifecycleAct;
import com.zs.androidappfw.ui.lifecycle.ContentProviderLifecycleAct;
import com.zs.androidappfw.ui.lifecycle.FragmentDynamicAct;
import com.zs.androidappfw.ui.lifecycle.ServiceLifecycleAct;
import com.zs.androidappfw.ui.wsim.WindowSoftInputModeAct;
import com.zs.androidappfw.ui.fundamental.drawable.DrawableAct;
import com.zs.androidappfw.ui.fundamental.font.FontActivity;
import com.zs.androidappfw.ui.lifecycle.ActivityStandardAct;
import com.zs.androidappfw.base.BaseFragment;

public class BasicFunctionFgm extends BaseFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fgm_main_basic_function, container, false);
        initView(view);
        return view;
    }

    protected void initView(View view) {
        initAndSetClickListener(view, R.id.basic_function_to_activity_lifecycle);
        initAndSetClickListener(view, R.id.basic_function_to_fragment_lifecycle);
        initAndSetClickListener(view, R.id.basic_function_to_service);
        initAndSetClickListener(view, R.id.basic_function_to_broadcast_receiver);
        initAndSetClickListener(view, R.id.basic_function_to_content_provider);
        initAndSetClickListener(view, R.id.basic_function_to_wsim);
        initAndSetClickListener(view, R.id.basic_function_to_drawable);
        initAndSetClickListener(view, R.id.basic_function_to_font);
        initAndSetClickListener(view, R.id.basic_function_to_file_system);
        initAndSetClickListener(view, R.id.basic_function_to_device_info);
    }

    private void initAndSetClickListener(View view, int id) {
        view.findViewById(id).setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.basic_function_to_activity_lifecycle:
                startActivity(ActivityStandardAct.class);
                break;
            case R.id.basic_function_to_fragment_lifecycle:
                startFragmentActivity(FragmentDynamicAct.class);
                break;
            case R.id.basic_function_to_service:
                startActivity(ServiceLifecycleAct.class);
                break;
            case R.id.basic_function_to_broadcast_receiver:
                startActivity(BroadcastReceiverLifecycleAct.class);
                break;
            case R.id.basic_function_to_content_provider:
                startActivity(ContentProviderLifecycleAct.class);
                break;
            case R.id.basic_function_to_wsim:
                startActivity(WindowSoftInputModeAct.class);
                break;
            case R.id.basic_function_to_drawable:
                startActivity(DrawableAct.class);
                break;
            case R.id.basic_function_to_font:
                startActivity(FontActivity.class);
                break;
            case R.id.basic_function_to_file_system:
                startActivity(FileSystemAct.class);
                break;
            case R.id.basic_function_to_device_info:
                startActivity(DeviceInfoAct.class);
                break;
        }
    }
}
