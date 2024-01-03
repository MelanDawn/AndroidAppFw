package com.zs.androidappfw.ui.lifecycle;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleFragmentActivity;

public class FragmentDynamicAct extends BaseTitleFragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_fgm_dynamic);

        Fragment fragment = new FragmentLifecycleFgm();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_lifecycle_dynamic_fl, fragment).commit();

        findViewById(R.id.fragment_lifecycle_to_static).setOnClickListener((view) -> {
            startActivity(new Intent(this, FragmentStaticAct.class));
        });
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_fragment_dynamic;
    }
}
