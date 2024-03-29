package com.zs.androidappfw.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

public class PermissionUtil {

    public static boolean checkBluetoothPermission(Context context) {
        return true;
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
