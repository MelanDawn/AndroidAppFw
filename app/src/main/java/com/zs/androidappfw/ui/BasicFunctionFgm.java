package com.zs.androidappfw.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseClickFragment;
import com.zs.androidappfw.ui.fundamental.AndroidBasicInfoAct;
import com.zs.androidappfw.ui.fundamental.drawable.DrawableAct;
import com.zs.androidappfw.ui.fundamental.font.FontActivity;
import com.zs.androidappfw.ui.fundamental.lang.JavaAct;
import com.zs.androidappfw.ui.lifecycle.ActivityStandardAct;
import com.zs.androidappfw.ui.lifecycle.BroadcastReceiverLifecycleAct;
import com.zs.androidappfw.ui.lifecycle.ContentProviderLifecycleAct;
import com.zs.androidappfw.ui.lifecycle.FragmentDynamicAct;
import com.zs.androidappfw.ui.lifecycle.ServiceLifecycleAct;
import com.zs.androidappfw.ui.wsim.WindowSoftInputModeAct;

public class BasicFunctionFgm extends BaseClickFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fgm_main_basic_function, container, false);
        initAndSetClickListener(view, new int[] {
                R.id.basic_function_to_java,
                R.id.basic_function_to_android,
                R.id.basic_function_to_activity_lifecycle,
                R.id.basic_function_to_fragment_lifecycle,
                R.id.basic_function_to_service,
                R.id.basic_function_to_broadcast_receiver,
                R.id.basic_function_to_content_provider,
                R.id.basic_function_to_wsim,
                R.id.basic_function_to_drawable,
                R.id.basic_function_to_font,
        });
        return view;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.basic_function_to_java:
                startActivity(JavaAct.class);
                break;
            case R.id.basic_function_to_android:
                startActivity(AndroidBasicInfoAct.class);
                break;
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
        }
    }
}
