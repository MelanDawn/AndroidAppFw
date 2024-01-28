package com.zs.androidappfw.ui.fundamental;

import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;
import com.zs.androidappfw.utils.ClassInfoUtil;

import java.util.LinkedHashMap;

public class ProcessAct extends BaseTitleActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_device_info);
        LinearLayout linearLayout = findViewById(R.id.device_info_ll);
        ClassInfoUtil.displayClassInfo(this, linearLayout, android.os.Process.class);
        ClassInfoUtil.displayGroupTitle(this, linearLayout);
        ClassInfoUtil.displayGroup(this, linearLayout, getContentMap());
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_android_process;
    }

    private LinkedHashMap<String, String> getContentMap() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        map.put("My Process Info", "");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            map.put("ProcessName", Process.myProcessName());
        }
        map.put("PID", String.valueOf(Process.myPid()));
        map.put("Thread ID", String.valueOf(Process.myTid()));
        map.put("UID", String.valueOf(Process.myUid()));
        map.put("UserHandle", Process.myUserHandle().toString());

        map.put("is64Bit", String.valueOf(Process.is64Bit()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
            map.put("isIsolated", String.valueOf(Process.isIsolated()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            map.put("isSdkSandbox", String.valueOf(Process.isSdkSandbox()));

        map.put("Process Info", "");
        StringBuilder result = new StringBuilder();
        String[] userArr = {
                "root", "system",
        };
        for (String user : userArr) {
            result.append(user).append("\t")
                    .append(Process.getUidForName(user)).append("\t")
                    .append(Process.getGidForName(user)).append("\n");
        }
        map.put("USER\tUID\tGID", result.substring(0));

        return map;
    }
}
