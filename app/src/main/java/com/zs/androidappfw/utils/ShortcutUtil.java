package com.zs.androidappfw.utils;

// Created by zhangs on 2019/2/19.

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.database.Cursor;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;

public class ShortcutUtil {

    public void createShortcut(Context context, String shortcutName, int shortcutIcon,
                               String shortcutId, boolean allowRepeat, Activity dst) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            if (hasShortcut(context)) return;

            Intent intent = new Intent();
            intent.setAction("android.intent.action.MAIN");
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setComponent(new ComponentName(context, dst.getClass()));
            addShortcut(context, shortcutName, shortcutIcon, intent, allowRepeat);
        } else {
            Intent intent = new Intent(context, dst.getClass());
            intent.setAction(Intent.ACTION_VIEW);
            addShortcutApi26(context, shortcutName, shortcutIcon, intent, shortcutId);
        }
    }

    /**
     * 判断当前应用在桌面是否有桌面快捷方式
     *
     */
    private static boolean hasShortcut(Context context) {
        boolean result = false;
        String title = null;
        try {
            final PackageManager pm = context.getPackageManager();
            title = pm.getApplicationLabel(
                    pm.getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA)).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        final String uriStr = "content://com.android.launcher3.settings/favorites?notify=true";
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.FROYO) {
//            uriStr = "content://com.android.launcher.settings/favorites?notify=true";
//        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
//            uriStr = "content://com.android.launcher2.settings/favorites?notify=true";
//        } else {
//            uriStr = "content://com.android.launcher3.settings/favorites?notify=true";
//        }
        final Uri CONTENT_URI = Uri.parse(uriStr);
        final Cursor c = context.getContentResolver().query(CONTENT_URI, null,
                "title=?", new String[]{title}, null);
        if (c != null) {
            result = c.getCount() > 0;
            c.close();
        }
        return result;
    }

    /**
     * 创建快捷方式
     * @param context 上下文环境
     * @param shortcutName 快捷方式名称
     * @param iconRes 快捷方式图标资源
     * @param actionIntent 快捷方式被点击后执行的意图
     * @param allowRepeat 快捷方式是否可重复创建
     */
    private static void addShortcut(Context context, String shortcutName, int iconRes,
                                    Intent actionIntent, boolean allowRepeat){
        Intent shortcutIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        //是否允许重复创建
        shortcutIntent.putExtra("duplicate", allowRepeat);
        //快捷方式的名称
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, shortcutName);
        //设置快捷方式图片
        Parcelable icon = Intent.ShortcutIconResource.fromContext(context, iconRes);
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
        //设置快捷方式动作
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, actionIntent);
        //向系统发送广播
        context.sendBroadcast(shortcutIntent);
    }

    @TargetApi(Build.VERSION_CODES.O)
    private static void addShortcutApi26(Context context, String shortcutName, int iconRes,
                                         Intent actionIntent, String shortcutId) {
        ShortcutManager manager = (ShortcutManager) context.getSystemService(Context.SHORTCUT_SERVICE);
        if (manager != null && manager.isRequestPinShortcutSupported()) {
            ShortcutInfo info = new ShortcutInfo.Builder(context, shortcutId)
                    .setIcon(Icon.createWithResource(context, iconRes))
                    .setShortLabel(shortcutName)
                    .setIntent(actionIntent)
                    .build();
            Intent pinnedShortcutCallbackIntent = manager.createShortcutResultIntent(info);
            PendingIntent callbackIntent = PendingIntent.getBroadcast(context, 0,
                    pinnedShortcutCallbackIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            manager.requestPinShortcut(info, callbackIntent.getIntentSender());
        }
    }
}
