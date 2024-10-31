package com.zs.androidappfw.wcn.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.net.wifi.rtt.RangingRequest;
import android.net.wifi.rtt.RangingResult;
import android.net.wifi.rtt.RangingResultCallback;
import android.net.wifi.rtt.WifiRttManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseClickFragment;
import com.zs.androidappfw.utils.BuildUtil;
import com.zs.androidappfw.utils.LUtil;
import com.zs.androidappfw.utils.PermissionUtil;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class WifiRttFgm extends BaseClickFragment {

    private final Executor mExecutor = Executors.newFixedThreadPool(2);

    private final BroadcastReceiver mReceiver = new WifiRttReceiver();

    WifiManager mWifiManager;
    private WifiRttManager mWifiRttManager;
    private RangingResultCallback mCallback;

    private TextView mStatusTv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        if (BuildUtil.versionAtLeastP()) {
            mWifiRttManager = (WifiRttManager) mContext.getSystemService(Context.WIFI_RTT_RANGING_SERVICE);
            mCallback = new RangingResultCb();

            IntentFilter filter = new IntentFilter(WifiRttManager.ACTION_WIFI_RTT_STATE_CHANGED);
            mContext.registerReceiver(mReceiver, filter);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fgm_wifi_rtt, container, false);
        initAndSetClickListener(view, new int[]{
                R.id.fg_wifi_rtt_ranging,
        });
        mStatusTv = view.findViewById(R.id.fg_wifi_rtt_status);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        updateStatus();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (BuildUtil.versionAtLeastP()) {
            mContext.unregisterReceiver(mReceiver);
        }
    }

    private void updateStatus() {
        String text;
        if (BuildUtil.versionAtLeastP() && mWifiManager.isDeviceToApRttSupported()) {
                text = String.format(Locale.getDefault(), "isDeviceToApRttSupported = %b \n "
                                + "isAvailable = %b", mWifiManager.isDeviceToApRttSupported(),
                        mWifiRttManager.isAvailable());
        } else {
            text = String.format("DeviceToApRttSupported = %b", mWifiManager.isDeviceToApRttSupported());
        }
        mStatusTv.setText(text);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.fg_wifi_rtt_ranging) {
            if (BuildUtil.versionAtLeastP()) {
                if (!mWifiManager.isDeviceToApRttSupported()) {
                    LUtil.w(TAG, "RTT not supported");
                    return;
                }
                LUtil.i(TAG, "isAvailable", mWifiRttManager.isAvailable());

                RangingRequest request = new RangingRequest.Builder()
//                        .addAccessPoint()
                        .build();

                if (PermissionUtil.checkNearbyWifiPermission(mContext)
                        || PermissionUtil.checkLocationPermission(mContext)) {
                    mWifiRttManager.startRanging(request, mExecutor, mCallback);
                }
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    private class RangingResultCb extends RangingResultCallback {

        @Override
        public void onRangingFailure(int i) {
            LUtil.d(TAG, i);
        }

        @Override
        public void onRangingResults(@NonNull List<RangingResult> list) {
            LUtil.d(TAG, list.size());
            for (RangingResult result : list) {
                LUtil.d(TAG, result.getDistanceMm());
                LUtil.d(TAG, result.getMacAddress());
                LUtil.d(TAG, result.getDistanceStdDevMm());
                LUtil.d(TAG, result.getNumAttemptedMeasurements());
                LUtil.d(TAG, result.getNumSuccessfulMeasurements());
                LUtil.d(TAG, result.getPeerHandle());
                LUtil.d(TAG, result.getRangingTimestampMillis());
                LUtil.d(TAG, result.getRssi());
                LUtil.d(TAG, result.getStatus());
                if (BuildUtil.versionAtLeastQ())
                    LUtil.d(TAG, result.getUnverifiedResponderLocation());
                if (BuildUtil.versionAtLeastS())
                    LUtil.d(TAG, result.is80211mcMeasurement());
            }
        }
    }

    private class WifiRttReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (WifiRttManager.ACTION_WIFI_RTT_STATE_CHANGED.equals(intent.getAction())) {
                updateStatus();
            }
        }
    }
}
