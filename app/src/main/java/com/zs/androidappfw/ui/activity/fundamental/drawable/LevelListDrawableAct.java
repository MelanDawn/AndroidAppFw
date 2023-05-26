package com.zs.androidappfw.ui.activity.fundamental.drawable;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.ui.base.BaseActivity;

public class LevelListDrawableAct extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_drawable_level_list);

        final ImageView imageView = findViewById(R.id.drawable_level_iv);

        findViewById(R.id.drawable_level_list_btn0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageLevel(0);
            }
        });
        findViewById(R.id.drawable_level_list_btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageLevel(5);
            }
        });

        findViewById(R.id.drawable_level_list_btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageLevel(15);
            }
        });

        findViewById(R.id.drawable_level_list_btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageLevel(25);
            }
        });
    }
}
