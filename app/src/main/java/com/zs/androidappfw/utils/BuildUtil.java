package com.zs.androidappfw.utils;

import android.os.Build;

public class BuildUtil {

    public static boolean versionAtLeastOmr1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1;
    }

    public static boolean versionAtLeastP() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P;
    }

    public static boolean versionAtLeastQ() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q;
    }

    public static boolean versionAtLeastR() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.R;
    }

    public static boolean versionAtLeastS() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.S;
    }

    public static boolean versionAtLeastSv2() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.S_V2;
    }

    public static boolean versionAtLeastT() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU;
    }
}
