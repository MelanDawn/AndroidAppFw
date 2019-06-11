package com.zs.androidappfw.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zs.androidappfw.utils.LUtil;

public abstract class BaseFragment extends Fragment {

    protected final String TAG = this.getClass().getSimpleName();

    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
        LUtil.d(TAG, "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LUtil.d(TAG, "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LUtil.d(TAG, "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LUtil.d(TAG, "onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LUtil.d(TAG, "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        LUtil.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LUtil.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LUtil.d(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        LUtil.d(TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LUtil.d(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LUtil.d(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LUtil.d(TAG, "onDetach");
    }

    public String getTAG() {
        return TAG;
    }
}

