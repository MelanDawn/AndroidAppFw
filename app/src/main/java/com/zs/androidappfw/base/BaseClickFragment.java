package com.zs.androidappfw.base;

import android.view.View;

import androidx.annotation.NonNull;

public abstract class BaseClickFragment extends BaseFragment implements View.OnClickListener {

    protected void initAndSetClickListener(@NonNull View view, @NonNull int[] resIds) {
        for (int resId : resIds) view.findViewById(resId).setOnClickListener(this);
    }
}

