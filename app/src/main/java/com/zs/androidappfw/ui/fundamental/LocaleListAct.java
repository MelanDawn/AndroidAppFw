package com.zs.androidappfw.ui.fundamental;

import android.os.Bundle;
import android.os.LocaleList;
import android.widget.LinearLayout;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;
import com.zs.androidappfw.utils.ClassInfoUtil;

import java.util.LinkedHashMap;
import java.util.Locale;

public class LocaleListAct extends BaseTitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_device_info);
        LinearLayout linearLayout = findViewById(R.id.device_info_ll);
        ClassInfoUtil.displayClassInfo(this, linearLayout, LocaleList.class);
        ClassInfoUtil.displayClassInfo(this, linearLayout, Locale.class);
        ClassInfoUtil.displayGroupTitle(this, linearLayout);
        ClassInfoUtil.displayGroup(this, linearLayout, getContentMap());
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_android_locale_list;
    }

    private LinkedHashMap<String, String> getContentMap() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        LocaleList list = LocaleList.getDefault();
        for (int i = 0; i < list.size(); i++) {
            Locale locale = list.get(i);
            StringBuilder sb = new StringBuilder();
            sb.append(locale.getCountry()).append("\n")
                    .append(locale.getDisplayCountry()).append("\n")
                    .append(locale.getDisplayLanguage()).append("\n")
                    .append(locale.getDisplayName()).append("\n")
                    .append(locale.getDisplayVariant()).append("\n")
                    .append(locale.toString());
            map.put(Locale.class.getName() + "@" + Integer.toHexString(hashCode()), sb.substring(0));
        }

        return map;
    }
}
