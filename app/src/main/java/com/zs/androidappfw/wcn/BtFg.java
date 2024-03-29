package com.zs.androidappfw.wcn;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseFragment;
import com.zs.androidappfw.wcn.bt.BleAct;
import com.zs.androidappfw.wcn.bt.ClassicBtAct;

public class BtFg extends BaseFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fgm_wcn_bt, container, false);
        initView(view);
        return view;
    }

    protected void initView(View view) {
        initAndSetClickListener(view, R.id.fg_wcn_bt_classic);
        initAndSetClickListener(view, R.id.fg_wcn_bt_ble);
    }

    private void initAndSetClickListener(View view, int id) {
        view.findViewById(id).setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fg_wcn_bt_classic:
                startActivity(ClassicBtAct.class);
                break;
            case R.id.fg_wcn_bt_ble:
                startActivity(BleAct.class);
                break;
        }
    }
}
