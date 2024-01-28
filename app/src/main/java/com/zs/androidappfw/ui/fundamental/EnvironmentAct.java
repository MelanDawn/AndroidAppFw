package com.zs.androidappfw.ui.fundamental;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;
import com.zs.androidappfw.utils.ClassInfoUtil;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Objects;

public class EnvironmentAct extends BaseTitleActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_device_info);
        LinearLayout linearLayout = findViewById(R.id.device_info_ll);
        ClassInfoUtil.displayClassInfo(this, linearLayout, Environment.class);

        LinkedHashMap<String, String> map;
        ClassInfoUtil.displayGroup(this, linearLayout, getContentMap());
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_android_environment;
    }

    private LinkedHashMap<String, String> getContentMap() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        map.put("getExternalStorageState()", Environment.getExternalStorageState());

        map.put("getRootDirectory()", Environment.getRootDirectory().getAbsolutePath());
        map.put("getDataDirectory()", Environment.getDataDirectory().getAbsolutePath());
        map.put("getDownloadCacheDirectory()", Environment.getDownloadCacheDirectory().getAbsolutePath());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            map.put("getStorageDirectory()", Environment.getStorageDirectory().getAbsolutePath());
        }
        map.put("getExternalStorageDirectory()", Environment.getExternalStorageDirectory().getAbsolutePath()
                + " ----> " + Environment.getExternalStorageState(Environment.getExternalStorageDirectory()));

        Field[] fields = Environment.class.getFields();
        for (Field field : fields) {
            if (field.getName().startsWith("DIRECTORY_")) {
                String value;
                try {
                    value = Objects.requireNonNull(field.get(field)).toString();
                } catch (IllegalAccessException | IllegalArgumentException e) {
                    value = "unknown";
                }
                map.put("getExternalStoragePublicDirectory(" + field.getName()+ ")",
                        Environment.getExternalStoragePublicDirectory(value).getAbsolutePath()
                                + " ----> "
                                + Environment.getExternalStorageState(Environment.getExternalStoragePublicDirectory(value)));
            }
        }

        return map;
    }
}
