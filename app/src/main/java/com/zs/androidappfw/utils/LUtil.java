package com.zs.androidappfw.utils;

import android.util.Log;
import com.zs.androidappfw.config.Config;

/**
 * Created by shoes on 2017/10/28.
 *
 */

public class LUtil {

    private static final String APP_TAG = Config.APP_TAG;

    private static final boolean V = true;
    private static final boolean D = true;
    private static final boolean I = true;
    private static final boolean W = true;
    private static final boolean E = true;

    public static void v(String tag, Object... msg){
        if (V) {
            Log.v(APP_TAG, formatLog(tag, msg));
        }
    }

    public static void d(String tag, Object... msg){
        if (D) {
            Log.d(APP_TAG, formatLog(tag, msg));
        }
    }

    public static void i(String tag, Object... msg){
        if (I) {
            Log.i(APP_TAG, formatLog(tag, msg));
        }
    }

    public static void w(String tag, Object... msg){
        if (W) {
            Log.w(APP_TAG, formatLog(tag, msg));
        }
    }

    public static void e(String tag, Object... msg){
        if (E) {
            Log.e(APP_TAG, formatLog(tag, msg));
        }
    }

    private static String formatLog(String tag, Object... msg){
        StringBuilder sb = new StringBuilder();
        Thread t = Thread.currentThread();
        sb.append("[ ").append(tag)
                .append("-").append(t.getId())
                .append(",").append(t.getName())
                .append(",").append(t.getThreadGroup() == null ? "NULL" : t.getThreadGroup().getName())
                .append(" ]");
        if (msg == null) {
            sb.append("NULL");
        } else {
            for (Object object : msg) {
                sb.append(" ").append(object == null ? "NULL" : object.toString());
            }
        }
        return sb.substring(0);
    }
}
