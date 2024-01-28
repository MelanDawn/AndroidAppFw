package com.zs.androidappfw.base;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.SharedElementCallback;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.loader.app.LoaderManager;

import com.zs.androidappfw.utils.LUtil;

import java.io.FileDescriptor;
import java.io.PrintWriter;

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

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        LUtil.i(TAG);
        return super.getLifecycle();
    }

    @NonNull
    @Override
    public LifecycleOwner getViewLifecycleOwner() {
        LUtil.i(TAG);
        return super.getViewLifecycleOwner();
    }

    @NonNull
    @Override
    public LiveData<LifecycleOwner> getViewLifecycleOwnerLiveData() {
        LUtil.i(TAG);
        return super.getViewLifecycleOwnerLiveData();
    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        LUtil.i(TAG);
        return super.getViewModelStore();
    }

    @NonNull
    @Override
    public ViewModelProvider.Factory getDefaultViewModelProviderFactory() {
        LUtil.i(TAG);
        return super.getDefaultViewModelProviderFactory();
    }

    @NonNull
    @Override
    public String toString() {
        LUtil.i(TAG);
        return super.toString();
    }

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        LUtil.i(TAG);
    }

    @Override
    public void setInitialSavedState(@Nullable SavedState state) {
        super.setInitialSavedState(state);
        LUtil.i(TAG);
    }

    @Override
    public void setTargetFragment(@Nullable Fragment fragment, int requestCode) {
        super.setTargetFragment(fragment, requestCode);
        LUtil.i(TAG);
    }

    @Nullable
    @Override
    public Context getContext() {
        LUtil.i(TAG);
        return super.getContext();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        LUtil.i(TAG);
    }

    @Override
    public void setRetainInstance(boolean retain) {
        super.setRetainInstance(retain);
        LUtil.i(TAG);
    }

    @Override
    public void setHasOptionsMenu(boolean hasMenu) {
        super.setHasOptionsMenu(hasMenu);
        LUtil.i(TAG);
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        LUtil.i(TAG);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LUtil.i(TAG);
    }

    @Override
    public boolean getUserVisibleHint() {
        LUtil.i(TAG);
        return super.getUserVisibleHint();
    }

    @NonNull
    @Override
    public LoaderManager getLoaderManager() {
        LUtil.i(TAG);
        return super.getLoaderManager();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        LUtil.i(TAG);
    }

    @Override
    public void startActivity(Intent intent, @Nullable Bundle options) {
        super.startActivity(intent, options);
        LUtil.i(TAG);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        LUtil.i(TAG);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        LUtil.i(TAG);
    }

    @Override
    public void startIntentSenderForResult(IntentSender intent, int requestCode, @Nullable Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, @Nullable Bundle options) throws IntentSender.SendIntentException {
        super.startIntentSenderForResult(intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags, options);
        LUtil.i(TAG);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LUtil.i(TAG);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        LUtil.i(TAG);
    }

    @Override
    public boolean shouldShowRequestPermissionRationale(@NonNull String permission) {
        LUtil.i(TAG);
        return super.shouldShowRequestPermissionRationale(permission);
    }

    @NonNull
    @Override
    public LayoutInflater onGetLayoutInflater(@Nullable Bundle savedInstanceState) {
        LUtil.i(TAG);
        return super.onGetLayoutInflater(savedInstanceState);
    }

    @SuppressLint("RestrictedApi")
    @NonNull
    @Override
    public LayoutInflater getLayoutInflater(@Nullable Bundle savedFragmentState) {
        LUtil.i(TAG);
        return super.getLayoutInflater(savedFragmentState);
    }

    @Override
    public void onInflate(@NonNull Context context, @NonNull AttributeSet attrs, @Nullable Bundle savedInstanceState) {
        super.onInflate(context, attrs, savedInstanceState);
        LUtil.i(TAG);
    }

    @Override
    public void onInflate(@NonNull Activity activity, @NonNull AttributeSet attrs, @Nullable Bundle savedInstanceState) {
        super.onInflate(activity, attrs, savedInstanceState);
        LUtil.i(TAG);
    }

    @Override
    public void onAttachFragment(@NonNull Fragment childFragment) {
        super.onAttachFragment(childFragment);
        LUtil.i(TAG);
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        LUtil.i(TAG);
    }

    @Nullable
    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        LUtil.i(TAG);
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    @Nullable
    @Override
    public Animator onCreateAnimator(int transit, boolean enter, int nextAnim) {
        LUtil.i(TAG);
        return super.onCreateAnimator(transit, enter, nextAnim);
    }

    @Nullable
    @Override
    public View getView() {
        LUtil.i(TAG);
        return super.getView();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        LUtil.i(TAG);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        LUtil.i(TAG);
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        super.onMultiWindowModeChanged(isInMultiWindowMode);
        LUtil.i(TAG);
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);
        LUtil.i(TAG);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LUtil.i(TAG);
    }

    @Override
    public void onPrimaryNavigationFragmentChanged(boolean isPrimaryNavigationFragment) {
        super.onPrimaryNavigationFragmentChanged(isPrimaryNavigationFragment);
        LUtil.i(TAG);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        LUtil.i(TAG);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        LUtil.i(TAG);
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        LUtil.i(TAG);
    }

    @Override
    public void onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu();
        LUtil.i(TAG);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        LUtil.i(TAG);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onOptionsMenuClosed(@NonNull Menu menu) {
        super.onOptionsMenuClosed(menu);
        LUtil.i(TAG);
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        LUtil.i(TAG);
    }

    @Override
    public void registerForContextMenu(@NonNull View view) {
        super.registerForContextMenu(view);
        LUtil.i(TAG);
    }

    @Override
    public void unregisterForContextMenu(@NonNull View view) {
        super.unregisterForContextMenu(view);
        LUtil.i(TAG);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        LUtil.i(TAG);
        return super.onContextItemSelected(item);
    }

    @Override
    public void setEnterSharedElementCallback(@Nullable SharedElementCallback callback) {
        super.setEnterSharedElementCallback(callback);
        LUtil.i(TAG);
    }

    @Override
    public void setExitSharedElementCallback(@Nullable SharedElementCallback callback) {
        super.setExitSharedElementCallback(callback);
        LUtil.i(TAG);
    }

    @Override
    public void setEnterTransition(@Nullable Object transition) {
        super.setEnterTransition(transition);
        LUtil.i(TAG);
    }

    @Nullable
    @Override
    public Object getEnterTransition() {
        LUtil.i(TAG);
        return super.getEnterTransition();
    }

    @Override
    public void setReturnTransition(@Nullable Object transition) {
        super.setReturnTransition(transition);
        LUtil.i(TAG);
    }

    @Nullable
    @Override
    public Object getReturnTransition() {
        LUtil.i(TAG);
        return super.getReturnTransition();
    }

    @Override
    public void setExitTransition(@Nullable Object transition) {
        super.setExitTransition(transition);
        LUtil.i(TAG);
    }

    @Nullable
    @Override
    public Object getExitTransition() {
        LUtil.i(TAG);
        return super.getExitTransition();
    }

    @Override
    public void setReenterTransition(@Nullable Object transition) {
        super.setReenterTransition(transition);
        LUtil.i(TAG);
    }

    @Nullable
    @Override
    public Object getReenterTransition() {
        LUtil.i(TAG);
        return super.getReenterTransition();
    }

    @Override
    public void setSharedElementEnterTransition(@Nullable Object transition) {
        super.setSharedElementEnterTransition(transition);
        LUtil.i(TAG);
    }

    @Nullable
    @Override
    public Object getSharedElementEnterTransition() {
        LUtil.i(TAG);
        return super.getSharedElementEnterTransition();
    }

    @Override
    public void setSharedElementReturnTransition(@Nullable Object transition) {
        super.setSharedElementReturnTransition(transition);
        LUtil.i(TAG);
    }

    @Nullable
    @Override
    public Object getSharedElementReturnTransition() {
        LUtil.i(TAG);
        return super.getSharedElementReturnTransition();
    }

    @Override
    public void setAllowEnterTransitionOverlap(boolean allow) {
        super.setAllowEnterTransitionOverlap(allow);
        LUtil.i(TAG);
    }

    @Override
    public boolean getAllowEnterTransitionOverlap() {
        LUtil.i(TAG);
        return super.getAllowEnterTransitionOverlap();
    }

    @Override
    public void setAllowReturnTransitionOverlap(boolean allow) {
        super.setAllowReturnTransitionOverlap(allow);
        LUtil.i(TAG);
    }

    @Override
    public boolean getAllowReturnTransitionOverlap() {
        LUtil.i(TAG);
        return super.getAllowReturnTransitionOverlap();
    }

    @Override
    public void postponeEnterTransition() {
        super.postponeEnterTransition();
        LUtil.i(TAG);
    }

    @Override
    public void startPostponedEnterTransition() {
        super.startPostponedEnterTransition();
        LUtil.i(TAG);
    }

    @Override
    public void dump(@NonNull String prefix, @Nullable FileDescriptor fd, @NonNull PrintWriter writer, @Nullable String[] args) {
        super.dump(prefix, fd, writer, args);
        LUtil.i(TAG);
    }
}
