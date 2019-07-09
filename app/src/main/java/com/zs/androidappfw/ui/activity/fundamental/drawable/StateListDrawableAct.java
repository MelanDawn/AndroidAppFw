package com.zs.androidappfw.ui.activity.fundamental.drawable;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.zs.androidappfw.R;
import com.zs.androidappfw.ui.base.BaseActivity;

public class StateListDrawableAct extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_drawable_state_list);

        final Button button = findViewById(R.id.drawable_state_list_btn1);

        findViewById(R.id.drawable_state_list_btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setEnabled(!button.isEnabled());
            }
        });
    }
}
