package com.zs.androidappfw.wcn.utils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.ParcelUuid;

public class BluetoothUtils {

    private static final String TAG = BluetoothUtils.class.getSimpleName();

    public static BluetoothAdapter getInstance(Context context) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
            BluetoothManager bluetoothManager =
                    (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
            if (bluetoothManager != null) {
                return bluetoothManager.getAdapter();
            } else {
                throw new RuntimeException("bluetoothManager is null");
            }
        } else {
            return BluetoothAdapter.getDefaultAdapter();
        }
    }

    public static boolean hasFeatureBt(Context context) {
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        return packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH);
    }

    public static boolean hasFeatureBle(Context context) {
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        return packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE);
    }

    public static String getContent(BluetoothDevice device) {
        if (device == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(" address=").append(device.getAddress())
                .append(" name=").append(device.getName())
                .append(" type=").append(getTypeName(device.getType()))
                .append(" state=").append(getBondState(device.getBondState()))
                .append(" class={").append(BluetoothUtils.getContent(device.getBluetoothClass())).append("}");
        ParcelUuid[] parcelUuidArray = device.getUuids();
        if (parcelUuidArray == null) {
            sb.append(" uuid=").append("NULL");
        } else {
            int i = 0;
            for (ParcelUuid uuid : parcelUuidArray) {
                i++;
                sb.append(" uuid-").append(i).append("=").append("{");
                sb.append(" timestamp=").append(uuid.getUuid().timestamp());
                sb.append(" version=").append(uuid.getUuid().version());
                sb.append(" variant=").append(uuid.getUuid().variant());
                sb.append(" clockSequence=").append(String.format("%04x", uuid.getUuid().clockSequence()));
                sb.append(" node=").append(String.format("%012x", uuid.getUuid().node()));
                sb.append(" toString=").append(uuid.getUuid().toString());
                sb.append(" }");
            }
        }
        return sb.substring(0);
    }

    public static String getTypeName(int type) {
        switch (type) {
            case BluetoothDevice.DEVICE_TYPE_UNKNOWN:
                return "UNKNOWN";
            case BluetoothDevice.DEVICE_TYPE_CLASSIC:
                return "CLASSIC";
            case BluetoothDevice.DEVICE_TYPE_LE:
                return "BLE";
            case BluetoothDevice.DEVICE_TYPE_DUAL:
                return "DUAL";
            default:
                return "ERROR";
        }
    }

    public static String getBondState(int state) {
        switch (state) {
            case BluetoothDevice.BOND_NONE:
                return "NONE";
            case BluetoothDevice.BOND_BONDING:
                return "BONDING";
            case BluetoothDevice.BOND_BONDED:
                return "BONDED";
            default:
                return "ERROR";
        }
    }

    public static String getContent(BluetoothClass clazz) {
        if (clazz == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(" clazzDec=").append(clazz.hashCode())
                .append(" major=").append(clazz.getMajorDeviceClass())
                .append(" minor=").append(clazz.getDeviceClass())
                .append(" clazzHex=").append(clazz.toString());
        return sb.substring(0);
    }
}
