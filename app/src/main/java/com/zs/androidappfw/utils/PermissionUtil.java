package com.zs.androidappfw.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

public class PermissionUtil {

    public static boolean checkBluetoothPermission(Context context) {
        if (BuildUtil.versionAtLeastS()) {
            return checkPermission(context, Manifest.permission.BLUETOOTH_CONNECT)
                    && checkPermission(context, Manifest.permission.BLUETOOTH_SCAN)
                    && checkPermission(context, Manifest.permission.BLUETOOTH_ADVERTISE);
        } else {
            return true;
        }
    }

    public static boolean checkNearbyWifiPermission(Context context) {
        return BuildUtil.versionAtLeastT()
                && checkPermission(context, Manifest.permission.NEARBY_WIFI_DEVICES);
    }

    public static boolean checkLocationPermission(Context context) {
        return checkPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
    }

    public static boolean checkPhonePermission(Context context) {
        return checkPermission(context, Manifest.permission.READ_PHONE_STATE)
                || checkPermission(context, Manifest.permission.READ_PHONE_NUMBERS);
    }

    public static boolean checkPermission(Context context, String permission) {
        return context == null ||
                context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }
}
