package com.zs.androidappfw.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zs.androidappfw.R;

/**
 * Created by shoes on 2017/10/25.
 *
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
    }

    public void toTextView(View view) {
        startActivity(new Intent(MainActivity.this, TextViewAct.class));
    }

    public void toEditText(View view) {
        startActivity(new Intent(MainActivity.this, EditTextAct.class));
    }

    public void toButton(View view) {
        startActivity(new Intent(MainActivity.this, ButtonAct.class));
    }

    public void toImageView(View view) {
        startActivity(new Intent(MainActivity.this, ImageViewAct.class));
    }
}
