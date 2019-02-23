package com.zs.androidappfw.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import com.zs.androidappfw.R;
import com.zs.androidappfw.ui.base.BaseActivity;

/**
 * Created by shoes on 2017/10/25.
 *
 */

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
    }

    public void toTextView(View view) {
        startActivity(new Intent(MainActivity.this, TextViewAct.class));
    }

    public void toCheckedTextView(View view) {
        startActivity(new Intent(MainActivity.this, CheckedTextViewAct.class));
    }

    public void toEditText(View view) {
        startActivity(new Intent(MainActivity.this, EditTextAct.class));
    }

    public void toButton(View view) {
        startActivity(new Intent(MainActivity.this, ButtonAct.class));
    }

    public void toCompoundButton(View view) {
        startActivity(new Intent(MainActivity.this, CompoundButtonAct.class));
    }

    public void toCheckBox(View view) {
        startActivity(new Intent(MainActivity.this, CheckBoxAct.class));
    }

    public void toRadioBtn(View view) {
        startActivity(new Intent(MainActivity.this, RadioButtonAct.class));
    }

    public void toSwitch(View view) {
        startActivity(new Intent(MainActivity.this, SwitchAct.class));
    }

    public void toToggleBtn(View view) {
        startActivity(new Intent(MainActivity.this, ToggleButtonAct.class));
    }

    public void toImageView(View view) {
        startActivity(new Intent(MainActivity.this, ImageViewAct.class));
    }
}
