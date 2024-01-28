package com.zs.androidappfw.utils;

import android.content.Context;
import android.graphics.Typeface;

import androidx.annotation.NonNull;

public class FontUtil {

    public static Typeface getTypeInterface(@NonNull Context context) {
        return Typeface.create("font/times.ttf", Typeface.ITALIC);
    }

    public static Typeface getTypeEnum(@NonNull Context context) {
        return Typeface.create("font/timesbd.ttf", Typeface.BOLD_ITALIC);
    }

    public static Typeface getTypeClass(@NonNull Context context) {
        return Typeface.create("font/timesbd.ttf", Typeface.BOLD);
    }

    public static Typeface getTypeAbstract(@NonNull Context context) {
        return Typeface.create("font/times.ttf", Typeface.NORMAL);
    }
}
