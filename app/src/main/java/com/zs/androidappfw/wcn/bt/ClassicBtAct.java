package com.zs.androidappfw.wcn.bt;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;
import com.zs.androidappfw.utils.LUtil;
import com.zs.androidappfw.wcn.utils.BluetoothUtils;

import java.util.Set;

public class ClassicBtAct extends BaseTitleActivity {

    private static final int REQUEST_ENABLE_BT = 1;
    private static final int REQUEST_DISCOVERABLE_BT = 2;

    private BluetoothAdapter mAdapter;

    private Receiver mReceiver = new Receiver();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_wcn_bt_classic);

        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        filter.addAction(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED);
        filter.addAction(BluetoothAdapter.ACTION_LOCAL_NAME_CHANGED);
        filter.addAction(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        filter.addAction(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        filter.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);

        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothDevice.ACTION_CLASS_CHANGED);
        filter.addAction(BluetoothDevice.ACTION_NAME_CHANGED);
        filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        filter.addAction(BluetoothDevice.ACTION_PAIRING_REQUEST);
        filter.addAction(BluetoothDevice.ACTION_UUID);
        filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED);
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);

        registerReceiver(mReceiver, filter);

        mAdapter = BluetoothUtils.getInstance(this);

        LUtil.d(mTag, "bt feature:", BluetoothUtils.hasFeatureBt(this));
        LUtil.d(mTag, "ble feature", BluetoothUtils.hasFeatureBle(this));
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_feature_classic_bt;
    }

    public void getBoundedDevices(View v) {
        Set<BluetoothDevice> set = mAdapter.getBondedDevices();
        LUtil.d(mTag, "bounded device size:", set.size());
        for (BluetoothDevice device : set) {
            LUtil.d(mTag, BluetoothUtils.getContent(device));
        }
    }

    public void enable(View v) {
        mAdapter.enable();
    }

    public void enableByBroadcast(View v) {
        if (!BluetoothUtils.getInstance(this).isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }

    public void disable(View v) {
        mAdapter.disable();
    }

    public void startDiscovery(View v) {
        mAdapter.startDiscovery();
    }

    public void cancelDiscovery(View v) {
        mAdapter.cancelDiscovery();
    }

    public void setDiscoverable(View v) {
        if (BluetoothUtils.getInstance(this).isEnabled()) {
            Intent discoverIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 240);
            startActivityForResult(discoverIntent, REQUEST_DISCOVERABLE_BT);
        }
    }

    public void setName(View v) {
        mAdapter.setName("");
    }

    public void getProfileProxy(View v) {
        mAdapter.getProfileProxy(this, new BluetoothProfile.ServiceListener() {
            @Override
            public void onServiceConnected(int profile, BluetoothProfile proxy) {
                LUtil.d(mTag, profile, proxy);
//                (BluetoothA2dp) proxy;
//                (BluetoothHeadset) profile;
            }
            @Override
            public void onServiceDisconnected(int profile) {
                LUtil.d(mTag, profile);
            }
            // only for a2dp and headset
        }, BluetoothProfile.A2DP);
    }

    public void closeProfileProxy(View v) {
        //mAdapter.closeProfileProxy();
    }

    public void listenUsingL2capChannel(View v) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            BluetoothServerSocket socket = null;
//            try {
//                socket = mAdapter.listenUsingL2capChannel();
//            } catch (IOException e) {
//                LUtil.e(TAG, "socket:", socket);
//            } finally {
//                if (socket != null) {
//                    try {
//                        socket.close();
//                    } catch (IOException e) {
//                        LUtil.e(TAG, "socket:", socket);
//                    }
//                }
//            }
//        }
    }

    public void listenUsingInsecureL2capChannel(View v) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            BluetoothServerSocket socket = null;
//            try {
//                socket = mAdapter.listenUsingInsecureL2capChannel();
//            } catch (IOException e) {
//                LUtil.e(TAG, "socket:", socket);
//            } finally {
//                if (socket != null) {
//                    try {
//                        socket.close();
//                    } catch (IOException e) {
//                        LUtil.e(TAG, "socket:", socket);
//                    }
//                }
//            }
//        }
    }

    public void listenUsingInsecureRfcommWithServiceRecord(View v) {
//        mAdapter.listenUsingInsecureRfcommWithServiceRecord();
    }

    public void listenUsingRfcommWithServiceRecord(View v) {
//        mAdapter.listenUsingRfcommWithServiceRecord();
    }

    private class Receiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            LUtil.d(mTag, "Receiver", action);
            if (action == null) return;
            BluetoothDevice device = null;
            BluetoothClass clazz = null;
            String name = null;
            short rssi = 0;
            switch (action) {
                case BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED:
                    int connectionState = intent.getIntExtra(BluetoothAdapter.EXTRA_CONNECTION_STATE, -1);
                    int connectionStatePre = intent.getIntExtra(BluetoothAdapter.EXTRA_PREVIOUS_CONNECTION_STATE, -1);
                    device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    LUtil.d(mTag, "class:", connectionState, connectionStatePre, BluetoothUtils.getContent(device));
                    break;
                case BluetoothAdapter.ACTION_DISCOVERY_STARTED:
                case BluetoothAdapter.ACTION_DISCOVERY_FINISHED:
                    break;
                case BluetoothAdapter.ACTION_LOCAL_NAME_CHANGED:
                    String localName = intent.getStringExtra(BluetoothAdapter.EXTRA_LOCAL_NAME);
                    LUtil.d(mTag, "localName:", localName);
                    break;
                case BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE:
                    int duration = intent.getIntExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, -1);
                    LUtil.d(mTag, "duration:", duration);
                    break;
                case BluetoothAdapter.ACTION_REQUEST_ENABLE:
                    break;
                case BluetoothAdapter.ACTION_SCAN_MODE_CHANGED:
                    int scanMode = intent.getIntExtra(BluetoothAdapter.EXTRA_SCAN_MODE, -1);
                    int scanModePre = intent.getIntExtra(BluetoothAdapter.EXTRA_PREVIOUS_SCAN_MODE, -1);
                    LUtil.d(mTag, "scanMode:", scanMode, "scanModePre:", scanModePre);
                    break;
                case BluetoothAdapter.ACTION_STATE_CHANGED:
                    int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1);
                    int statePrevious = intent.getIntExtra(BluetoothAdapter.EXTRA_PREVIOUS_STATE, -1);
                    LUtil.d(mTag, "state:", state, "previousState:", statePrevious);
                    break;
                case BluetoothDevice.ACTION_ACL_CONNECTED:
                case BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED:
                case BluetoothDevice.ACTION_ACL_DISCONNECTED:
                    device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    LUtil.d(mTag, "class:", BluetoothUtils.getContent(device));
                    break;
                case BluetoothDevice.ACTION_BOND_STATE_CHANGED:
                    int bondState = intent.getIntExtra(BluetoothDevice.EXTRA_BOND_STATE, -1);
                    int bondPreviousState = intent.getIntExtra(BluetoothDevice.EXTRA_PREVIOUS_BOND_STATE, -1);
                    device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    LUtil.d(mTag, "bond state:", bondState, " previous state:", bondPreviousState, BluetoothUtils.getContent(device));
                    break;
                case BluetoothDevice.ACTION_CLASS_CHANGED:
                    clazz = intent.getParcelableExtra(BluetoothDevice.EXTRA_CLASS);
                    device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    LUtil.d(mTag, "class:", BluetoothUtils.getContent(clazz), BluetoothUtils.getContent(device));
                    break;
                case BluetoothDevice.ACTION_FOUND:
                    device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    clazz = intent.getParcelableExtra(BluetoothDevice.EXTRA_CLASS);
                    name = intent.getStringExtra(BluetoothDevice.EXTRA_NAME);
                    rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI, (short) 0);
                    LUtil.d(mTag, BluetoothUtils.getContent(device), BluetoothUtils.getContent(clazz), name, rssi);
                    break;
                case BluetoothDevice.ACTION_NAME_CHANGED:
                    name = intent.getStringExtra(BluetoothDevice.EXTRA_NAME);
                    device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    LUtil.d(mTag, "name:", name, BluetoothUtils.getContent(device));
                    break;
                case BluetoothDevice.ACTION_PAIRING_REQUEST:
                    int paringVariant = intent.getIntExtra(BluetoothDevice.EXTRA_PAIRING_VARIANT, -1);
                    String paringKey = intent.getStringExtra(BluetoothDevice.EXTRA_PAIRING_KEY);
                    int reason = intent.getIntExtra("android.bluetooth.device.extra.REASON", -1);
                    LUtil.d(mTag, "paring:", paringVariant, paringKey, reason);
                    break;
                case BluetoothDevice.ACTION_UUID:
                    Parcelable[] uuids = intent.getParcelableArrayExtra(BluetoothDevice.EXTRA_UUID);
                    device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    LUtil.d(mTag, "uuids:", uuids, BluetoothUtils.getContent(device));
                    break;
            }
        }
    }
    public void printSimpleMethod(View view) {
        // not recommended
        // advertise use-case: AdvertisingIdClient.Info.getId();
        // analytics use-case: InstanceId.getId();
        LUtil.d(mTag, "address =", mAdapter.getAddress());
        LUtil.d(mTag, "name =", mAdapter.getName());
        LUtil.d(mTag, "scanMode =", mAdapter.getScanMode());
        LUtil.d(mTag, "state =", mAdapter.getState());
        LUtil.d(mTag, "advertiser =", mAdapter.getBluetoothLeAdvertiser());
        LUtil.d(mTag, "belScanner =", mAdapter.getBluetoothLeScanner());
        LUtil.d(mTag, "a2dpConnState =", mAdapter.getProfileConnectionState(BluetoothProfile.A2DP));
        LUtil.d(mTag, "headsetConnState =", mAdapter.getProfileConnectionState(BluetoothProfile.HEADSET));
//        LUtil.d(TAG, "remoteDevice[] =", mAdapter.getRemoteDevice("90:F0:52:8E:5A:8E".getBytes()));
//        LUtil.d(TAG, "remoteDeviceStr =", mAdapter.getRemoteDevice("90:F0:52:8E:5A:8E"));

        LUtil.d(mTag, "discovering =", mAdapter.isDiscovering());
        LUtil.d(mTag, "enabled =", mAdapter.isEnabled());
        LUtil.d(mTag, "multipleAdvertisementSupported =", mAdapter.isMultipleAdvertisementSupported());
        LUtil.d(mTag, "offloadedFilteringSupported =", mAdapter.isOffloadedFilteringSupported());
        LUtil.d(mTag, "offloadedScanBatchingSupported =", mAdapter.isOffloadedScanBatchingSupported());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LUtil.d(mTag, "maxLength =", mAdapter.getLeMaximumAdvertisingDataLength());

            LUtil.d(mTag, "Le2MPhySupported =", mAdapter.isLe2MPhySupported());
            LUtil.d(mTag, "LeCodedPhySupported =", mAdapter.isLeCodedPhySupported());
            LUtil.d(mTag, "LeExtendedAdvertisingSupported =", mAdapter.isLeExtendedAdvertisingSupported());
            LUtil.d(mTag, "LePeriodicAdvertisingSupported =", mAdapter.isLePeriodicAdvertisingSupported());
        }
    }
}
