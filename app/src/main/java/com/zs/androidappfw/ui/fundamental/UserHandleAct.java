package com.zs.androidappfw.ui.fundamental;

import android.content.Context;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;
import com.zs.androidappfw.utils.ClassInfoUtil;

import java.util.LinkedHashMap;
import java.util.List;

public class UserHandleAct extends BaseTitleActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_device_info);
        LinearLayout linearLayout = findViewById(R.id.device_info_ll);
        ClassInfoUtil.displayClassInfo(this, linearLayout, UserHandle.class);
        ClassInfoUtil.displayGroupTitle(this, linearLayout);
        ClassInfoUtil.displayGroup(this, linearLayout, getContentMap());
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_android_user_handle;
    }

    private LinkedHashMap<String, String> getContentMap() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        UserManager userManager = (UserManager) getSystemService(Context.USER_SERVICE);
        List<UserHandle> userHandles = userManager.getUserProfiles();

        for (UserHandle userHandle : userHandles) {
            map.put(userHandle.toString(), userHandle.toString());
        }

        return map;
    }
}
