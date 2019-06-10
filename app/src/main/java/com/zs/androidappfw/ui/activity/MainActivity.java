package com.zs.androidappfw.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.zs.androidappfw.R;
import com.zs.androidappfw.ui.activity.animation.AnimationAttributeAct;
import com.zs.androidappfw.ui.activity.animation.AnimationFrameAct;
import com.zs.androidappfw.ui.activity.animation.AnimationTweenAct;
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

    public void toAbsoluteLayout(View view) {
        startActivity(new Intent(MainActivity.this, AbsoluteLayoutAct.class));
    }

    public void toWebView(View view) {
        startActivity(new Intent(MainActivity.this, WebViewAct.class));
    }

    public void toFrameLayout(View view) {
        startActivity(new Intent(MainActivity.this, FrameLayoutAct.class));
    }

    public void toAppWidgetHostView(View view) {
        startActivity(new Intent(MainActivity.this, AppWidgetHostViewAct.class));
    }

    public void toCalendarView(View view) {
        startActivity(new Intent(MainActivity.this, CalendarViewAct.class));
    }

    public void toGestureOverlayView(View view) {
        startActivity(new Intent(MainActivity.this, GestureOverlayViewAct.class));
    }

    public void toDatePicker(View view) {
        startActivity(new Intent(MainActivity.this, DatePickerAct.class));
    }

    public void toMediaController(View view) {
        startActivity(new Intent(MainActivity.this, MediaControllerAct.class));
    }

    public void toHorizontalScrollView(View view) {
        startActivity(new Intent(MainActivity.this, HorizontalScrollViewAct.class));
    }

    public void toScrollView(View view) {
        startActivity(new Intent(MainActivity.this, ScrollViewAct.class));
    }

    public void toTabHost(View view) {
        startActivity(new Intent(MainActivity.this, TabHostAct.class));
    }

    public void toTimePicker(View view) {
        startActivity(new Intent(MainActivity.this, TimePickerAct.class));
    }

    public void toViewAnimator(View view) {
        startActivity(new Intent(MainActivity.this, ViewAnimatorAct.class));
    }

    public void toViewFlipper(View view) {
        startActivity(new Intent(MainActivity.this, ViewFlipperAct.class));
    }

    public void toViewSwitcher(View view) {
        startActivity(new Intent(MainActivity.this, ViewSwitcherAct.class));
    }

    public void toImageSwitcher(View view) {
        startActivity(new Intent(MainActivity.this, ImageSwitcherAct.class));
    }

    public void toTextSwitcher(View view) {
        startActivity(new Intent(MainActivity.this, TextSwitcherAct.class));
    }

    public void toGridLayout(View view) {
        startActivity(new Intent(MainActivity.this, GridLayoutAct.class));
    }

    public void toLinearLayout(View view) {
        startActivity(new Intent(MainActivity.this, LinearLayoutAct.class));
    }

    public void toActionMenuView(View view) {
        startActivity(new Intent(MainActivity.this, ActionMenuViewAct.class));
    }

    public void toNumberPicker(View view) {
        startActivity(new Intent(MainActivity.this, NumberPickerAct.class));
    }

    public void toRadioGroup(View view) {
        startActivity(new Intent(MainActivity.this, RadioGroupAct.class));
    }

    public void toSearchView(View view) {
        startActivity(new Intent(MainActivity.this, SearchViewAct.class));
    }

    public void toTabWidget(View view) {
        startActivity(new Intent(MainActivity.this, TabWidgetAct.class));
    }

    public void toTableLayout(View view) {
        startActivity(new Intent(MainActivity.this, TableLayoutAct.class));
    }

    public void toTableRow(View view) {
        startActivity(new Intent(MainActivity.this, TableRowAct.class));
    }

    public void toZoomControls(View view) {
        startActivity(new Intent(MainActivity.this, ZoomControlsAct.class));
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

    public void toAnimationFrame(View view) {
        startActivity(AnimationFrameAct.class);
    }

    public void toAnimationTween(View view) {
        startActivity(AnimationTweenAct.class);
    }

    public void toAnimationAttribute(View view) {
        startActivity(AnimationAttributeAct.class);
    }

    private void startActivity(Class<?> cls) {
        startActivity(new Intent(MainActivity.this, cls));
    }
}
