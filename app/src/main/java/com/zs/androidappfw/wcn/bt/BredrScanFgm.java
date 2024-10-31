package com.zs.androidappfw.wcn.bt;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseClickFragment;
import com.zs.androidappfw.utils.LUtil;
import com.zs.androidappfw.wcn.utils.BtAdapter;
import com.zs.androidappfw.wcn.utils.BtDevice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BredrScanFgm extends BaseClickFragment {

    private final Map<String, BtDevice> mScanResultMap = new HashMap<>();
    private final List<BtDevice> mScanResultList = new ArrayList<>();
    private final List<BtDevice> mBondedList = new ArrayList<>();
    private BredrScanAdapter mAdapter;

    private Button mScanBtn;
    private boolean mDiscovering = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fgm_bredr_scan, container, false);
        initView(view);
        return view;
    }

    protected void initView(View view) {
        mAdapter = new BredrScanAdapter(mContext, mScanResultList);
        RecyclerView bredrScanRv = view.findViewById(R.id.fgm_bredr_scan_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        bredrScanRv.setLayoutManager(linearLayoutManager);
        bredrScanRv.addItemDecoration(new DividerItemDecoration(mContext,
                DividerItemDecoration.VERTICAL));
        bredrScanRv.setAdapter(mAdapter);

        addTestData();
        addBondedDevice();

        mScanBtn = view.findViewById(R.id.fgm_bredr_scan_btn);
        mScanBtn.setOnClickListener(this);
        updateScanStatus(BtAdapter.isDiscovering(mContext));
    }

    private void addTestData() {
        BtDevice device = new BtDevice(null, null, null,
                "AAAADDDDAAAADDDDAAAADDDDAAAADDDDAAAADDDDAAAADDDDAAAADDDDAAAADDDDAAAADDDDAAAADDDD", -127);
        mScanResultList.add(device);
        mScanResultMap.put(device.getAddress(), device);
        mAdapter.notifyItemInserted(mScanResultList.size() - 1);
    }

    private void addBondedDevice() {
        Set<BluetoothDevice> set = BtAdapter.getBondedDevices(mContext.getApplicationContext());
        LUtil.d(TAG, "bluetooth bonded device list size:", set.size());
        for (BluetoothDevice device : set) {
            BtDevice btDevice = new BtDevice(mContext, device);
            mBondedList.add(btDevice);
            mScanResultMap.put(btDevice.getAddress(), btDevice);
        }
        mScanResultList.addAll(mBondedList);
    }

    public void setResult(BtDevice device) {
        mScanResultList.add(device);
        mScanResultMap.put(device.getAddress(), device);
        mAdapter.notifyItemInserted(mScanResultList.size() - 1);
    }

    public void updateScanStatus(boolean started) {
        mDiscovering = started;
        if (mDiscovering) {
            mScanBtn.setText("正在扫描，点击取消");
        } else {
            mScanBtn.setText("扫描周边蓝牙设备");
        }
    }

    @Override
    public void onClick(View v) {
        mDiscovering = BtAdapter.isDiscovering(mContext);
        if (!mDiscovering) {
            if (!BtAdapter.startDiscovery(mContext)) {
                LUtil.w(TAG, "start discovery failed");
            }
        } else {
            if (!BtAdapter.cancelDiscovery(mContext)) {
                LUtil.w(TAG, "cancel discovery failed");
            }
        }
    }
}
