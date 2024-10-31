package com.zs.androidappfw.wcn.utils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.ParcelUuid;

import androidx.annotation.Nullable;

import com.zs.androidappfw.utils.BuildUtil;
import com.zs.androidappfw.utils.LUtil;
import com.zs.androidappfw.utils.PermissionUtil;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

public class BtAdapter {

    private static final String TAG = BtAdapter.class.getSimpleName();

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

    public static boolean enable(Context context) {
        boolean result = false;
        if (PermissionUtil.checkBluetoothPermission(context)) {
            result = getInstance(context).enable();
            LUtil.i(TAG, "enable BT:", result);
        } else {
            LUtil.w(TAG, "no Bluetooth Permission");
        }
        return result;
    }

    public static boolean disable(Context context) {
        boolean result = false;
        if (PermissionUtil.checkBluetoothPermission(context)) {
            result = getInstance(context).disable();
            LUtil.i(TAG, "disable BT:", result);
        } else {
            LUtil.w(TAG, "no Bluetooth Permission");
        }
        return result;
    }

    public static boolean startDiscovery(Context context) {
        boolean result = false;
        if (PermissionUtil.checkBluetoothPermission(context)) {
            result = getInstance(context).startDiscovery();
            LUtil.i(TAG, "BT startDiscovery:", result);
        } else {
            LUtil.w(TAG, "no Bluetooth Permission");
        }
        return result;
    }

    public static boolean cancelDiscovery(Context context) {
        boolean result = false;
        if (PermissionUtil.checkBluetoothPermission(context)) {
            result = getInstance(context).cancelDiscovery();
            LUtil.i(TAG, "BT cancelDiscovery:", result);
        } else {
            LUtil.w(TAG, "no Bluetooth Permission");
        }
        return result;
    }

    public static boolean isDiscovering(Context context) {
        if (PermissionUtil.checkBluetoothPermission(context)) {
            return getInstance(context).isDiscovering();
        } else {
            LUtil.w(TAG, "no Bluetooth Permission");
            return false;
        }
    }

    public static String getAddress(Context context) {
        return getInstance(context).getAddress();
    }

    public static String getName(Context context) {
        if (PermissionUtil.checkBluetoothPermission(context)) {
            return getInstance(context).getName();
        } else {
            LUtil.w(TAG, "no Bluetooth Permission");
            return "";
        }
    }

    public static boolean setName(Context context, String name) {
        boolean result = false;
        if (PermissionUtil.checkBluetoothPermission(context)) {
            result = getInstance(context).setName(name);
            LUtil.i(TAG, "set name:", name, result);
        } else {
            LUtil.w(TAG, "no Bluetooth Permission");
        }
        return result;
    }

    public static int getScanMode(Context context) {
        if (PermissionUtil.checkBluetoothPermission(context)) {
            return getInstance(context).getScanMode();
        } else {
            LUtil.w(TAG, "no Bluetooth Permission");
            return BluetoothAdapter.SCAN_MODE_NONE;
        }
    }

    public static String getScanModeName(Context context) {
        int scanMode = getScanMode(context);
        if (BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE == scanMode) {
            return "CONNECTABLE_DISCOVERABLE";
        } else if (BluetoothAdapter.SCAN_MODE_CONNECTABLE == scanMode) {
            return "CONNECTABLE";
        } else {
            return "NONE";
        }
    }

    public static @Nullable Duration getDiscoverableTimeout(Context context) {
        if (BuildUtil.versionAtLeastT() && PermissionUtil.checkBluetoothPermission(context)) {
            return getInstance(context).getDiscoverableTimeout();
        } else {
            LUtil.w(TAG, "no Bluetooth Permission");
            return null;
        }
    }

    public static Set<BluetoothDevice> getBondedDevices(Context context) {
        if (PermissionUtil.checkBluetoothPermission(context)) {
            return getInstance(context).getBondedDevices();
        } else {
            LUtil.w(TAG, "no Bluetooth Permission");
            return new HashSet<>();
        }
    }

    public static int getMaxConnectedAudioDevices(Context context) {
        if (BuildUtil.versionAtLeastT() && PermissionUtil.checkBluetoothPermission(context)) {
            return getInstance(context).getMaxConnectedAudioDevices();
        } else {
            LUtil.w(TAG, "no Bluetooth Permission");
            return -1;
        }
    }

    public static int getProfileConnectionState(Context context, int profile) {
        if (PermissionUtil.checkBluetoothPermission(context)) {
            return getInstance(context).getProfileConnectionState(profile);
        } else {
            LUtil.w(TAG, "no Bluetooth Permission");
            return BluetoothProfile.STATE_DISCONNECTED;
        }
    }

    public static String getProfileConnectionStateStr(Context context, int profile) {
        int state = getProfileConnectionState(context, profile);
        if (BluetoothProfile.STATE_CONNECTED == state) {
            return "CONNECTED";
        } else if (BluetoothProfile.STATE_CONNECTING == state) {
            return "CONNECTING";
        } else if (BluetoothProfile.STATE_DISCONNECTING == state) {
            return "DISCONNECTING";
        } else {
            return "DISCONNECTED";
        }
    }


    public static String getStateName(Context context) {
        return getStateName(getInstance(context).getState());
    }

    public static String getStateName(int state) {
        if (BluetoothAdapter.STATE_ON == state) {
            return "ON";
        } else if (BluetoothAdapter.STATE_TURNING_ON == state) {
            return "TURNING_ON";
        } else if (BluetoothAdapter.STATE_TURNING_OFF == state) {
            return "TURNING_OFF";
        } else {
            return "OFF";
        }
    }

    public static String getContent(Context context, BluetoothDevice device) {
        if (device == null) return "";
        BtDevice btDevice = new BtDevice(context, device);
        StringBuilder sb = new StringBuilder();
        sb.append(" address=").append(btDevice.getAddress())
                .append(" name=").append(btDevice.getName(context))
                .append(" type=").append(btDevice.getTypeName())
                .append(" bondState=").append(btDevice.getBondStateName())
                .append(" class={").append(getContent(btDevice.getBluetoothClass())).append("}");
        ParcelUuid[] parcelUuidArray = btDevice.getUuids();
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
