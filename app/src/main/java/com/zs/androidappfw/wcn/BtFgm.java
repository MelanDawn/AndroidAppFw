package com.zs.androidappfw.wcn;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothStatusCodes;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseClickFragment;
import com.zs.androidappfw.utils.BuildUtil;
import com.zs.androidappfw.wcn.bt.BleAct;
import com.zs.androidappfw.wcn.bt.BredrActivity;
import com.zs.androidappfw.wcn.bt.BredrBaseAdapter;
import com.zs.androidappfw.wcn.utils.BtAdapter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BtFgm extends BaseClickFragment implements View.OnClickListener {

    private final List<Pair<String, String>> mDataList = new ArrayList<>();
    private final BredrBaseAdapter mAdapter = new BredrBaseAdapter(mDataList);

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

        RecyclerView recyclerView = view.findViewById(R.id.fg_wcn_bt_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext,
                DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BluetoothAdapter adapter = BtAdapter.getInstance(mContext);
        Pair<String, String> pair = new Pair<>("BREDR Supported", String.valueOf(BtAdapter.hasFeatureBt(mContext)));
        mDataList.add(pair);
        pair = new Pair<>("BLE Supported", String.valueOf(BtAdapter.hasFeatureBle(mContext)));
        mDataList.add(pair);
        pair = new Pair<>("Adapter Name", BtAdapter.getName(mContext));
        mDataList.add(pair);
        pair = new Pair<>("Adapter Address", BtAdapter.getAddress(mContext));
        mDataList.add(pair);
        pair = new Pair<>("Adapter Enabled", String.valueOf(adapter.isEnabled()));
        mDataList.add(pair);
        pair = new Pair<>("Adapter State", BtAdapter.getStateName(mContext));
        mDataList.add(pair);
        pair = new Pair<>("Adapter ScanMode", BtAdapter.getScanModeName(mContext));
        mDataList.add(pair);
        if (BuildUtil.versionAtLeastT()) {
            Duration duration = BtAdapter.getDiscoverableTimeout(mContext);
            pair = new Pair<>("Adapter ScanMode Timeout (s)",
                    String.valueOf(duration == null ? "UNKNOWN" : duration.getSeconds()));
            mDataList.add(pair);
        }

        Map<Integer, String> map = getBluetoothProfiles();
        for (int profile : map.keySet()) {
            pair = new Pair<>("Profile " + map.get(profile), BtAdapter.getProfileConnectionStateStr(mContext, profile));
            mDataList.add(pair);
        }

        pair = new Pair<>("MaxConnectedAudioDevices",
                String.valueOf(BtAdapter.getMaxConnectedAudioDevices(mContext)));
        mDataList.add(pair);
        pair = new Pair<>("OffloadedFilteringSupported",
                String.valueOf(adapter.isOffloadedFilteringSupported()));
        mDataList.add(pair);
        pair = new Pair<>("OffloadedScanBatchingSupported",
                String.valueOf(adapter.isOffloadedScanBatchingSupported()));
        mDataList.add(pair);
        pair = new Pair<>("MultipleAdvertisementSupported",
                String.valueOf(adapter.isMultipleAdvertisementSupported()));
        mDataList.add(pair);
        pair = new Pair<>("LeMaximumAdvertisingDataLength",
                String.valueOf(adapter.getLeMaximumAdvertisingDataLength()));
        mDataList.add(pair);
        pair = new Pair<>("Le2MPhySupported",
                String.valueOf(adapter.isLe2MPhySupported()));
        mDataList.add(pair);
        pair = new Pair<>("LeCodedPhySupported",
                String.valueOf(adapter.isLeCodedPhySupported()));
        mDataList.add(pair);
        pair = new Pair<>("LeExtendedAdvertisingSupported",
                String.valueOf(adapter.isLeExtendedAdvertisingSupported()));
        mDataList.add(pair);
        pair = new Pair<>("LePeriodicAdvertisingSupported",
                String.valueOf(adapter.isLe2MPhySupported()));
        mDataList.add(pair);
        if (BuildUtil.versionAtLeastT()) {
            pair = new Pair<>("LeAudioSupported",
                    String.valueOf(adapter.isLeAudioSupported() == BluetoothStatusCodes.FEATURE_SUPPORTED));
            mDataList.add(pair);
            pair = new Pair<>("LeAudioBroadcastSourceSupported",
                    String.valueOf(adapter.isLeAudioBroadcastSourceSupported() == BluetoothStatusCodes.FEATURE_SUPPORTED));
            mDataList.add(pair);
            pair = new Pair<>("LeAudioBroadcastAssistantSupported",
                    String.valueOf(adapter.isLeAudioBroadcastAssistantSupported() == BluetoothStatusCodes.FEATURE_SUPPORTED));
            mDataList.add(pair);
        }
    }

    private static @NonNull Map<Integer, String> getBluetoothProfiles() {
        Map<Integer, String> map = new HashMap<>();
        map.put(BluetoothProfile.HEADSET, "HEADSET Client");
        map.put(BluetoothProfile.A2DP, "A2DP Source");
        map.put(BluetoothProfile.GATT, "GATT Client");
        map.put(BluetoothProfile.GATT_SERVER, "GATT Server");
        if (BuildUtil.versionAtLeastT()) map.put(BluetoothProfile.HAP_CLIENT, "HAP Client");
        if (BuildUtil.versionAtLeastT()) map.put(BluetoothProfile.CSIP_SET_COORDINATOR, "CSIP_SET_COORDINATOR");

        map.put(BluetoothProfile.HEALTH, "HEALTH");
        map.put(BluetoothProfile.SAP, "SAP");
        if (BuildUtil.versionAtLeastP()) map.put(BluetoothProfile.HID_DEVICE, "HID_DEVICE");
        if (BuildUtil.versionAtLeastQ()) map.put(BluetoothProfile.HEARING_AID, "HEARING_AID");
        if (BuildUtil.versionAtLeastT()) map.put(BluetoothProfile.LE_AUDIO, "LE_AUDIO");
        return map;
    }

    private void initAndSetClickListener(View view, int id) {
        view.findViewById(id).setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fg_wcn_bt_classic:
                startActivity(new Intent(getActivity(), BredrActivity.class));
                break;
            case R.id.fg_wcn_bt_ble:
                startActivity(BleAct.class);
                break;
        }
    }
}
