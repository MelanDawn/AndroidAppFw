package com.zs.androidappfw.ui.fundamental;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;
import com.zs.androidappfw.utils.LUtil;

import java.io.File;
import java.util.LinkedHashMap;

public class FileSystemAct extends BaseTitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_file_system);

        LinearLayout linearLayout = findViewById(R.id.file_system_ll);

        LinkedHashMap<String, String> map = getFilePath();
        for (String key: map.keySet()) {
            String value = map.get(key);
            TextView left = new TextView(this);
            left.setText(key);
            linearLayout.addView(left);
            if (value != null && value.length() > 0) {
                left.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                left.setPadding(10, 0, 0, 0);

                TextView right = new TextView(this);
                right.setText(value);
                right.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                right.setPadding(10, 0, 0, 0);
                right.setTextColor(Color.BLUE);
                linearLayout.addView(right);
            } else {
                left.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                left.setTextColor(Color.RED);
            }
            View divider = new View(this);
            divider.setMinimumHeight(1);
            divider.setMinimumWidth(2048);
            divider.setBackgroundColor(Color.BLACK);
            linearLayout.addView(divider);
        }
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_file_system;
    }

    private LinkedHashMap<String, String> getFilePath() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        try {
            map.put("Environment", "");
            map.put("getExternalStorageState()", Environment.getExternalStorageState());
            map.put("getRootDirectory()", Environment.getRootDirectory().getAbsolutePath());
            map.put("getDataDirectory()", Environment.getDataDirectory().getAbsolutePath());
            map.put("getDownloadCacheDirectory()", Environment.getDownloadCacheDirectory().getAbsolutePath());
            map.put("Environment /storage/emulated/0", "");
            map.put("getExternalStorageDirectory()", Environment.getExternalStorageDirectory().getAbsolutePath());
            map.put("getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS)",
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS).getAbsolutePath());
            map.put("getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)",
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath());
            map.put("getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)",
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath());
            map.put("getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)",
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
            map.put("getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES)",
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).getAbsolutePath());
            map.put("getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)",
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getAbsolutePath());
            map.put("getExternalStoragePublicDirectory(Environment.DIRECTORY_NOTIFICATIONS)",
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_NOTIFICATIONS).getAbsolutePath());
            map.put("getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)",
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath());
            map.put("getExternalStoragePublicDirectory(Environment.DIRECTORY_PODCASTS)",
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PODCASTS).getAbsolutePath());
            map.put("getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES)",
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath());
            map.put("getExternalStoragePublicDirectory(\"testEnvironment\")",
                    Environment.getExternalStoragePublicDirectory("testEnvironment").getAbsolutePath());

            map.put("Context /storage/emulated/0/Android/data", "");
            map.put("getExternalCacheDir()", getExternalCacheDir().getAbsolutePath());
            map.put("getExternalCacheDir(Environment.DIRECTORY_ALARMS)",
                    getExternalFilesDir(Environment.DIRECTORY_ALARMS).getAbsolutePath());
            map.put("getExternalCacheDir(Environment.DIRECTORY_DOCUMENTS)",
                    getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath());
            map.put("getExternalCacheDir(Environment.DIRECTORY_DCIM)",
                    getExternalFilesDir(Environment.DIRECTORY_DCIM).getAbsolutePath());
            map.put("getExternalCacheDir(Environment.DIRECTORY_DOWNLOADS)",
                    getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
            map.put("getExternalCacheDir(Environment.DIRECTORY_MOVIES)",
                    getExternalFilesDir(Environment.DIRECTORY_MOVIES).getAbsolutePath());
            map.put("getExternalCacheDir(Environment.DIRECTORY_NOTIFICATIONS)",
                    getExternalFilesDir(Environment.DIRECTORY_NOTIFICATIONS).getAbsolutePath());
            map.put("getExternalCacheDir(Environment.DIRECTORY_PICTURES)",
                    getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath());
            map.put("getExternalCacheDir(Environment.DIRECTORY_PODCASTS)",
                    getExternalFilesDir(Environment.DIRECTORY_PODCASTS).getAbsolutePath());
            map.put("getExternalCacheDir(Environment.DIRECTORY_RINGTONES)",
                    getExternalFilesDir(Environment.DIRECTORY_RINGTONES).getAbsolutePath());
            map.put("getExternalCacheDir(\"testContext\")",
                    getExternalFilesDir("testContext").getAbsolutePath());
            map.put("Context /storage/emulated/0/Android/obb", "");
            map.put("getObbDir()", getObbDir().getAbsolutePath());
            map.put("Context /storage/emulated/0/Android/media", "");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                int count = 1;
                for (File file : getExternalMediaDirs()) {
                    map.put("getExternalMediaDirs() " + count, file.getAbsolutePath());
                    count++;
                }
            }

            map.put("Context /data/user/0", "");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                map.put("getDataDir()", getDataDir().getAbsolutePath());
            map.put("getCacheDir()", getCacheDir().getAbsolutePath());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                map.put("getCodeCacheDir()", getCodeCacheDir().getAbsolutePath());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                map.put("getNoBackupFilesDir()", getNoBackupFilesDir().getAbsolutePath());
            }

            map.put("getFilesDir()", getFilesDir().getAbsolutePath());
            map.put("getDir(\"testDir\")", getDir("testDir", Context.MODE_PRIVATE).getAbsolutePath());
            map.put("getDatabasePath(\"testDatabasePath\")", getDatabasePath("testDatabasePath").getAbsolutePath());
            map.put("getFileStreamPath(\"testFileStreamPath\")", getFileStreamPath("testFileStreamPath").getAbsolutePath());

            map.put("Context files list", "");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                int count = 1;
                for (String file : fileList()) {
                    map.put("fileList() " + count + " " + getFilesDir(), file);
                    count++;
                }
            }

            LUtil.d(mTag, "Environment:", Environment.getExternalStorageState());

            LUtil.d(mTag, Environment.getRootDirectory().getAbsolutePath());
            LUtil.d(mTag, Environment.getDataDirectory().getAbsolutePath());
            LUtil.d(mTag, Environment.getDownloadCacheDirectory().getAbsolutePath());
            LUtil.d(mTag, Environment.getExternalStorageDirectory().getAbsolutePath());
            LUtil.d(mTag, Environment.getExternalStorageDirectory().getCanonicalPath());
            LUtil.d(mTag, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS).getAbsolutePath());
            LUtil.d(mTag, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath());
            LUtil.d(mTag, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath());
            LUtil.d(mTag, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
            LUtil.d(mTag, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).getAbsolutePath());
            LUtil.d(mTag, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getAbsolutePath());
            LUtil.d(mTag, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_NOTIFICATIONS).getAbsolutePath());
            LUtil.d(mTag, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath());
            LUtil.d(mTag, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PODCASTS).getAbsolutePath());
            LUtil.d(mTag, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES).getAbsolutePath());
            LUtil.d(mTag, Environment.getExternalStoragePublicDirectory("testEnvironment").getAbsolutePath());

            LUtil.d(mTag, "Context:");
            LUtil.d(mTag, getExternalCacheDir().getAbsolutePath());
            LUtil.d(mTag, getExternalCacheDir().getCanonicalPath());
            LUtil.d(mTag, getExternalFilesDir(Environment.DIRECTORY_ALARMS).getAbsolutePath());
            LUtil.d(mTag, getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath());
            LUtil.d(mTag, getExternalFilesDir(Environment.DIRECTORY_DCIM).getAbsolutePath());
            LUtil.d(mTag, getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
            LUtil.d(mTag, getExternalFilesDir(Environment.DIRECTORY_MOVIES).getAbsolutePath());
            LUtil.d(mTag, getExternalFilesDir(Environment.DIRECTORY_NOTIFICATIONS).getAbsolutePath());
            LUtil.d(mTag, getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath());
            LUtil.d(mTag, getExternalFilesDir(Environment.DIRECTORY_PODCASTS).getAbsolutePath());
            LUtil.d(mTag, getExternalFilesDir(Environment.DIRECTORY_RINGTONES).getAbsolutePath());
            LUtil.d(mTag, getExternalFilesDir("testContext").getAbsolutePath());
            LUtil.d(mTag, getObbDir().getAbsolutePath());

            LUtil.d(mTag, "Start Media List:");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                for (File file : getExternalMediaDirs())
                    LUtil.d(mTag, file.getAbsolutePath());
            }
            LUtil.d(mTag, "End   Media List:");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                LUtil.d(mTag, getDataDir().getAbsolutePath());
            LUtil.d(mTag, getCacheDir().getAbsolutePath());
            LUtil.d(mTag, getCacheDir().getCanonicalPath());
            LUtil.d(mTag, getCacheDir().getPath());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                LUtil.d(mTag, getCodeCacheDir().getAbsolutePath());
            LUtil.d(mTag, getFilesDir().getAbsolutePath());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                LUtil.d(mTag, getNoBackupFilesDir().getAbsolutePath());
            LUtil.d(mTag, getDir("testDir", Context.MODE_PRIVATE).getAbsolutePath());
            LUtil.d(mTag, getDatabasePath("testDatabasePath").getAbsolutePath());
            LUtil.d(mTag, getFileStreamPath("testFileStreamPath").getAbsolutePath());
            LUtil.d(mTag, "File List:");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                for (String file : fileList())
                    LUtil.d(mTag, file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
