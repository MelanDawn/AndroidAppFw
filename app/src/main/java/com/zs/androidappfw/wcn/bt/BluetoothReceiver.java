package com.zs.androidappfw.wcn.bt;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothHearingAid;
import android.bluetooth.BluetoothHidDevice;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;

import com.zs.androidappfw.base.BaseBroadcastReceiver;
import com.zs.androidappfw.utils.BuildUtil;
import com.zs.androidappfw.utils.LUtil;
import com.zs.androidappfw.wcn.utils.BtAdapter;
import com.zs.androidappfw.wcn.utils.BtDevice;

import java.util.HashSet;
import java.util.Set;

public class BluetoothReceiver extends BaseBroadcastReceiver {

    static final String ACTION_BLUETOOTH_HID_HOST = "android.bluetooth.input.profile.action.CONNECTION_STATE_CHANGED";

    static int MSG_ADAPTER_CONNECTION_STATE_CHANGED = 1;
    static int MSG_ADAPTER_DISCOVERY_STARTED = 2;
    static int MSG_ADAPTER_DISCOVERY_FINISHED = 3;
    static int MSG_ADAPTER_NAME_CHANGED = 4;
    static int MSG_ADAPTER_SCAN_MODE_CHANGED = 5;
    static int MSG_ADAPTER_STATE_CHANGED = 6;
    static int MSG_ADAPTER_REQUEST_DISCOVERABLE = 7;
    static int MSG_ADAPTER_REQUEST_ENABLE = 8;

    static int MSG_DEVICE_NAME_CHANGED = 11;
    static int MSG_DEVICE_ALIAS_CHANGED = 12;
    static int MSG_DEVICE_CLASS_CHANGED = 13;
    static int MSG_DEVICE_BOND_STATE_CHANGED = 14;
    static int MSG_DEVICE_FOUND = 15;
    static int MSG_DEVICE_UUID = 16;
    static int MSG_DEVICE_ACL_CONNECTED = 17;
    static int MSG_DEVICE_ACL_DISCONNECTED = 18;
    static int MSG_DEVICE_ACL_DISCONNECT_REQUESTED = 19;
    static int MSG_DEVICE_PAIRING_REQUEST = 20;

    static int MSG_PROFILE_A2DP_SOURCE_CONNECTION_STATE_CHANGED = 31;
    static int MSG_PROFILE_A2DP_SOURCE_PLAYING_STATE_CHANGED = 32;

    static int MSG_PROFILE_HFP_AG_CONNECTION_STATE_CHANGED = 41;
    static int MSG_PROFILE_HFP_AG_AUDIO_STATE_CHANGED = 42;
    static int MSG_PROFILE_HFP_AG_VENDOR_SPECIFIC_HEADSET_EVENT = 43;

    static int MSG_PROFILE_HID_HOST_CONNECTION_STATE_CHANGED = 51;
    static int MSG_PROFILE_HID_DEVICE_CONNECTION_STATE_CHANGED = 56;

    static int MSG_PROFILE_HEARING_AID_CONNECTION_STATE_CHANGED = 61;

    private final Set<Handler> mHandlerSet = new HashSet<>();

    public static IntentFilter getIntentFilter() {
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
        if (BuildUtil.versionAtLeastR()) {
            filter.addAction(BluetoothDevice.ACTION_ALIAS_CHANGED);
        }

        filter.addAction(BluetoothA2dp.ACTION_CONNECTION_STATE_CHANGED);
        filter.addAction(BluetoothA2dp.ACTION_PLAYING_STATE_CHANGED);
        filter.addAction(BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED);
        filter.addAction(BluetoothHeadset.ACTION_AUDIO_STATE_CHANGED);
        filter.addAction(BluetoothHeadset.ACTION_VENDOR_SPECIFIC_HEADSET_EVENT);
        filter.addAction(ACTION_BLUETOOTH_HID_HOST);
        if (BuildUtil.versionAtLeastP()) {
            filter.addAction(BluetoothHidDevice.ACTION_CONNECTION_STATE_CHANGED);
        }
        if (BuildUtil.versionAtLeastQ()) {
            filter.addAction(BluetoothHearingAid.ACTION_CONNECTION_STATE_CHANGED);
        }
        return filter;
    }

    public synchronized void registerHandler(Handler handler) {
        mHandlerSet.add(handler);
    }

    public synchronized void unregisterHandler(Handler handler) {
        mHandlerSet.remove(handler);
    }

    private synchronized void dispatchMessage(int what, int a, int b, Object obj) {
        Message message = Message.obtain();
        message.what = what;
        message.arg1 = a;
        message.arg2 = b;
        message.obj = obj;
        for (Handler handler : mHandlerSet) {
            handler.sendMessage(message);
        }
    }

    private void dispatchMessage(int what) {
        dispatchMessage(what, 0, 0, null);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        LUtil.d(mTag, "Receive:", action);
        if (action == null) return;
        BluetoothDevice device = null;
        BluetoothClass clazz = null;
        String name = null;
        short rssi = -127;
        switch (action) {
            case BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED:
                int connectionState = intent.getIntExtra(BluetoothAdapter.EXTRA_CONNECTION_STATE, -1);
                int connectionStatePre = intent.getIntExtra(BluetoothAdapter.EXTRA_PREVIOUS_CONNECTION_STATE, -1);
                device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                LUtil.d(mTag, "class:", connectionState, connectionStatePre, BtAdapter.getContent(context, device));
                dispatchMessage(MSG_ADAPTER_CONNECTION_STATE_CHANGED, connectionStatePre, connectionState, device);
                break;
            case BluetoothAdapter.ACTION_DISCOVERY_STARTED:
                dispatchMessage(MSG_ADAPTER_DISCOVERY_STARTED);
                break;
            case BluetoothAdapter.ACTION_DISCOVERY_FINISHED:
                dispatchMessage(MSG_ADAPTER_DISCOVERY_FINISHED);
                break;
            case BluetoothAdapter.ACTION_LOCAL_NAME_CHANGED:
                String localName = intent.getStringExtra(BluetoothAdapter.EXTRA_LOCAL_NAME);
                LUtil.d(mTag, "localName:", localName);
                dispatchMessage(MSG_ADAPTER_NAME_CHANGED, 0, 0, localName);
                break;
            case BluetoothAdapter.ACTION_SCAN_MODE_CHANGED:
                int scanMode = intent.getIntExtra(BluetoothAdapter.EXTRA_SCAN_MODE, -1);
                int scanModePre = intent.getIntExtra(BluetoothAdapter.EXTRA_PREVIOUS_SCAN_MODE, -1);
                LUtil.d(mTag, "scanMode:", scanMode, "scanModePre:", scanModePre);
                dispatchMessage(MSG_ADAPTER_SCAN_MODE_CHANGED, scanModePre, scanMode, null);
                break;
            case BluetoothAdapter.ACTION_STATE_CHANGED:
                int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1);
                int statePrevious = intent.getIntExtra(BluetoothAdapter.EXTRA_PREVIOUS_STATE, -1);
                LUtil.d(mTag, "state:", state, "previousState:", statePrevious);
                dispatchMessage(MSG_ADAPTER_STATE_CHANGED, statePrevious, state, null);
                break;
            case BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE:
                int duration = intent.getIntExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, -1);
                LUtil.d(mTag, "duration:", duration);
                dispatchMessage(MSG_ADAPTER_REQUEST_DISCOVERABLE, duration, 0, null);
                break;
            case BluetoothAdapter.ACTION_REQUEST_ENABLE:
                dispatchMessage(MSG_ADAPTER_REQUEST_ENABLE, 0, 0, null);
                break;

            case BluetoothDevice.ACTION_ACL_CONNECTED:
            case BluetoothDevice.ACTION_ACL_DISCONNECTED:
                int transport = BluetoothDevice.TRANSPORT_AUTO;
                if (BuildUtil.versionAtLeastT()) {
                    transport = intent.getIntExtra(BluetoothDevice.EXTRA_TRANSPORT, BluetoothDevice.TRANSPORT_AUTO);
                }
                device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                LUtil.d(mTag, "device:", BtAdapter.getContent(context, device), "transport:", transport);
                dispatchMessage(
                        BluetoothDevice.ACTION_ACL_CONNECTED.equals(action) ? MSG_DEVICE_ACL_CONNECTED : MSG_DEVICE_ACL_DISCONNECTED,
                        transport, 0, device);
                break;
            case BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED:
                device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                LUtil.d(mTag, "device:", BtAdapter.getContent(context, device));
                dispatchMessage(MSG_DEVICE_ACL_DISCONNECT_REQUESTED, 0, 0, device);
                break;
            case BluetoothDevice.ACTION_PAIRING_REQUEST:
                int paringVariant = intent.getIntExtra(BluetoothDevice.EXTRA_PAIRING_VARIANT, -1);
                String paringKey = intent.getStringExtra(BluetoothDevice.EXTRA_PAIRING_KEY);
                int reason = intent.getIntExtra("android.bluetooth.device.extra.REASON", -1);
                LUtil.d(mTag, "paring:", paringVariant, paringKey, reason); //TODO
                dispatchMessage(MSG_DEVICE_PAIRING_REQUEST, 0, 0, new BtDevice(context.getApplicationContext(), device, clazz, name, rssi));
                break;
            case BluetoothDevice.ACTION_FOUND:
                device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                clazz = intent.getParcelableExtra(BluetoothDevice.EXTRA_CLASS);
                name = intent.getStringExtra(BluetoothDevice.EXTRA_NAME);
                rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI, (short) 0);
                boolean isCoordinatedSetMember = false;
                if (BuildUtil.versionAtLeastT()) {
                    isCoordinatedSetMember = intent.getBooleanExtra(BluetoothDevice.EXTRA_IS_COORDINATED_SET_MEMBER, false);
                }
                LUtil.d(mTag, BtAdapter.getContent(context, device), BtAdapter.getContent(clazz), name, rssi, isCoordinatedSetMember);
                dispatchMessage(MSG_DEVICE_FOUND, 0, 0,
                        new BtDevice(context.getApplicationContext(), device, clazz, name, rssi, isCoordinatedSetMember));
                break;
            case BluetoothDevice.ACTION_BOND_STATE_CHANGED:
                int bondState = intent.getIntExtra(BluetoothDevice.EXTRA_BOND_STATE, -1);
                int preBondState = intent.getIntExtra(BluetoothDevice.EXTRA_PREVIOUS_BOND_STATE, -1);
                device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                LUtil.d(mTag, "bond state:", bondState, " previous state:",
                        preBondState, BtAdapter.getContent(context, device));
                dispatchMessage(MSG_DEVICE_BOND_STATE_CHANGED, preBondState, bondState, device);
                break;
            case BluetoothDevice.ACTION_CLASS_CHANGED:
                clazz = intent.getParcelableExtra(BluetoothDevice.EXTRA_CLASS);
                device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                LUtil.d(mTag, "class:", BtAdapter.getContent(clazz), BtAdapter.getContent(context, device));
                dispatchMessage(MSG_DEVICE_CLASS_CHANGED, 0, 0, new BtDevice(context.getApplicationContext(), device));
                break;
            case BluetoothDevice.ACTION_NAME_CHANGED:
                name = intent.getStringExtra(BluetoothDevice.EXTRA_NAME);
                device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                LUtil.d(mTag, "name:", name, BtAdapter.getContent(context, device));
                dispatchMessage(MSG_DEVICE_NAME_CHANGED, 0, 0, new BtDevice(context.getApplicationContext(), device));
                break;
            case BluetoothDevice.ACTION_UUID:
                Parcelable[] uuids = intent.getParcelableArrayExtra(BluetoothDevice.EXTRA_UUID);
                device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                LUtil.d(mTag, "uuids:", uuids, BtAdapter.getContent(context, device));
                dispatchMessage(MSG_DEVICE_UUID, 0, 0, new BtDevice(context.getApplicationContext(), device));
                break;
            case BluetoothDevice.ACTION_ALIAS_CHANGED:
                device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                LUtil.d(mTag, BtAdapter.getContent(context, device));
                dispatchMessage(MSG_DEVICE_ALIAS_CHANGED, 0, 0, new BtDevice(context.getApplicationContext(), device));
                break;

            case BluetoothA2dp.ACTION_CONNECTION_STATE_CHANGED:
                dispatchMessage(MSG_PROFILE_A2DP_SOURCE_CONNECTION_STATE_CHANGED);
                break;
            case BluetoothA2dp.ACTION_PLAYING_STATE_CHANGED:
                dispatchMessage(MSG_PROFILE_A2DP_SOURCE_PLAYING_STATE_CHANGED);
                break;
            case BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED:
                dispatchMessage(MSG_PROFILE_HFP_AG_CONNECTION_STATE_CHANGED);
                break;
            case BluetoothHeadset.ACTION_AUDIO_STATE_CHANGED:
                dispatchMessage(MSG_PROFILE_HFP_AG_AUDIO_STATE_CHANGED);
                break;
            case BluetoothHeadset.ACTION_VENDOR_SPECIFIC_HEADSET_EVENT:
                dispatchMessage(MSG_PROFILE_HFP_AG_VENDOR_SPECIFIC_HEADSET_EVENT);
                break;
            case ACTION_BLUETOOTH_HID_HOST:
                dispatchMessage(MSG_PROFILE_HID_HOST_CONNECTION_STATE_CHANGED);
                break;
            case BluetoothHidDevice.ACTION_CONNECTION_STATE_CHANGED:
                dispatchMessage(MSG_PROFILE_HID_DEVICE_CONNECTION_STATE_CHANGED);
                break;
            case BluetoothHearingAid.ACTION_CONNECTION_STATE_CHANGED:
                dispatchMessage(MSG_PROFILE_HEARING_AID_CONNECTION_STATE_CHANGED);
                break;
        }
    }
}
