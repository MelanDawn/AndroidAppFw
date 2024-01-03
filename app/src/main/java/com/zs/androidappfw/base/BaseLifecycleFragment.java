package com.zs.androidappfw.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zs.androidappfw.utils.LUtil;

public class BaseLifecycleFragment extends BaseFragment {

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
        LUtil.i(TAG);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LUtil.i(TAG);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        LUtil.i(TAG);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LUtil.i(TAG);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LUtil.i(TAG);
    }

    @Override
    public void onStart() {
        super.onStart();
        LUtil.i(TAG);
    }

    @Override
    public void onResume() {
        super.onResume();
        LUtil.i(TAG);
    }

    @Override
    public void onPause() {
        super.onPause();
        LUtil.i(TAG);
    }

    @Override
    public void onStop() {
        super.onStop();
        LUtil.i(TAG);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LUtil.i(TAG);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LUtil.i(TAG);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LUtil.i(TAG);
    }
}
