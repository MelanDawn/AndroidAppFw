package com.zs.androidappfw.ui.view;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.QuickContactBadge;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;

public class QuickContactBadgeAct extends BaseTitleActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_quick_contact_badge);

        QuickContactBadge badge = findViewById(R.id.quick_contact_badge_1);
        badge.assignContactFromPhone("15527305674", true);
        badge.setMode(ContactsContract.QuickContact.MODE_SMALL);
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_view_quick_contact_badge;
    }
}
