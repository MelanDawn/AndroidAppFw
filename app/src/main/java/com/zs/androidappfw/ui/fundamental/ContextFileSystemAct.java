package com.zs.androidappfw.ui.fundamental;

import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.widget.LinearLayout;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;
import com.zs.androidappfw.utils.ClassInfoUtil;

import java.io.File;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Objects;

public class ContextFileSystemAct extends BaseTitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_device_info);
        LinearLayout linearLayout = findViewById(R.id.device_info_ll);
        ClassInfoUtil.displayGroup(this, linearLayout, getContentMap());
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_android_file_system;
    }

    private LinkedHashMap<String, String> getContentMap() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        map.put(getObbDir().getAbsolutePath(), "");
        map.put("getObbDir()", getObbDir().getAbsolutePath());
        AddFileArray("getObbDirs()", getObbDirs(), map);

        map.put(getExternalMediaDirs()[0].getAbsolutePath(), "");
        AddFileArray("getExternalMediaDirs()", getExternalMediaDirs(), map);

        map.put(getExternalFilesDir(Environment.DIRECTORY_ALARMS).getParentFile().getAbsolutePath(), "");
        Field[] fields = Environment.class.getFields();
        LinkedHashMap<String, String> tmpMap = new LinkedHashMap<>();
        for (Field field : fields) {
            if (field.getName().startsWith("DIRECTORY_")) {
                String value;
                try {
                    value = Objects.requireNonNull(field.get(field)).toString();
                } catch (IllegalAccessException | IllegalArgumentException e) {
                    value = "";
                }
                if (!TextUtils.isEmpty(value)) {
                    String path = " ";
                    File file = getExternalFilesDir(value);
                    if (file != null) path = file.getAbsolutePath();
                    map.put("getExternalFilesDir(" + field.getName() + ")", path);

                    AddFileArray("getExternalFilesDirs(" + field.getName() + ")",
                            getExternalFilesDirs(value), tmpMap);
                }
            }
        }
        map.putAll(tmpMap);

        map.put(getExternalCacheDir().getAbsolutePath(), "");
        map.put("getExternalCacheDir()", Objects.requireNonNull(getExternalCacheDir()).getAbsolutePath());
        AddFileArray("getExternalCacheDirs()", getExternalCacheDirs(), map);

        map.put(getDataDir().getAbsolutePath(), "");
        map.put("getDataDir()", getDataDir().getAbsolutePath());
        map.put("getCacheDir()", getCacheDir().getAbsolutePath());
        map.put("getCodeCacheDir()", getCodeCacheDir().getAbsolutePath());
        map.put("getDatabasePath(\"testDataPath\")", getDatabasePath("testDataPath").getAbsolutePath());
        map.put("getDir(\"testDir\", MODE_PRIVATE)", getDir("testDir", MODE_PRIVATE).getAbsolutePath());


        map.put("getNoBackupFilesDir()", getNoBackupFilesDir().getAbsolutePath());
        map.put("getFilesDir()", getFilesDir().getAbsolutePath());
        AddFileArray("fileList()", fileList(), map);
        map.put("getFileStreamPath(\"testStream\")", getFileStreamPath("testStream").getAbsolutePath());

        return map;
    }

    private void AddFileArray(String key, File[] files, LinkedHashMap<String, String> map) {
        StringBuilder sb = new StringBuilder();
        if (files != null) {
            for (File file : files) {
                sb.append(file.getAbsoluteFile()).append("\n");
            }
        }
        if (sb.length() == 0) sb.append(" ");
        map.put(key, sb.substring(0));
    }

    private void AddFileArray(String key, String[] files, LinkedHashMap<String, String> map) {
        StringBuilder sb = new StringBuilder();
        if (files != null) {
            for (String file : files) {
                sb.append(file).append("\n");
            }
        }
        if (sb.length() == 0) sb.append(" ");
        map.put(key, sb.substring(0));
    }
}
