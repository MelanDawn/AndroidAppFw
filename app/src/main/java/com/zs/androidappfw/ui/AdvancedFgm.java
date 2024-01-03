package com.zs.androidappfw.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseFragment;
import com.zs.androidappfw.ui.advanced.CustomizationPermissionAct;
import com.zs.androidappfw.ui.advanced.CustomizationViewAct;
import com.zs.androidappfw.ui.advanced.CustomizationViewGroupAct;
import com.zs.androidappfw.ui.advanced.DispatchTouchEventAct;

public class AdvancedFgm extends BaseFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fgm_main_advanced, container, false);
        initView(view);
        return view;
    }

    protected void initView(View view) {
        initAndSetClickListener(view, R.id.advanced_to_diy_permission);
        initAndSetClickListener(view, R.id.advanced_to_dispatch_touch_event);
        initAndSetClickListener(view, R.id.advanced_to_diy_view);
        initAndSetClickListener(view, R.id.advanced_to_diy_view_group);
    }

    private void initAndSetClickListener(View view, int id) {
        view.findViewById(id).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.advanced_to_diy_permission:
                startActivity(CustomizationPermissionAct.class);
                break;
            case R.id.advanced_to_dispatch_touch_event:
                startActivity(DispatchTouchEventAct.class);
                break;
            case R.id.advanced_to_diy_view:
                startActivity(CustomizationViewAct.class);
                break;
            case R.id.advanced_to_diy_view_group:
                startActivity(CustomizationViewGroupAct.class);
                break;
        }
    }
}
