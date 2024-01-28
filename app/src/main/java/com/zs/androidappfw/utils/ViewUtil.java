package com.zs.androidappfw.utils;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zs.androidappfw.base.BaseActivity;

public class ViewUtil {

    public static TextView createStartTv(BaseActivity activity, ViewGroup viewGroup) {
        TextView textView = createBaseTv(activity, viewGroup);

        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.START);
        return textView;
    }

    private static TextView createBaseTv(BaseActivity activity, ViewGroup viewGroup) {
        TextView textView = new TextView(activity);
        viewGroup.addView(textView);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(layoutParams);
        textView.setPadding(50, 0, 0, 0);
        textView.setAllCaps(false);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(14);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
        return textView;
    }

    public static void addDivider(BaseActivity activity, LinearLayout linearLayout, int color) {
        View groupDivider = new View(activity);
        groupDivider.setMinimumHeight(5);
        groupDivider.setMinimumWidth(2048);
        groupDivider.setBackgroundColor(color);
        linearLayout.addView(groupDivider);
    }
}
