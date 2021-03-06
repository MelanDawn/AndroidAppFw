package com.zs.androidappfw.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zs.androidappfw.R;
import com.zs.androidappfw.ui.activity.advanced.CustomizationPermissionAct;
import com.zs.androidappfw.ui.activity.advanced.CustomizationViewAct;
import com.zs.androidappfw.ui.activity.advanced.CustomizationViewGroupAct;
import com.zs.androidappfw.ui.activity.advanced.DispatchTouchEventAct;
import com.zs.androidappfw.ui.base.BaseFragment;

public class AdvancedFrgm extends BaseFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frgm_main_advanced, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
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
