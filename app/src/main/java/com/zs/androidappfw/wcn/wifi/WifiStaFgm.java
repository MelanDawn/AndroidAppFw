package com.zs.androidappfw.wcn.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkSuggestion;
import android.net.wifi.WpsInfo;
import android.net.wifi.hotspot2.PasspointConfiguration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseClickFragment;
import com.zs.androidappfw.utils.BuildUtil;
import com.zs.androidappfw.utils.BundleUtil;
import com.zs.androidappfw.utils.LUtil;
import com.zs.androidappfw.utils.PermissionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class WifiStaFgm extends BaseClickFragment {

    private WifiManager mWifiManager;

    private final BroadcastReceiver mReceiver = new WifiStaReceiver();

    private final Executor mExecutor = Executors.newFixedThreadPool(4);

    private final WpsCb mWpsCb = new WpsCb();
    private final LocalOnlyHotspotCb mLocalOnlyHotspotCb = new LocalOnlyHotspotCb();

    private SubsystemRestartTrackingCb mSubsystemCb;
    private ScanResultsCb mScanResultsCb;
    private SuggestionUserApprovalStatusCb mSuggestionUserApprovalStatusCb;
    private SuggestionConnectionStatusCb mSuggestionConnectionStatusCb;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fgm_wifi_sta, container, false);
        initAndSetClickListener(view, new int[]{
                R.id.fg_wifi_sta_switch_btn,
                R.id.fg_wifi_sta_scan_btn,
                R.id.fg_wifi_sta_reassociate_btn,
                R.id.fg_wifi_sta_disconnect_btn,
                R.id.fg_wifi_sta_reconnect_btn,
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (BuildUtil.versionAtLeastS()) {
            mSuggestionUserApprovalStatusCb = new SuggestionUserApprovalStatusCb();
            mWifiManager.addSuggestionUserApprovalStatusListener(mExecutor, mSuggestionUserApprovalStatusCb);
            mSubsystemCb = new SubsystemRestartTrackingCb();
            mWifiManager.registerSubsystemRestartTrackingCallback(mExecutor, mSubsystemCb);
        }
        if (BuildUtil.versionAtLeastR()) {
            mScanResultsCb = new ScanResultsCb();
            mWifiManager.registerScanResultsCallback(mExecutor, mScanResultsCb);
            if (PermissionUtil.checkLocationPermission(mContext)) {
                mSuggestionConnectionStatusCb = new SuggestionConnectionStatusCb();
                mWifiManager.addSuggestionConnectionStatusListener(mExecutor, mSuggestionConnectionStatusCb);
            }
        }

        // fa song guang bo
//        intentFilter.addAction(WifiManager.ACTION_PICK_WIFI_NETWORK);
//        intentFilter.addAction(WifiManager.ACTION_REQUEST_SCAN_ALWAYS_AVAILABLE);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.NETWORK_IDS_CHANGED_ACTION);
        if (BuildUtil.versionAtLeastQ())
            intentFilter.addAction(WifiManager.ACTION_WIFI_NETWORK_SUGGESTION_POST_CONNECTION);
        if (BuildUtil.versionAtLeastR())
            intentFilter.addAction(WifiManager.ACTION_WIFI_SCAN_AVAILABILITY_CHANGED);
        intentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiManager.RSSI_CHANGED_ACTION);
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION);
        intentFilter.addAction(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION);
        mContext.registerReceiver(mReceiver, intentFilter);

        WpsInfo wpsInfo = new WpsInfo();
        wpsInfo.pin = "";
        wpsInfo.BSSID = "";
        wpsInfo.setup = 1;
        mWifiManager.startWps(wpsInfo, mWpsCb);

        if (PermissionUtil.checkNearbyWifiPermission(mContext)) {
            mWifiManager.startLocalOnlyHotspot(mLocalOnlyHotspotCb, new Handler());
        }

        List<WifiConfiguration> wifiConfigList = mWifiManager.getConfiguredNetworks();
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.SSID = "Ktz";

        // permission todo
//        if (BuildUtil.versionAtLeastS()) mWifiManager.addNetworkPrivileged(wifiConfiguration);
        mWifiManager.addNetwork(wifiConfiguration);
        mWifiManager.updateNetwork(wifiConfiguration);
        mWifiManager.removeNetwork(1);

        if (BuildUtil.versionAtLeastR()) {
            List<WifiNetworkSuggestion> list = new ArrayList<>();
            mWifiManager.getMaxNumberOfNetworkSuggestionsPerApp();
            mWifiManager.addNetworkSuggestions(list);
            List<WifiNetworkSuggestion> tmp = mWifiManager.getNetworkSuggestions();
            mWifiManager.removeNetworkSuggestions(tmp);
            if (BuildUtil.versionAtLeastT()) {
                mWifiManager.removeNetworkSuggestions(tmp, WifiManager.ACTION_REMOVE_SUGGESTION_LINGER);
                mWifiManager.removeNetworkSuggestions(tmp, WifiManager.ACTION_REMOVE_SUGGESTION_DISCONNECT);
            }
        }

        // permission todo
//        if (BuildUtil.versionAtLeastT()) mWifiManager.allowAutojoinGlobal(true);
//        if (BuildUtil.versionAtLeastT()) mWifiManager.queryAutojoinGlobal(mExecutor, consumer -> {
//
//        });

//        mWifiManager.addOrUpdatePasspointConfiguration();
//        mWifiManager.removePasspointConfiguration();
//        mWifiManager.flushPasspointAnqpCache();
//        mWifiManager.getPasspointConfigurations();

        mWifiManager.createWifiLock(WifiManager.WIFI_MODE_FULL_HIGH_PERF, "");
        if (BuildUtil.versionAtLeastQ())
            mWifiManager.createWifiLock(WifiManager.WIFI_MODE_FULL_LOW_LATENCY, "");
        mWifiManager.createWifiLock(WifiManager.WIFI_MODE_FULL, "");
        mWifiManager.createMulticastLock("");

        if (BuildUtil.versionAtLeastR()) {
            for (int i = -127; i <= 0; i++) {
                LUtil.d(TAG, "calculateSignalLevel " + i + ", " + mWifiManager.calculateSignalLevel(i));
            }
            LUtil.d(TAG, "getMaxSignalLevel " + mWifiManager.getMaxSignalLevel());
        }

        mWifiManager.enableNetwork(1, false);
        mWifiManager.disableNetwork(1);


        // permission todo
//        if (BuildUtil.versionAtLeastS()) {
//            mWifiManager.getCallerConfiguredNetworks();
//            mWifiManager.removeNonCallerConfiguredNetworks();
//        }
//        if (BuildUtil.versionAtLeastT()) mWifiManager.reportCreateInterfaceImpact();

//        new Thread(() -> {
//                try {
//                    mWifiManager.setTdlsEnabled(InetAddress.getByName("https://www.baidu.com"), true);
//                } catch (UnknownHostException e) {
//                    LUtil.w(TAG, e);
//                    e.printStackTrace();
//                }
//        }).start();

        mWifiManager.setTdlsEnabledWithMacAddress("AA:BB:CC:DD:EE:FF", true);

        if (BuildUtil.versionAtLeastT()) mWifiManager.getStaConcurrencyForMultiInternetMode();
        WifiInfo wifiInfo = mWifiManager.getConnectionInfo();
        DhcpInfo dhcpInfo = mWifiManager.getDhcpInfo();

//        mWifiManager.isWifiDisplayR2Supported();
//        mWifiManager.isWifiEnabled();
//        mWifiManager.is5GHzBandSupported();
//        mWifiManager.is6GHzBandSupported();
//        mWifiManager.is60GHzBandSupported();
//        mWifiManager.is24GHzBandSupported();
//        mWifiManager.isAutoWakeupEnabled();
//        mWifiManager.isBridgedApConcurrencySupported();
//        mWifiManager.isCarrierNetworkOffloadEnabled();
//        mWifiManager.isDeviceToApRttSupported();
//        mWifiManager.isDecoratedIdentitySupported();
//        mWifiManager.isEasyConnectDppAkmSupported();
//        mWifiManager.isEasyConnectEnrolleeResponderModeSupported();
//        mWifiManager.isEasyConnectSupported();
//        mWifiManager.isEnhancedOpenSupported();
//        mWifiManager.isEnhancedPowerReportingSupported();
//        mWifiManager.isMakeBeforeBreakWifiSwitchingSupported();
//        mWifiManager.isP2pSupported();
//        mWifiManager.isPasspointTermsAndConditionsSupported();
//        mWifiManager.isPreferredNetworkOffloadSupported();
//        mWifiManager.isScanAlwaysAvailable();
//        mWifiManager.isScanThrottleEnabled();
//        mWifiManager.isStaApConcurrencySupported();
//        mWifiManager.isStaBridgedApConcurrencySupported();
//        mWifiManager.isStaConcurrencyForLocalOnlyConnectionsSupported();
//        mWifiManager.isStaConcurrencyForMultiInternetSupported();
//        mWifiManager.isTdlsSupported();
//        mWifiManager.isTrustOnFirstUseSupported();
//        mWifiManager.isWapiSupported();
//        mWifiManager.isWifiPasspointEnabled();
//        mWifiManager.isWifiStandardSupported();
//        mWifiManager.isWpa3SaeH2eSupported();
//        mWifiManager.isWpa3SaeSupported();
//        mWifiManager.isWpa3SaePublicKeySupported();
//        mWifiManager.isWpa3SuiteBSupported();

        // Deprecated
//        mWifiManager.pingSupplicant();
//        mWifiManager.saveConfiguration();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (BuildUtil.versionAtLeastR()) {
            mWifiManager.unregisterScanResultsCallback(mScanResultsCb);
            mScanResultsCb = null;
            if (PermissionUtil.checkLocationPermission(mContext)) {
                mWifiManager.removeSuggestionConnectionStatusListener(mSuggestionConnectionStatusCb);
                mSuggestionConnectionStatusCb = null;
            }
        }
        if (BuildUtil.versionAtLeastS()) {
            mWifiManager.removeSuggestionUserApprovalStatusListener(mSuggestionUserApprovalStatusCb);
            mSuggestionUserApprovalStatusCb = null;
            mWifiManager.unregisterSubsystemRestartTrackingCallback(mSubsystemCb);
            mSubsystemCb = null;
        }

        mWifiManager.cancelWps(mWpsCb);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.fg_wifi_sta_switch_btn) {
            LUtil.d(TAG, "setWifiEnabled", mWifiManager.setWifiEnabled(true));
        } else if (id == R.id.fg_wifi_sta_scan_btn) {
            LUtil.d(TAG, "startScan", mWifiManager.startScan());
        } else if (id == R.id.fg_wifi_sta_reassociate_btn) {
            LUtil.d(TAG, "reassociate", mWifiManager.reassociate());
        } else if (id == R.id.fg_wifi_sta_disconnect_btn) {
            LUtil.d(TAG, "disconnect", mWifiManager.disconnect());
        } else if (id == R.id.fg_wifi_sta_reconnect_btn) {
            LUtil.d(TAG, "reconnect", mWifiManager.reconnect());
        } else {

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.S)
    private class SuggestionUserApprovalStatusCb implements WifiManager.SuggestionUserApprovalStatusListener {
        @Override
        public void onUserApprovalStatusChange(int i) {
            LUtil.d(WifiStaFgm.this.TAG, i);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    private class SuggestionConnectionStatusCb implements WifiManager.SuggestionConnectionStatusListener {
        @Override
        public void onConnectionStatus(@NonNull WifiNetworkSuggestion wifiNetworkSuggestion, int i) {
            LUtil.d(WifiStaFgm.this.TAG, i, wifiNetworkSuggestion.getSsid());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.S)
    private class SubsystemRestartTrackingCb extends WifiManager.SubsystemRestartTrackingCallback {
        @Override
        public void onSubsystemRestarting() {
            LUtil.d(WifiStaFgm.this.TAG);
        }

        @Override
        public void onSubsystemRestarted() {
            LUtil.d(WifiStaFgm.this.TAG);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    private class ScanResultsCb extends WifiManager.ScanResultsCallback {
        @Override
        public void onScanResultsAvailable() {
            if (PermissionUtil.checkLocationPermission(mContext)) {
                List<ScanResult> scanResults = mWifiManager.getScanResults();
                for (ScanResult scanResult : scanResults) {
                    scanResult.toString();
                }
            }
        }
    }

    private class WpsCb extends WifiManager.WpsCallback {

        @Override
        public void onStarted(String s) {
            LUtil.d(WifiStaFgm.this.TAG, s);
        }

        @Override
        public void onSucceeded() {
            LUtil.d(WifiStaFgm.this.TAG);
        }

        @Override
        public void onFailed(int i) {
            LUtil.d(WifiStaFgm.this.TAG, i);
        }
    }

    private class LocalOnlyHotspotCb extends WifiManager.LocalOnlyHotspotCallback {
        @Override
        public void onStarted(WifiManager.LocalOnlyHotspotReservation reservation) {
            super.onStarted(reservation);
            LUtil.d(WifiStaFgm.this.TAG, reservation);
        }

        @Override
        public void onStopped() {
            super.onStopped();
            LUtil.d(WifiStaFgm.this.TAG);
        }

        @Override
        public void onFailed(int reason) {
            super.onFailed(reason);
            LUtil.d(WifiStaFgm.this.TAG, reason);
        }
    }

    private class WifiStaReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            LUtil.d(WifiStaFgm.this.TAG, action);
            LUtil.d(WifiStaFgm.this.TAG, " --> ", BundleUtil.getContent(intent.getExtras()));
            if (WifiManager.NETWORK_IDS_CHANGED_ACTION.equals(action)) {
                WifiInfo wifiInfo = mWifiManager.getConnectionInfo();
                LUtil.d(WifiStaFgm.this.TAG, wifiInfo);
                List<PasspointConfiguration> passpointConfigurations = mWifiManager.getPasspointConfigurations();
                LUtil.d(WifiStaFgm.this.TAG, passpointConfigurations);

                if (PermissionUtil.checkLocationPermission(context)) {
                    List<WifiConfiguration> configuredNetworks = mWifiManager.getConfiguredNetworks();
                    LUtil.d(WifiStaFgm.this.TAG, configuredNetworks);
                    if (BuildUtil.versionAtLeastS()) {
                        List<WifiConfiguration> callerConfiguredNetworks = mWifiManager.getCallerConfiguredNetworks();
                        LUtil.d(WifiStaFgm.this.TAG, callerConfiguredNetworks);
                    }
                }
            } else if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(action)) {
                // Wi-Fi STA 连接对应的网络对象（层 3）
                NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
                LUtil.d(WifiStaFgm.this.TAG, info);
            } else if (WifiManager.RSSI_CHANGED_ACTION.equals(action)) {
                // 已连接Wi-FI的 RSSI
                int rssi = intent.getIntExtra(WifiManager.EXTRA_NEW_RSSI, -127);
                LUtil.d(WifiStaFgm.this.TAG, rssi);
            } else if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equals(action)) {
                // 扫描结束
                boolean scanSucceed = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false);
                LUtil.d(WifiStaFgm.this.TAG, scanSucceed);
            } else if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(action)) {
                // Wi-Fi STA 状态变化
                int state = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);
                int previousState = intent.getIntExtra(WifiManager.EXTRA_PREVIOUS_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);
                int wifiState = mWifiManager.getWifiState();
                LUtil.d(WifiStaFgm.this.TAG, previousState, "->", state, wifiState);
            } else if (WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION.equals(action)) {
                // Supplicant 状态
                boolean supplicantConnected = intent.getBooleanExtra(WifiManager.EXTRA_SUPPLICANT_CONNECTED, false);
                LUtil.d(WifiStaFgm.this.TAG, supplicantConnected);
            } else if (WifiManager.SUPPLICANT_STATE_CHANGED_ACTION.equals(action)) {
                // Wi-Fi STA 连接过程的状态变化（层 3）
                SupplicantState state = intent.getParcelableExtra(WifiManager.EXTRA_NEW_STATE);
                LUtil.d(WifiStaFgm.this.TAG, state);
            } else {
                if (BuildUtil.versionAtLeastR()
                        && WifiManager.ACTION_WIFI_SCAN_AVAILABILITY_CHANGED.equals(action)) {
                    // Wi-Fi STA 关闭时仍旧可以扫描与否
                    boolean scanAvailable = intent.getBooleanExtra(WifiManager.EXTRA_SCAN_AVAILABLE, false);
                    LUtil.d(WifiStaFgm.this.TAG, scanAvailable);
                } else if (BuildUtil.versionAtLeastQ()
                        && WifiManager.ACTION_WIFI_NETWORK_SUGGESTION_POST_CONNECTION.equals(action)) {
                    WifiNetworkSuggestion suggestion = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_SUGGESTION);
                    LUtil.d(WifiStaFgm.this.TAG, suggestion);
                }
            }
        }
    }
}
