package com.zs.androidappfw.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseClickFragment;
import com.zs.androidappfw.ui.animation.AnimationAttributeAct;
import com.zs.androidappfw.ui.animation.AnimationFrameAct;
import com.zs.androidappfw.ui.animation.AnimationTweenAct;
import com.zs.androidappfw.ui.view.AbsSeekBarAct;
import com.zs.androidappfw.ui.view.AutoCompleteTextViewAct;
import com.zs.androidappfw.ui.view.ButtonAct;
import com.zs.androidappfw.ui.view.CheckBoxAct;
import com.zs.androidappfw.ui.view.CheckedTextViewAct;
import com.zs.androidappfw.ui.view.ChronometerAct;
import com.zs.androidappfw.ui.view.CompoundButtonAct;
import com.zs.androidappfw.ui.view.EditTextAct;
import com.zs.androidappfw.ui.view.ExtractEditTextAct;
import com.zs.androidappfw.ui.view.ImageButtonAct;
import com.zs.androidappfw.ui.view.ImageViewAct;
import com.zs.androidappfw.ui.view.MultiAutoCompleteTextViewAct;
import com.zs.androidappfw.ui.view.ProgressBarAct;
import com.zs.androidappfw.ui.view.QuickContactBadgeAct;
import com.zs.androidappfw.ui.view.RadioButtonAct;
import com.zs.androidappfw.ui.view.RatingBarAct;
import com.zs.androidappfw.ui.view.SeekBarAct;
import com.zs.androidappfw.ui.view.SpaceAct;
import com.zs.androidappfw.ui.view.SwitchAct;
import com.zs.androidappfw.ui.view.TextClockAct;
import com.zs.androidappfw.ui.view.TextViewAct;
import com.zs.androidappfw.ui.view.ToggleButtonAct;
import com.zs.androidappfw.ui.view.ViewStubAct;
import com.zs.androidappfw.ui.view.tag.BlinkAct;
import com.zs.androidappfw.ui.view.tag.IncludeAct;
import com.zs.androidappfw.ui.view.tag.MergeAct;
import com.zs.androidappfw.ui.view.tag.RequestFocusAct;
import com.zs.androidappfw.ui.view.tag.TagAct;
import com.zs.androidappfw.ui.view.viewgroup.AbsoluteLayoutAct;
import com.zs.androidappfw.ui.view.viewgroup.ActionMenuViewAct;
import com.zs.androidappfw.ui.view.viewgroup.AppWidgetHostViewAct;
import com.zs.androidappfw.ui.view.viewgroup.CalendarViewAct;
import com.zs.androidappfw.ui.view.viewgroup.DatePickerAct;
import com.zs.androidappfw.ui.view.viewgroup.DialerFilterAct;
import com.zs.androidappfw.ui.view.viewgroup.FrameLayoutAct;
import com.zs.androidappfw.ui.view.viewgroup.GestureOverlayViewAct;
import com.zs.androidappfw.ui.view.viewgroup.GridLayoutAct;
import com.zs.androidappfw.ui.view.viewgroup.HorizontalScrollViewAct;
import com.zs.androidappfw.ui.view.viewgroup.ImageSwitcherAct;
import com.zs.androidappfw.ui.view.viewgroup.LinearLayoutAct;
import com.zs.androidappfw.ui.view.viewgroup.MediaControllerAct;
import com.zs.androidappfw.ui.view.viewgroup.NumberPickerAct;
import com.zs.androidappfw.ui.view.viewgroup.RelativeLayoutAct;
import com.zs.androidappfw.ui.view.viewgroup.ScrollViewAct;
import com.zs.androidappfw.ui.view.viewgroup.SearchViewAct;
import com.zs.androidappfw.ui.view.viewgroup.TabHostAct;
import com.zs.androidappfw.ui.view.viewgroup.TabWidgetAct;
import com.zs.androidappfw.ui.view.viewgroup.TableLayoutAct;
import com.zs.androidappfw.ui.view.viewgroup.TableRowAct;
import com.zs.androidappfw.ui.view.viewgroup.TextSwitcherAct;
import com.zs.androidappfw.ui.view.viewgroup.TimePickerAct;
import com.zs.androidappfw.ui.view.viewgroup.TwoLineListItemAct;
import com.zs.androidappfw.ui.view.viewgroup.ViewAnimatorAct;
import com.zs.androidappfw.ui.view.viewgroup.ViewFlipperAct;
import com.zs.androidappfw.ui.view.viewgroup.ViewGroupAct;
import com.zs.androidappfw.ui.view.viewgroup.ViewSwitcherAct;
import com.zs.androidappfw.ui.view.viewgroup.WebViewAct;
import com.zs.androidappfw.ui.view.viewgroup.ZoomControlsAct;

public class ViewFgm extends BaseClickFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fgm_main_view, container, false);
        initAndSetClickListener(view, new int[] {
                R.id.view_to_tag_blink,
                R.id.view_to_tag_include,
                R.id.view_to_tag_merge,
                R.id.view_to_tag_request_focus,
                R.id.view_to_tag_tag,

                R.id.view_to_progress_bar,
                R.id.view_to_abs_seek_bar,
                R.id.view_to_seek_bar,
                R.id.view_to_rating_bar,
                R.id.view_to_image_view,
                R.id.view_to_image_button,
                R.id.view_to_quick_contact_badge,
                R.id.view_to_text_view,
                R.id.view_to_checked_text_view,
                R.id.view_to_chronometer,
                R.id.view_to_text_clock,
                R.id.view_to_edit_text,
                R.id.view_to_auto_complete_text_view,
                R.id.view_to_multi_auto_complete_text_view,
                R.id.view_to_extract_edit_text,
                R.id.view_to_button,
                R.id.view_to_compound_button,
                R.id.view_to_check_box,
                R.id.view_to_radio_btn,
                R.id.view_to_switch,
                R.id.native_view_to_toggle_btn,
                R.id.view_to_view_group,
                R.id.view_to_absolute_layout,
                R.id.view_to_web_view,
                R.id.view_to_frame_layout,
                R.id.view_to_app_widget_host_view,
                R.id.view_to_calendar_view,
                R.id.view_to_date_picker,
                R.id.view_to_time_picker,
                R.id.view_to_gesture_overlay_view,
                R.id.view_to_media_controller,
                R.id.view_to_horizontal_scroll_view,
                R.id.view_to_scroll_view,
                R.id.view_to_tab_host,
                R.id.view_to_view_animator,
                R.id.view_to_view_flipper,
                R.id.view_to_view_switcher,
                R.id.view_to_image_switcher,
                R.id.view_to_text_switcher,
                R.id.view_to_grid_layout,
                R.id.view_to_linear_layout,
                R.id.view_to_action_menu_view,
                R.id.view_to_number_picker,
                R.id.view_to_radio_group,
                R.id.view_to_search_view,
                R.id.view_to_tab_widget,
                R.id.view_to_table_layout,
                R.id.view_to_table_row,
                R.id.view_to_zoom_controls,
                R.id.view_to_relative_layout,
                R.id.view_to_dialer_filter,
                R.id.view_to_two_line_list_item,
                R.id.view_to_space,
                R.id.view_to_view_stub,

                R.id.view_to_animation_frame,
                R.id.view_to_animation_tween,
                R.id.view_to_animation_attribute,
        });
        return view;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_to_tag_blink:
                startActivity(BlinkAct.class);
                break;
            case R.id.view_to_tag_include:
                startActivity(IncludeAct.class);
                break;
            case R.id.view_to_tag_merge:
                startActivity(MergeAct.class);
                break;
            case R.id.view_to_tag_request_focus:
                startActivity(RequestFocusAct.class);
                break;
            case R.id.view_to_tag_tag:
                startActivity(TagAct.class);
                break;

            case R.id.view_to_progress_bar:
                startActivity(ProgressBarAct.class);
                break;
            case R.id.view_to_abs_seek_bar:
                startActivity(AbsSeekBarAct.class);
                break;
            case R.id.view_to_rating_bar:
                startActivity(RatingBarAct.class);
                break;
            case R.id.view_to_seek_bar:
                startActivity(SeekBarAct.class);
                break;
            case R.id.view_to_image_view:
                startActivity(ImageViewAct.class);
                break;
            case R.id.view_to_image_button:
                startActivity(ImageButtonAct.class);
                break;
            case R.id.view_to_quick_contact_badge:
                startActivity(QuickContactBadgeAct.class);
                break;
            case R.id.view_to_text_view:
                startActivity(TextViewAct.class);
                break;
            case R.id.view_to_checked_text_view:
                startActivity(CheckedTextViewAct.class);
                break;
            case R.id.view_to_chronometer:
                startActivity(ChronometerAct.class);
                break;
            case R.id.view_to_text_clock:
                startActivity(TextClockAct.class);
                break;
            case R.id.view_to_edit_text:
                startActivity(EditTextAct.class);
                break;
            case R.id.view_to_auto_complete_text_view:
                startActivity(AutoCompleteTextViewAct.class);
                break;
            case R.id.view_to_multi_auto_complete_text_view:
                startActivity(MultiAutoCompleteTextViewAct.class);
                break;
            case R.id.view_to_extract_edit_text:
                startActivity(ExtractEditTextAct.class);
                break;
            case R.id.view_to_button:
                startActivity(ButtonAct.class);
                break;
            case R.id.view_to_compound_button:
                startActivity(CompoundButtonAct.class);
                break;
            case R.id.view_to_check_box:
                startActivity(CheckBoxAct.class);
                break;
            case R.id.view_to_radio_btn:
                startActivity(RadioButtonAct.class);
                break;
            case R.id.view_to_switch:
                startActivity(SwitchAct.class);
                break;
            case R.id.native_view_to_toggle_btn:
                startActivity(ToggleButtonAct.class);
                break;
            case R.id.view_to_view_group:
                startActivity(ViewGroupAct.class);
                break;
            case R.id.view_to_absolute_layout:
                startActivity(AbsoluteLayoutAct.class);
                break;
            case R.id.view_to_web_view:
                startActivity(WebViewAct.class);
                break;
            case R.id.view_to_frame_layout:
                startActivity(FrameLayoutAct.class);
                break;
            case R.id.view_to_app_widget_host_view:
                startActivity(AppWidgetHostViewAct.class);
                break;
            case R.id.view_to_calendar_view:
                startActivity(CalendarViewAct.class);
                break;
            case R.id.view_to_date_picker:
                startActivity(DatePickerAct.class);
                break;
            case R.id.view_to_time_picker:
                startActivity(TimePickerAct.class);
                break;
            case R.id.view_to_gesture_overlay_view:
                startActivity(GestureOverlayViewAct.class);
                break;
            case R.id.view_to_media_controller:
                startActivity(MediaControllerAct.class);
                break;
            case R.id.view_to_horizontal_scroll_view:
                startActivity(HorizontalScrollViewAct.class);
                break;
            case R.id.view_to_scroll_view:
                startActivity(ScrollViewAct.class);
                break;
            case R.id.view_to_tab_host:
                startActivity(TabHostAct.class);
                break;
            case R.id.view_to_view_animator:
                startActivity(ViewAnimatorAct.class);
                break;
            case R.id.view_to_view_flipper:
                startActivity(ViewFlipperAct.class);
                break;
            case R.id.view_to_view_switcher:
                startActivity(ViewSwitcherAct.class);
                break;
            case R.id.view_to_image_switcher:
                startActivity(ImageSwitcherAct.class);
                break;
            case R.id.view_to_text_switcher:
                startActivity(TextSwitcherAct.class);
                break;
            case R.id.view_to_grid_layout:
                startActivity(GridLayoutAct.class);
                break;
            case R.id.view_to_linear_layout:
                startActivity(LinearLayoutAct.class);
                break;
            case R.id.view_to_action_menu_view:
                startActivity(ActionMenuViewAct.class);
                break;
            case R.id.view_to_number_picker:
                startActivity(NumberPickerAct.class);
                break;
            case R.id.view_to_radio_group:
                startActivity(RadioButtonAct.class);
                break;
            case R.id.view_to_search_view:
                startActivity(SearchViewAct.class);
                break;
            case R.id.view_to_tab_widget:
                startActivity(TabWidgetAct.class);
                break;
            case R.id.view_to_table_layout:
                startActivity(TableLayoutAct.class);
                break;
            case R.id.view_to_table_row:
                startActivity(TableRowAct.class);
                break;
            case R.id.view_to_zoom_controls:
                startActivity(ZoomControlsAct.class);
                break;
            case R.id.view_to_relative_layout:
                startActivity(RelativeLayoutAct.class);
                break;
            case R.id.view_to_dialer_filter:
                startActivity(DialerFilterAct.class);
                break;
            case R.id.view_to_two_line_list_item:
                startActivity(TwoLineListItemAct.class);
                break;
            case R.id.view_to_space:
                startActivity(SpaceAct.class);
                break;
            case R.id.view_to_view_stub:
                startActivity(ViewStubAct.class);
                break;

            case R.id.view_to_animation_frame:
                startActivity(AnimationFrameAct.class);
                break;
            case R.id.view_to_animation_tween:
                startActivity(AnimationTweenAct.class);
                break;
            case R.id.view_to_animation_attribute:
                startActivity(AnimationAttributeAct.class);
                break;
        }
    }
}
