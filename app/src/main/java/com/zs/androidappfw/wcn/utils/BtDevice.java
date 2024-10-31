package com.zs.androidappfw.wcn.utils;

import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.ParcelUuid;
import android.text.TextUtils;

import com.zs.androidappfw.utils.PermissionUtil;

public class BtDevice {

    private Context appContext;

    private BluetoothDevice device = null;
    private BluetoothClass clazz = null;
    private String name = "";
    private int rssi = -127;
    private ParcelUuid[] uuids = null;
    private int connectionState = 0;
    private boolean isCoordinatedSetMember = false;

    public BtDevice(Context context, BluetoothDevice device) {
        appContext = context;
        this.device = device;
    }

    public BtDevice(Context context, BluetoothDevice device, ParcelUuid[] uuids) {
        this(context, device);
        this.uuids = uuids;
    }

    public BtDevice(Context context, BluetoothDevice device, BluetoothClass clazz, String name, int rssi) {
        this(context, device, clazz, name, rssi, false);
    }

    public BtDevice(Context context, BluetoothDevice device, BluetoothClass clazz, String name, int rssi,
            boolean isCoordinatedSetMember) {
        appContext = context;
        this.device = device;
        this.clazz = clazz;
        this.name = name;
        this.rssi = rssi;
        this.isCoordinatedSetMember = isCoordinatedSetMember;
    }

    public int getRssi() {
        return rssi;
    }

    public String getRssiStr() {
        return String.valueOf(rssi);
    }

    public String getName(Context context) {
        String deviceName = "";
        if (TextUtils.isEmpty(name)) {
            if ((device != null) && PermissionUtil.checkBluetoothPermission(context)) {
                deviceName = device.getName();
            }
        }
        if (TextUtils.isEmpty(deviceName)) {
            return "unknown";
        } else {
            return deviceName;
        }
    }

    public BluetoothClass getBluetoothClass() {
        if ((clazz == null) && (device != null) && PermissionUtil.checkBluetoothPermission(appContext)) {
            return device.getBluetoothClass();
        }
        return clazz;
    }

    public BluetoothDevice getBluetoothDevice() {
        return device;
    }

    public String getAddress() {
        return (device == null) ? "00:00:00:00:00:00" : device.getAddress();
    }

    public int getType() {
        int type = BluetoothDevice.DEVICE_TYPE_UNKNOWN;
        if (device != null && PermissionUtil.checkBluetoothPermission(appContext)) type = device.getType();
        return type;
    }

    public String getTypeName() {
        return getTypeName(getType());
    }

    public int getBondState() {
        int bondState = BluetoothDevice.BOND_NONE;
        if (device != null && PermissionUtil.checkBluetoothPermission(appContext)) {
            bondState = device.getBondState();
        }
        return bondState;
    }

    public String getBondStateName() {
        return getBondState(getBondState());
    }

    public ParcelUuid[] getUuids() {
        if ((uuids == null) && (device != null) && PermissionUtil.checkBluetoothPermission(appContext)) {
            return device.getUuids();
        }
        return uuids;
    }

    private static String getTypeName(int type) {
        switch (type) {
            case BluetoothDevice.DEVICE_TYPE_CLASSIC:
                return "BR/EDR";
            case BluetoothDevice.DEVICE_TYPE_LE:
                return "BLE";
            case BluetoothDevice.DEVICE_TYPE_DUAL:
                return "DUAL";
            case BluetoothDevice.DEVICE_TYPE_UNKNOWN:
            default:
                return "UNKNOWN";
        }
    }

    private static String getBondState(int state) {
        switch (state) {
            case BluetoothDevice.BOND_BONDING:
                return "BONDING";
            case BluetoothDevice.BOND_BONDED:
                return "BONDED";
            case BluetoothDevice.BOND_NONE:
            default:
                return "NONE";
        }
    }
}
