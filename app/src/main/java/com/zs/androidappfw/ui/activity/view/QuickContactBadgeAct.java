package com.zs.androidappfw.ui.activity.view;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.widget.QuickContactBadge;

import com.zs.androidappfw.R;
import com.zs.androidappfw.ui.base.BaseActivity;

public class QuickContactBadgeAct extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_quick_contact_badge);

        QuickContactBadge badge = findViewById(R.id.quick_contact_badge_1);
        badge.assignContactFromPhone("15527305674", true);
        badge.setMode(ContactsContract.QuickContact.MODE_SMALL);
    }

}
