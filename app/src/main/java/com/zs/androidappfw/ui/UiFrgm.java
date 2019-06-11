package com.zs.androidappfw.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.TableRow;

import com.zs.androidappfw.R;
import com.zs.androidappfw.ui.activity.animation.AnimationAttributeAct;
import com.zs.androidappfw.ui.activity.animation.AnimationFrameAct;
import com.zs.androidappfw.ui.activity.animation.AnimationTweenAct;
import com.zs.androidappfw.ui.activity.view.AbsSeekBarAct;
import com.zs.androidappfw.ui.activity.view.AutoCompleteTextViewAct;
import com.zs.androidappfw.ui.activity.view.ButtonAct;
import com.zs.androidappfw.ui.activity.view.CheckBoxAct;
import com.zs.androidappfw.ui.activity.view.CheckedTextViewAct;
import com.zs.androidappfw.ui.activity.view.ChronometerAct;
import com.zs.androidappfw.ui.activity.view.CompoundButtonAct;
import com.zs.androidappfw.ui.activity.view.EditTextAct;
import com.zs.androidappfw.ui.activity.view.ExtractEditTextAct;
import com.zs.androidappfw.ui.activity.view.ImageButtonAct;
import com.zs.androidappfw.ui.activity.view.ImageViewAct;
import com.zs.androidappfw.ui.activity.view.MultiAutoCompleteTextViewAct;
import com.zs.androidappfw.ui.activity.view.ProgressBarAct;
import com.zs.androidappfw.ui.activity.view.QuickContactBadgeAct;
import com.zs.androidappfw.ui.activity.view.RadioButtonAct;
import com.zs.androidappfw.ui.activity.view.RatingBarAct;
import com.zs.androidappfw.ui.activity.view.SeekBarAct;
import com.zs.androidappfw.ui.activity.view.TextClockAct;
import com.zs.androidappfw.ui.activity.view.TextViewAct;
import com.zs.androidappfw.ui.activity.view.ToggleButtonAct;
import com.zs.androidappfw.ui.activity.view.viewgroup.AbsoluteLayoutAct;
import com.zs.androidappfw.ui.activity.view.viewgroup.ActionMenuViewAct;
import com.zs.androidappfw.ui.activity.view.viewgroup.AppWidgetHostViewAct;
import com.zs.androidappfw.ui.activity.view.viewgroup.CalendarViewAct;
import com.zs.androidappfw.ui.activity.view.viewgroup.DatePickerAct;
import com.zs.androidappfw.ui.activity.view.viewgroup.DialerFilterAct;
import com.zs.androidappfw.ui.activity.view.viewgroup.FrameLayoutAct;
import com.zs.androidappfw.ui.activity.view.viewgroup.GestureOverlayViewAct;
import com.zs.androidappfw.ui.activity.view.viewgroup.GridLayoutAct;
import com.zs.androidappfw.ui.activity.view.viewgroup.HorizontalScrollViewAct;
import com.zs.androidappfw.ui.activity.view.viewgroup.ImageSwitcherAct;
import com.zs.androidappfw.ui.activity.view.viewgroup.LinearLayoutAct;
import com.zs.androidappfw.ui.activity.view.viewgroup.MediaControllerAct;
import com.zs.androidappfw.ui.activity.view.viewgroup.NumberPickerAct;
import com.zs.androidappfw.ui.activity.view.viewgroup.RadioGroupAct;
import com.zs.androidappfw.ui.activity.view.viewgroup.RelativeLayoutAct;
import com.zs.androidappfw.ui.activity.view.viewgroup.ScrollViewAct;
import com.zs.androidappfw.ui.activity.view.viewgroup.SearchViewAct;
import com.zs.androidappfw.ui.activity.view.viewgroup.SwitchAct;
import com.zs.androidappfw.ui.activity.view.viewgroup.TabHostAct;
import com.zs.androidappfw.ui.activity.view.viewgroup.TabWidgetAct;
import com.zs.androidappfw.ui.activity.view.viewgroup.TableLayoutAct;
import com.zs.androidappfw.ui.activity.view.viewgroup.TableRowAct;
import com.zs.androidappfw.ui.activity.view.viewgroup.TextSwitcherAct;
import com.zs.androidappfw.ui.activity.view.viewgroup.TimePickerAct;
import com.zs.androidappfw.ui.activity.view.viewgroup.TwoLineListItemAct;
import com.zs.androidappfw.ui.activity.view.viewgroup.ViewAnimatorAct;
import com.zs.androidappfw.ui.activity.view.viewgroup.ViewFlipperAct;
import com.zs.androidappfw.ui.activity.view.viewgroup.ViewGroupAct;
import com.zs.androidappfw.ui.activity.view.viewgroup.ViewSwitcherAct;
import com.zs.androidappfw.ui.activity.view.viewgroup.WebViewAct;
import com.zs.androidappfw.ui.activity.view.viewgroup.ZoomControlsAct;
import com.zs.androidappfw.ui.base.BaseActivity;
import com.zs.androidappfw.ui.base.BaseFragment;

public class UiFrgm extends BaseFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frgm_main_ui, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
    }

    private void initView(View view) {
        initAndSetClickListener(view, R.id.ui_to_progress_bar);
        initAndSetClickListener(view, R.id.ui_to_abs_seek_bar);
        initAndSetClickListener(view, R.id.ui_to_rating_bar);
        initAndSetClickListener(view, R.id.ui_to_seek_bar);
        initAndSetClickListener(view, R.id.ui_to_image_view);
        initAndSetClickListener(view, R.id.ui_to_image_button);
        initAndSetClickListener(view, R.id.ui_to_quick_contact_badge);
        initAndSetClickListener(view, R.id.ui_to_text_view);
        initAndSetClickListener(view, R.id.ui_to_checked_text_view);
        initAndSetClickListener(view, R.id.ui_to_chronometer);
        initAndSetClickListener(view, R.id.ui_to_text_clock);
        initAndSetClickListener(view, R.id.ui_to_edit_text);
        initAndSetClickListener(view, R.id.ui_to_auto_complete_text_view);
        initAndSetClickListener(view, R.id.ui_to_multi_auto_complete_text_view);
        initAndSetClickListener(view, R.id.ui_to_extract_edit_text);
        initAndSetClickListener(view, R.id.ui_to_button);
        initAndSetClickListener(view, R.id.ui_to_compound_button);
        initAndSetClickListener(view, R.id.ui_to_check_box);
        initAndSetClickListener(view, R.id.ui_to_radio_btn);
        initAndSetClickListener(view, R.id.ui_to_switch);
        initAndSetClickListener(view, R.id.ui_to_toggle_btn);
        initAndSetClickListener(view, R.id.ui_to_view_group);
        initAndSetClickListener(view, R.id.ui_to_absolute_layout);
        initAndSetClickListener(view, R.id.ui_to_web_view);
        initAndSetClickListener(view, R.id.ui_to_frame_layout);
        initAndSetClickListener(view, R.id.ui_to_app_widget_host_view);
        initAndSetClickListener(view, R.id.ui_to_calendar_view);
        initAndSetClickListener(view, R.id.ui_to_date_picker);
        initAndSetClickListener(view, R.id.ui_to_time_picker);
        initAndSetClickListener(view, R.id.ui_to_gesture_overlay_view);
        initAndSetClickListener(view, R.id.ui_to_media_controller);
        initAndSetClickListener(view, R.id.ui_to_horizontal_scroll_view);
        initAndSetClickListener(view, R.id.ui_to_scroll_view);
        initAndSetClickListener(view, R.id.ui_to_tab_host);
        initAndSetClickListener(view, R.id.ui_to_view_animator);
        initAndSetClickListener(view, R.id.ui_to_view_flipper);
        initAndSetClickListener(view, R.id.ui_to_view_switcher);
        initAndSetClickListener(view, R.id.ui_to_image_switcher);
        initAndSetClickListener(view, R.id.ui_to_text_switcher);
        initAndSetClickListener(view, R.id.ui_to_grid_layout);
        initAndSetClickListener(view, R.id.ui_to_linear_layout);
        initAndSetClickListener(view, R.id.ui_to_action_menu_view);
        initAndSetClickListener(view, R.id.ui_to_number_picker);
        initAndSetClickListener(view, R.id.ui_to_radio_group);
        initAndSetClickListener(view, R.id.ui_to_search_view);
        initAndSetClickListener(view, R.id.ui_to_tab_widget);
        initAndSetClickListener(view, R.id.ui_to_table_layout);
        initAndSetClickListener(view, R.id.ui_to_table_row);
        initAndSetClickListener(view, R.id.ui_to_zoom_controls);
        initAndSetClickListener(view, R.id.ui_to_relative_layout);
        initAndSetClickListener(view, R.id.ui_to_dialer_filter);
        initAndSetClickListener(view, R.id.ui_to_two_line_list_item);
        initAndSetClickListener(view, R.id.ui_to_animation_frame);
        initAndSetClickListener(view, R.id.ui_to_animation_tween);
        initAndSetClickListener(view, R.id.ui_to_animation_attribute);
    }

    private void initAndSetClickListener(View view, int id) {
        view.findViewById(id).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ui_to_progress_bar:
                startActivity(ProgressBarAct.class);
                break;
            case R.id.ui_to_abs_seek_bar:
                startActivity(AbsSeekBarAct.class);
                break;
            case R.id.ui_to_rating_bar:
                startActivity(RatingBarAct.class);
                break;
            case R.id.ui_to_seek_bar:
                startActivity(SeekBarAct.class);
                break;
            case R.id.ui_to_image_view:
                startActivity(ImageViewAct.class);
                break;
            case R.id.ui_to_image_button:
                startActivity(ImageButtonAct.class);
                break;
            case R.id.ui_to_quick_contact_badge:
                startActivity(QuickContactBadgeAct.class);
                break;
            case R.id.ui_to_text_view:
                startActivity(TextViewAct.class);
                break;
            case R.id.ui_to_checked_text_view:
                startActivity(CheckedTextViewAct.class);
                break;
            case R.id.ui_to_chronometer:
                startActivity(ChronometerAct.class);
                break;
            case R.id.ui_to_text_clock:
                startActivity(TextClockAct.class);
                break;
            case R.id.ui_to_edit_text:
                startActivity(EditTextAct.class);
                break;
            case R.id.ui_to_auto_complete_text_view:
                startActivity(AutoCompleteTextViewAct.class);
                break;
            case R.id.ui_to_multi_auto_complete_text_view:
                startActivity(MultiAutoCompleteTextViewAct.class);
                break;
            case R.id.ui_to_extract_edit_text:
                startActivity(ExtractEditTextAct.class);
                break;
            case R.id.ui_to_button:
                startActivity(ButtonAct.class);
                break;
            case R.id.ui_to_compound_button:
                startActivity(CompoundButtonAct.class);
                break;
            case R.id.ui_to_check_box:
                startActivity(CheckBoxAct.class);
                break;
            case R.id.ui_to_radio_btn:
                startActivity(RadioButtonAct.class);
                break;
            case R.id.ui_to_switch:
                startActivity(SwitchAct.class);
                break;
            case R.id.ui_to_toggle_btn:
                startActivity(ToggleButtonAct.class);
                break;
            case R.id.ui_to_view_group:
                startActivity(ViewGroupAct.class);
                break;
            case R.id.ui_to_absolute_layout:
                startActivity(AbsoluteLayoutAct.class);
                break;
            case R.id.ui_to_web_view:
                startActivity(WebViewAct.class);
                break;
            case R.id.ui_to_frame_layout:
                startActivity(FrameLayoutAct.class);
                break;
            case R.id.ui_to_app_widget_host_view:
                startActivity(AppWidgetHostViewAct.class);
                break;
            case R.id.ui_to_calendar_view:
                startActivity(CalendarViewAct.class);
                break;
            case R.id.ui_to_date_picker:
                startActivity(DatePickerAct.class);
                break;
            case R.id.ui_to_time_picker:
                startActivity(TimePickerAct.class);
                break;
            case R.id.ui_to_gesture_overlay_view:
                startActivity(GestureOverlayViewAct.class);
                break;
            case R.id.ui_to_media_controller:
                startActivity(MediaControllerAct.class);
                break;
            case R.id.ui_to_horizontal_scroll_view:
                startActivity(HorizontalScrollViewAct.class);
                break;
            case R.id.ui_to_scroll_view:
                startActivity(ScrollViewAct.class);
                break;
            case R.id.ui_to_tab_host:
                startActivity(TabHostAct.class);
                break;
            case R.id.ui_to_view_animator:
                startActivity(ViewAnimatorAct.class);
                break;
            case R.id.ui_to_view_flipper:
                startActivity(ViewFlipperAct.class);
                break;
            case R.id.ui_to_view_switcher:
                startActivity(ViewSwitcherAct.class);
                break;
            case R.id.ui_to_image_switcher:
                startActivity(ImageSwitcherAct.class);
                break;
            case R.id.ui_to_text_switcher:
                startActivity(TextSwitcherAct.class);
                break;
            case R.id.ui_to_grid_layout:
                startActivity(GridLayoutAct.class);
                break;
            case R.id.ui_to_linear_layout:
                startActivity(LinearLayoutAct.class);
                break;
            case R.id.ui_to_action_menu_view:
                startActivity(ActionMenuViewAct.class);
                break;
            case R.id.ui_to_number_picker:
                startActivity(NumberPickerAct.class);
                break;
            case R.id.ui_to_radio_group:
                startActivity(RadioButtonAct.class);
                break;
            case R.id.ui_to_search_view:
                startActivity(SearchViewAct.class);
                break;
            case R.id.ui_to_tab_widget:
                startActivity(TabWidgetAct.class);
                break;
            case R.id.ui_to_table_layout:
                startActivity(TableLayoutAct.class);
                break;
            case R.id.ui_to_table_row:
                startActivity(TableRowAct.class);
                break;
            case R.id.ui_to_zoom_controls:
                startActivity(ZoomControlsAct.class);
                break;
            case R.id.ui_to_relative_layout:
                startActivity(RelativeLayoutAct.class);
                break;
            case R.id.ui_to_dialer_filter:
                startActivity(DialerFilterAct.class);
                break;
            case R.id.ui_to_two_line_list_item:
                startActivity(TwoLineListItemAct.class);
                break;
            case R.id.ui_to_animation_frame:
                startActivity(AnimationFrameAct.class);
                break;
            case R.id.ui_to_animation_tween:
                startActivity(AnimationTweenAct.class);
                break;
            case R.id.ui_to_animation_attribute:
                startActivity(AnimationAttributeAct.class);
                break;
        }
    }

    private void startActivity(Class<? extends BaseActivity> cls) {
        startActivity(new Intent(getActivity(), cls));
    }
}
