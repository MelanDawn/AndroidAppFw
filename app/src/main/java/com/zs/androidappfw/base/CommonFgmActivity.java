package com.zs.androidappfw.base;

import android.content.Intent;
import android.os.Bundle;

import com.zs.androidappfw.R;

public class CommonFgmActivity extends BaseTitleFragmentActivity {

    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_fgm_common);

        mIntent = getIntent();
        try {
            String className = getIntent().getStringExtra(KEY_FRAGMENT_CLASS);
            if (className != null) {
                getSupportFragmentManager().beginTransaction().add(R.id.fgm_common_fl,
                        (BaseClickFragment) Class.forName(className).newInstance()).commit();
            }
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected int getTitleResId() {
        return mIntent.getIntExtra(BaseTitleFragment.FRAGMENT_TITLE_RES_ID, 0);
    }
}
