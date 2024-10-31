package com.zs.androidappfw.wcn.wifi;

import android.content.Context;
import android.net.wifi.aware.WifiAwareManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseClickFragment;

import java.util.ArrayList;
import java.util.List;

public class WifiNanFgm extends BaseClickFragment {

    private WifiAwareManager mAwareManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAwareManager = (WifiAwareManager) mContext.getSystemService(Context.WIFI_AWARE_SERVICE);
//        mAwareManager.isSetChannelOnDataPathSupported();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fgm_wifi_sta, container, false);
        initAndSetClickListener(view, new int[] {

        });
        return view;
    }

    @Override
    public void onClick(View v) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        List<int[]> list = new ArrayList<>();
        int[] a;
    }
}
