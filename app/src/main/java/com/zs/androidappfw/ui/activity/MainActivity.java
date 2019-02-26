package com.zs.androidappfw.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

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

    public void toProgressBar(View view) {
        startActivity(new Intent(MainActivity.this, ProgressBarAct.class));
    }

    public void toAbsSeekBar(View view) {
        startActivity(new Intent(MainActivity.this, AbsSeekBarAct.class));
    }

    public void toRatingBar(View view) {
        startActivity(new Intent(MainActivity.this, RatingBarAct.class));
    }

    public void toSeekBar(View view) {
        startActivity(new Intent(MainActivity.this, SeekBarAct.class));
    }

    public void toImageView(View view) {
        startActivity(new Intent(MainActivity.this, ImageViewAct.class));
    }

    public void toImageButton(View view) {
        startActivity(new Intent(MainActivity.this, ImageButtonAct.class));
    }

    public void toQuickContactBadge(View view) {
        startActivity(new Intent(MainActivity.this, QuickContactBadgeAct.class));
    }

    public void toTextView(View view) {
        startActivity(new Intent(MainActivity.this, TextViewAct.class));
    }

    public void toCheckedTextView(View view) {
        startActivity(new Intent(MainActivity.this, CheckedTextViewAct.class));
    }

    public void toChronometer(View view) {
        startActivity(new Intent(MainActivity.this, ChronometerAct.class));
    }

    public void toTextClock(View view) {
        startActivity(new Intent(MainActivity.this, TextClockAct.class));
    }

    public void toEditText(View view) {
        startActivity(new Intent(MainActivity.this, EditTextAct.class));
    }

    public void toAutoCompleteTextView(View view) {
        startActivity(new Intent(MainActivity.this, AutoCompleteTextViewAct.class));
    }

    public void toMultiAutoCompleteTextView(View view) {
        startActivity(new Intent(MainActivity.this, MultiAutoCompleteTextViewAct.class));
    }

    public void toExtractEditText(View view) {
        startActivity(new Intent(MainActivity.this, ExtractEditTextAct.class));
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

    public void toViewGroup(View view) {
        startActivity(new Intent(MainActivity.this, ViewGroupAct.class));
    }

    public void toRelativeLayout(View view) {
        startActivity(new Intent(MainActivity.this, RelativeLayoutAct.class));
    }

    public void toDialerFilter(View view) {
        startActivity(new Intent(MainActivity.this, DialerFilterAct.class));
    }

    public void toTwoLineListItem(View view) {
        startActivity(new Intent(MainActivity.this, TwoLineListItemAct.class));
    }
}
