package com.zs.androidappfw.ui.fundamental;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseActivity;

import java.util.LinkedHashMap;

public class DeviceInfoAct extends BaseActivity {

    private static final String CENTER_RED = "CENTER_RED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_device_info);

        LinearLayout linearLayout = findViewById(R.id.device_info_ll);

        LinkedHashMap<String, String> map = getDeviceInfo();
        for (String key: map.keySet()) {
            String value = map.get(key);
            TextView left = new TextView(this);
            left.setText(key);
            linearLayout.addView(left);
            if (!CENTER_RED.equals(value)) {
                left.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                left.setPadding(50, 0, 0, 0);

                TextView right = new TextView(this);
                right.setText(value);
                right.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                right.setPadding(50, 0, 0, 0);
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

    private LinkedHashMap<String, String> getDeviceInfo() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("Build.", CENTER_RED);
        map.put("BOARD", Build.BOARD);
        map.put("BOOTLOADER", Build.BOOTLOADER);
        map.put("BRAND", Build.BRAND);
        map.put("DEVICE", Build.DEVICE);
        map.put("DISPLAY", Build.DISPLAY);
        map.put("FINGERPRINT", Build.FINGERPRINT);
        map.put("HARDWARE", Build.HARDWARE);
        map.put("HOST", Build.HOST);
        map.put("ID", Build.ID);
        map.put("MANUFACTURER", Build.MANUFACTURER);
        map.put("MODEL", Build.MODEL);
        map.put("PRODUCT", Build.PRODUCT);
        map.put("TAGS", Build.TAGS);
        map.put("TYPE", Build.TYPE);
        map.put("UNKNOWN", Build.UNKNOWN);
        map.put("USER", Build.USER);
        map.put("CPU_ABI --Deprecated API 21", Build.CPU_ABI);
        map.put("CPU_ABI2 --Deprecated API 21", Build.CPU_ABI2);
        map.put("RADIO --Deprecated API 15", Build.RADIO);
        map.put("SERIAL --Not Recommended", Build.SERIAL);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            for (int i = 0; i < Build.SUPPORTED_32_BIT_ABIS.length; i++) {
                map.put("SUPPORTED_32_BIT_ABIS " + i, Build.SUPPORTED_32_BIT_ABIS[0]);
            }
            for (int i = 0; i < Build.SUPPORTED_64_BIT_ABIS.length; i++) {
                map.put("SUPPORTED_64_BIT_ABIS " + i, Build.SUPPORTED_64_BIT_ABIS[0]);
            }
        }
        map.put("TIME", String.valueOf(Build.TIME));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                map.put("getSerial() --Add API 26", Build.getSerial());
            } else {
                map.put("getSerial()", "NO Permission");
            }
        }
        map.put("getRadioVersion()", String.valueOf(Build.getRadioVersion()));

        map.put("Build.VERSION.", CENTER_RED);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) map.put("BASE_OS", Build.VERSION.BASE_OS);
        map.put("CODENAME", Build.VERSION.CODENAME);
        map.put("INCREMENTAL", Build.VERSION.INCREMENTAL);
        map.put("RELEASE", Build.VERSION.RELEASE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) map.put("SECURITY_PATCH", Build.VERSION.SECURITY_PATCH);
        map.put("SDK --Deprecated API 15", Build.VERSION.SDK);
        map.put("SDK_INT", String.valueOf(Build.VERSION.SDK_INT));

        map.put("Build.VERSION_CODES.", CENTER_RED);
        map.put("CUR_DEVELOPMENT", String.valueOf(Build.VERSION_CODES.CUR_DEVELOPMENT));

        return map;
    }
}
