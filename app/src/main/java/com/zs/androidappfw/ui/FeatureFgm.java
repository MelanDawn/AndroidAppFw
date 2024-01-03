package com.zs.androidappfw.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseFragment;
import com.zs.androidappfw.cellular.CellularActivity;
import com.zs.androidappfw.wcn.WcnActivity;

public class FeatureFgm extends BaseFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fgm_main_function, container, false);
        initView(view);
        return view;
    }

    protected void initView(View view) {
        view.findViewById(R.id.function_to_wcn_btn).setOnClickListener(this);
        view.findViewById(R.id.function_to_cellular_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.function_to_wcn_btn) {
            startActivity(new Intent(getActivity(), WcnActivity.class));
        } else if (id == R.id.function_to_cellular_btn) {
            startActivity(new Intent(getActivity(), CellularActivity.class));
        }
    }
}
