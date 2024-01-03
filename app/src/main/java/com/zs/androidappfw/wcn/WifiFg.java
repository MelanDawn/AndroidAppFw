package com.zs.androidappfw.wcn;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseFragment;
import com.zs.androidappfw.base.BaseTitleFragment;

public class WifiFg extends BaseFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fgm_wcn_wifi, container, false);
        initView(view);
        return view;
    }

    protected void initView(View view) {
        initAndSetClickListener(view, R.id.fg_wcn_wifi_wifi);
    }

    private void initAndSetClickListener(View view, int id) {
        view.findViewById(id).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fg_wcn_wifi_wifi:
                //startActivity(ClassicBtAct.class);
                break;
        }
    }
}
