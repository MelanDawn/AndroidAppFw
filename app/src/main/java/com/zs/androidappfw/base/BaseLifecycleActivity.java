package com.zs.androidappfw.base;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.app.Dialog;
import android.app.DirectAction;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.LoaderManager;
import android.app.PendingIntent;
import android.app.PictureInPictureParams;
import android.app.PictureInPictureUiState;
import android.app.SharedElementCallback;
import android.app.TaskStackBuilder;
import android.app.VoiceInteractor;
import android.app.assist.AssistContent;
import android.content.ComponentCallbacks;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.LocusId;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.PersistableBundle;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.DragAndDropPermissions;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toolbar;
import android.window.OnBackInvokedDispatcher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.ui.lifecycle.ActivitySingleInstanceAct;
import com.zs.androidappfw.ui.lifecycle.ActivitySingleTaskAct;
import com.zs.androidappfw.ui.lifecycle.ActivitySingleTaskAffinityAct;
import com.zs.androidappfw.ui.lifecycle.ActivitySingleTopAct;
import com.zs.androidappfw.ui.lifecycle.ActivityStandardAct;
import com.zs.androidappfw.ui.lifecycle.ActivityStandardConfigChangesAct;
import com.zs.androidappfw.utils.LUtil;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;
import java.util.function.Consumer;

public abstract class BaseLifecycleActivity extends BaseTitleActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_activity_lifecycle);
        LUtil.i(mTag);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LUtil.i(mTag);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LUtil.i(mTag);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LUtil.i(mTag);
    }

    @Override
    protected void onStop() {
        super.onStop();
        LUtil.i(mTag);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LUtil.i(mTag);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LUtil.i(mTag);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        LUtil.i(mTag, outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        LUtil.i(mTag, savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LUtil.i(mTag, newConfig);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LUtil.i(mTag, intent.toString());
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        LUtil.i(mTag);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        LUtil.i(mTag);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LUtil.i(mTag, "requestCode=", requestCode, "resultCode=", resultCode);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
        LUtil.i(mTag);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        LUtil.i(mTag);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        LUtil.i(mTag);
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        LUtil.i(mTag);
    }

    @Override
    protected void onApplyThemeResource(Resources.Theme theme, int resid, boolean first) {
        super.onApplyThemeResource(theme, resid, first);
        LUtil.i(mTag);
    }

    @Override
    protected void onChildTitleChanged(Activity childActivity, CharSequence title) {
        super.onChildTitleChanged(childActivity, title);
        LUtil.i(mTag);
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        LUtil.i(mTag);
    }

    @Nullable
    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        LUtil.i(mTag);
        return super.onWindowStartingActionMode(callback);
    }

    @Nullable
    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int type) {
        LUtil.i(mTag);
        return super.onWindowStartingActionMode(callback, type);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        LUtil.i(mTag);
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode, Configuration newConfig) {
        super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig);
        LUtil.i(mTag);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        LUtil.i(mTag);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
        LUtil.i(mTag);
        return super.onKeyMultiple(keyCode, repeatCount, event);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        LUtil.i(mTag);
        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    public boolean onKeyShortcut(int keyCode, KeyEvent event) {
        LUtil.i(mTag);
        return super.onKeyShortcut(keyCode, event);
    }

    @Override
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> data, Menu menu, int deviceId) {
        LUtil.i(mTag);
        super.onProvideKeyboardShortcuts(data, menu, deviceId);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        LUtil.i(mTag);
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public Intent getIntent() {
        LUtil.i(mTag);
        return super.getIntent();
    }

    @Override
    public void setIntent(Intent newIntent) {
        super.setIntent(newIntent);
        LUtil.i(mTag);
    }

    @Override
    public void setLocusContext(@Nullable LocusId locusId, @Nullable Bundle bundle) {
        super.setLocusContext(locusId, bundle);
        LUtil.i(mTag);
    }

    @Override
    public WindowManager getWindowManager() {
        LUtil.i(mTag);
        return super.getWindowManager();
    }

    @Override
    public Window getWindow() {
        LUtil.i(mTag);
        return super.getWindow();
    }

    @Override
    public LoaderManager getLoaderManager() {
        LUtil.i(mTag);
        return super.getLoaderManager();
    }

    @Nullable
    @Override
    public View getCurrentFocus() {
        LUtil.i(mTag);
        return super.getCurrentFocus();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        LUtil.i(mTag);
    }

    @Override
    public void registerActivityLifecycleCallbacks(@NonNull Application.ActivityLifecycleCallbacks callback) {
        super.registerActivityLifecycleCallbacks(callback);
        LUtil.i(mTag);
    }

    @Override
    public void unregisterActivityLifecycleCallbacks(@NonNull Application.ActivityLifecycleCallbacks callback) {
        super.unregisterActivityLifecycleCallbacks(callback);
        LUtil.i(mTag);
    }

    @Override
    public void registerComponentCallbacks(ComponentCallbacks callback) {
        super.registerComponentCallbacks(callback);
        LUtil.i(mTag);
    }

    @Override
    public void unregisterComponentCallbacks(ComponentCallbacks callback) {
        super.unregisterComponentCallbacks(callback);
        LUtil.i(mTag);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        LUtil.i(mTag);
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        LUtil.i(mTag);
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        LUtil.i(mTag);
    }

    @Override
    public void onStateNotSaved() {
        super.onStateNotSaved();
        LUtil.i(mTag);
    }

    @Override
    public void onTopResumedActivityChanged(boolean isTopResumedActivity) {
        super.onTopResumedActivityChanged(isTopResumedActivity);
        LUtil.i(mTag);
    }

    @Override
    public boolean isVoiceInteraction() {
        LUtil.i(mTag);
        return super.isVoiceInteraction();
    }

    @Override
    public boolean isVoiceInteractionRoot() {
        LUtil.i(mTag);
        return super.isVoiceInteractionRoot();
    }

    @Override
    public VoiceInteractor getVoiceInteractor() {
        LUtil.i(mTag);
        return super.getVoiceInteractor();
    }

    @Override
    public boolean isLocalVoiceInteractionSupported() {
        LUtil.i(mTag);
        return super.isLocalVoiceInteractionSupported();
    }

    @Override
    public void startLocalVoiceInteraction(Bundle privateOptions) {
        super.startLocalVoiceInteraction(privateOptions);
        LUtil.i(mTag);
    }

    @Override
    public void onLocalVoiceInteractionStarted() {
        super.onLocalVoiceInteractionStarted();
        LUtil.i(mTag);
    }

    @Override
    public void onLocalVoiceInteractionStopped() {
        super.onLocalVoiceInteractionStopped();
        LUtil.i(mTag);
    }

    @Override
    public void stopLocalVoiceInteraction() {
        super.stopLocalVoiceInteraction();
        LUtil.i(mTag);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        LUtil.i(mTag);
    }

    @Override
    public boolean onCreateThumbnail(Bitmap outBitmap, Canvas canvas) {
        LUtil.i(mTag);
        return super.onCreateThumbnail(outBitmap, canvas);
    }

    @Nullable
    @Override
    public CharSequence onCreateDescription() {
        LUtil.i(mTag);
        return super.onCreateDescription();
    }

    @Override
    public void onProvideAssistData(Bundle data) {
        super.onProvideAssistData(data);
        LUtil.i(mTag);
    }

    @Override
    public void onProvideAssistContent(AssistContent outContent) {
        super.onProvideAssistContent(outContent);
        LUtil.i(mTag);
    }

    @Override
    public void onGetDirectActions(@NonNull CancellationSignal cancellationSignal, @NonNull Consumer<List<DirectAction>> callback) {
        super.onGetDirectActions(cancellationSignal, callback);
        LUtil.i(mTag);
    }

    @Override
    public void onPerformDirectAction(@NonNull String actionId, @NonNull Bundle arguments, @NonNull CancellationSignal cancellationSignal, @NonNull Consumer<Bundle> resultListener) {
        super.onPerformDirectAction(actionId, arguments, cancellationSignal, resultListener);
        LUtil.i(mTag);
    }

    @Override
    public boolean showAssist(Bundle args) {
        LUtil.i(mTag);
        return super.showAssist(args);
    }

    @Override
    public void reportFullyDrawn() {
        super.reportFullyDrawn();
        LUtil.i(mTag);
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        super.onMultiWindowModeChanged(isInMultiWindowMode);
        LUtil.i(mTag);
    }

    @Override
    public boolean isInMultiWindowMode() {
        LUtil.i(mTag);
        return super.isInMultiWindowMode();
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, Configuration newConfig) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig);
        LUtil.i(mTag);
    }

    @Override
    public void onPictureInPictureUiStateChanged(@NonNull PictureInPictureUiState pipState) {
        super.onPictureInPictureUiStateChanged(pipState);
        LUtil.i(mTag);
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);
        LUtil.i(mTag);
    }

    @Override
    public boolean isInPictureInPictureMode() {
        LUtil.i(mTag);
        return super.isInPictureInPictureMode();
    }

    @Override
    public void enterPictureInPictureMode() {
        super.enterPictureInPictureMode();
        LUtil.i(mTag);
    }

    @Override
    public boolean enterPictureInPictureMode(@NonNull PictureInPictureParams params) {
        LUtil.i(mTag);
        return super.enterPictureInPictureMode(params);
    }

    @Override
    public void setPictureInPictureParams(@NonNull PictureInPictureParams params) {
        super.setPictureInPictureParams(params);
        LUtil.i(mTag);
    }

    @Override
    public int getMaxNumPictureInPictureActions() {
        LUtil.i(mTag);
        return super.getMaxNumPictureInPictureActions();
    }

    @Override
    public boolean onPictureInPictureRequested() {
        LUtil.i(mTag);
        return super.onPictureInPictureRequested();
    }

    @Override
    public void setShouldDockBigOverlays(boolean shouldDockBigOverlays) {
        super.setShouldDockBigOverlays(shouldDockBigOverlays);
        LUtil.i(mTag);
    }

    @Override
    public boolean shouldDockBigOverlays() {
        LUtil.i(mTag);
        return super.shouldDockBigOverlays();
    }

    @Override
    public int getChangingConfigurations() {
        LUtil.i(mTag);
        return super.getChangingConfigurations();
    }

    @Nullable
    @Override
    public Object getLastNonConfigurationInstance() {
        LUtil.i(mTag);
        return super.getLastNonConfigurationInstance();
    }

    @Override
    public Object onRetainNonConfigurationInstance() {
        LUtil.i(mTag);
        return super.onRetainNonConfigurationInstance();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        LUtil.i(mTag);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        LUtil.i(mTag);
    }

    @Override
    public FragmentManager getFragmentManager() {
        LUtil.i(mTag);
        return super.getFragmentManager();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        LUtil.i(mTag);
    }

    @Override
    public void startManagingCursor(Cursor c) {
        super.startManagingCursor(c);
        LUtil.i(mTag);
    }

    @Override
    public void stopManagingCursor(Cursor c) {
        super.stopManagingCursor(c);
        LUtil.i(mTag);
    }

    @Override
    public <T extends View> T findViewById(int id) {
        LUtil.i(mTag);
        return super.findViewById(id);
    }

    @Nullable
    @Override
    public ActionBar getActionBar() {
        LUtil.i(mTag);
        return super.getActionBar();
    }

    @Override
    public void setActionBar(@Nullable Toolbar toolbar) {
        super.setActionBar(toolbar);
        LUtil.i(mTag);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        LUtil.i(mTag);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        LUtil.i(mTag);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        LUtil.i(mTag);
    }

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        super.addContentView(view, params);
        LUtil.i(mTag);
    }

    @Override
    public TransitionManager getContentTransitionManager() {
        LUtil.i(mTag);
        return super.getContentTransitionManager();
    }

    @Override
    public void setContentTransitionManager(TransitionManager tm) {
        super.setContentTransitionManager(tm);
        LUtil.i(mTag);
    }

    @Override
    public Scene getContentScene() {
        LUtil.i(mTag);
        return super.getContentScene();
    }

    @Override
    public void setFinishOnTouchOutside(boolean finish) {
        super.setFinishOnTouchOutside(finish);
        LUtil.i(mTag);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LUtil.i(mTag);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LUtil.i(mTag);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onTrackballEvent(MotionEvent event) {
        LUtil.i(mTag);
        return super.onTrackballEvent(event);
    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        LUtil.i(mTag);
        return super.onGenericMotionEvent(event);
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        LUtil.i(mTag);
    }

    @Override
    public void onWindowAttributesChanged(WindowManager.LayoutParams params) {
        super.onWindowAttributesChanged(params);
        LUtil.i(mTag);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        LUtil.i(mTag);
    }

    @Override
    public boolean hasWindowFocus() {
        LUtil.i(mTag);
        return super.hasWindowFocus();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        LUtil.i(mTag);
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean dispatchKeyShortcutEvent(KeyEvent event) {
        LUtil.i(mTag);
        return super.dispatchKeyShortcutEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LUtil.i(mTag);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean dispatchTrackballEvent(MotionEvent ev) {
        LUtil.i(mTag);
        return super.dispatchTrackballEvent(ev);
    }

    @Override
    public boolean dispatchGenericMotionEvent(MotionEvent ev) {
        LUtil.i(mTag);
        return super.dispatchGenericMotionEvent(ev);
    }

    @Override
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        LUtil.i(mTag);
        return super.dispatchPopulateAccessibilityEvent(event);
    }

    @Nullable
    @Override
    public View onCreatePanelView(int featureId) {
        LUtil.i(mTag);
        return super.onCreatePanelView(featureId);
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        LUtil.i(mTag);
        return super.onCreatePanelMenu(featureId, menu);
    }

    @Override
    public boolean onPreparePanel(int featureId, @Nullable View view, @NonNull Menu menu) {
        LUtil.i(mTag);
        return super.onPreparePanel(featureId, view, menu);
    }

    @Override
    public boolean onMenuOpened(int featureId, @NonNull Menu menu) {
        LUtil.i(mTag);
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, @NonNull MenuItem item) {
        LUtil.i(mTag);
        return super.onMenuItemSelected(featureId, item);
    }

    @Override
    public void onPanelClosed(int featureId, @NonNull Menu menu) {
        super.onPanelClosed(featureId, menu);
        LUtil.i(mTag);
    }

    @Override
    public void invalidateOptionsMenu() {
        super.invalidateOptionsMenu();
        LUtil.i(mTag);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        LUtil.i(mTag);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        LUtil.i(mTag);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        LUtil.i(mTag);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigateUp() {
        LUtil.i(mTag);
        return super.onNavigateUp();
    }

    @Override
    public boolean onNavigateUpFromChild(Activity child) {
        LUtil.i(mTag);
        return super.onNavigateUpFromChild(child);
    }

    @Override
    public void onCreateNavigateUpTaskStack(TaskStackBuilder builder) {
        super.onCreateNavigateUpTaskStack(builder);
        LUtil.i(mTag);
    }

    @Override
    public void onPrepareNavigateUpTaskStack(TaskStackBuilder builder) {
        super.onPrepareNavigateUpTaskStack(builder);
        LUtil.i(mTag);
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
        LUtil.i(mTag);
    }

    @Override
    public void openOptionsMenu() {
        super.openOptionsMenu();
        LUtil.i(mTag);
    }

    @Override
    public void closeOptionsMenu() {
        super.closeOptionsMenu();
        LUtil.i(mTag);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        LUtil.i(mTag);
    }

    @Override
    public void registerForContextMenu(View view) {
        super.registerForContextMenu(view);
        LUtil.i(mTag);
    }

    @Override
    public void unregisterForContextMenu(View view) {
        super.unregisterForContextMenu(view);
        LUtil.i(mTag);
    }

    @Override
    public void openContextMenu(View view) {
        super.openContextMenu(view);
        LUtil.i(mTag);
    }

    @Override
    public void closeContextMenu() {
        super.closeContextMenu();
        LUtil.i(mTag);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        LUtil.i(mTag);
        return super.onContextItemSelected(item);
    }

    @Override
    public void onContextMenuClosed(@NonNull Menu menu) {
        super.onContextMenuClosed(menu);
        LUtil.i(mTag);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        LUtil.i(mTag);
        return super.onCreateDialog(id);
    }

    @Nullable
    @Override
    protected Dialog onCreateDialog(int id, Bundle args) {
        LUtil.i(mTag);
        return super.onCreateDialog(id, args);
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        LUtil.i(mTag);
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog, Bundle args) {
        super.onPrepareDialog(id, dialog, args);
        LUtil.i(mTag);
    }

    @Override
    public boolean onSearchRequested(@Nullable SearchEvent searchEvent) {
        LUtil.i(mTag);
        return super.onSearchRequested(searchEvent);
    }

    @Override
    public boolean onSearchRequested() {
        LUtil.i(mTag);
        return super.onSearchRequested();
    }

    @Override
    public void startSearch(@Nullable String initialQuery, boolean selectInitialQuery, @Nullable Bundle appSearchData, boolean globalSearch) {
        super.startSearch(initialQuery, selectInitialQuery, appSearchData, globalSearch);
        LUtil.i(mTag);
    }

    @Override
    public void triggerSearch(String query, @Nullable Bundle appSearchData) {
        super.triggerSearch(query, appSearchData);
        LUtil.i(mTag);
    }

    @Override
    public void takeKeyEvents(boolean get) {
        super.takeKeyEvents(get);
        LUtil.i(mTag);
    }

    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        LUtil.i(mTag);
        return super.getLayoutInflater();
    }

    @NonNull
    @Override
    public MenuInflater getMenuInflater() {
        LUtil.i(mTag);
        return super.getMenuInflater();
    }

    @Override
    public void setTheme(int resid) {
        super.setTheme(resid);
        LUtil.i(mTag);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        LUtil.i(mTag);
    }

    @Override
    public boolean shouldShowRequestPermissionRationale(@NonNull String permission) {
        LUtil.i(mTag);
        return super.shouldShowRequestPermissionRationale(permission);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        LUtil.i(mTag);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        LUtil.i(mTag);
    }

    @Override
    public boolean isActivityTransitionRunning() {
        LUtil.i(mTag);
        return super.isActivityTransitionRunning();
    }

    @Override
    public void startIntentSenderForResult(IntentSender intent, int requestCode, @Nullable Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags) throws IntentSender.SendIntentException {
        super.startIntentSenderForResult(intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags);
        LUtil.i(mTag);
    }

    @Override
    public void startIntentSenderForResult(IntentSender intent, int requestCode, @Nullable Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, @Nullable Bundle options) throws IntentSender.SendIntentException {
        super.startIntentSenderForResult(intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags, options);
        LUtil.i(mTag);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        LUtil.i(mTag);
    }

    @Override
    public void startActivity(Intent intent, @Nullable Bundle options) {
        super.startActivity(intent, options);
        LUtil.i(mTag);
    }

    @Override
    public void startActivities(Intent[] intents) {
        super.startActivities(intents);
        LUtil.i(mTag);
    }

    @Override
    public void startActivities(Intent[] intents, @Nullable Bundle options) {
        super.startActivities(intents, options);
        LUtil.i(mTag);
    }

    @Override
    public void startIntentSender(IntentSender intent, @Nullable Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags) throws IntentSender.SendIntentException {
        super.startIntentSender(intent, fillInIntent, flagsMask, flagsValues, extraFlags);
        LUtil.i(mTag);
    }

    @Override
    public void startIntentSender(IntentSender intent, @Nullable Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, @Nullable Bundle options) throws IntentSender.SendIntentException {
        super.startIntentSender(intent, fillInIntent, flagsMask, flagsValues, extraFlags, options);
        LUtil.i(mTag);
    }

    @Override
    public boolean startActivityIfNeeded(@NonNull Intent intent, int requestCode) {
        LUtil.i(mTag);
        return super.startActivityIfNeeded(intent, requestCode);
    }

    @Override
    public boolean startActivityIfNeeded(@NonNull Intent intent, int requestCode, @Nullable Bundle options) {
        LUtil.i(mTag);
        return super.startActivityIfNeeded(intent, requestCode, options);
    }

    @Override
    public boolean startNextMatchingActivity(@NonNull Intent intent) {
        LUtil.i(mTag);
        return super.startNextMatchingActivity(intent);
    }

    @Override
    public boolean startNextMatchingActivity(@NonNull Intent intent, @Nullable Bundle options) {
        LUtil.i(mTag);
        return super.startNextMatchingActivity(intent, options);
    }

    @Override
    public void startActivityFromChild(@NonNull Activity child, Intent intent, int requestCode) {
        super.startActivityFromChild(child, intent, requestCode);
        LUtil.i(mTag);
    }

    @Override
    public void startActivityFromChild(@NonNull Activity child, Intent intent, int requestCode, @Nullable Bundle options) {
        super.startActivityFromChild(child, intent, requestCode, options);
        LUtil.i(mTag);
    }

    @Override
    public void startActivityFromFragment(@NonNull Fragment fragment, Intent intent, int requestCode) {
        super.startActivityFromFragment(fragment, intent, requestCode);
        LUtil.i(mTag);
    }

    @Override
    public void startActivityFromFragment(@NonNull Fragment fragment, Intent intent, int requestCode, @Nullable Bundle options) {
        super.startActivityFromFragment(fragment, intent, requestCode, options);
        LUtil.i(mTag);
    }

    @Override
    public void startIntentSenderFromChild(Activity child, IntentSender intent, int requestCode, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags) throws IntentSender.SendIntentException {
        super.startIntentSenderFromChild(child, intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags);
        LUtil.i(mTag);
    }

    @Override
    public void startIntentSenderFromChild(Activity child, IntentSender intent, int requestCode, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, @Nullable Bundle options) throws IntentSender.SendIntentException {
        super.startIntentSenderFromChild(child, intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags, options);
        LUtil.i(mTag);
    }

    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        super.overridePendingTransition(enterAnim, exitAnim);
        LUtil.i(mTag);

    }

    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim, int backgroundColor) {
        super.overridePendingTransition(enterAnim, exitAnim, backgroundColor);
        LUtil.i(mTag);
    }

    @Nullable
    @Override
    public Uri getReferrer() {
        LUtil.i(mTag);
        return super.getReferrer();
    }

    @Override
    public Uri onProvideReferrer() {
        LUtil.i(mTag);
        return super.onProvideReferrer();
    }

    @Nullable
    @Override
    public String getCallingPackage() {
        LUtil.i(mTag);
        return super.getCallingPackage();
    }

    @Nullable
    @Override
    public ComponentName getCallingActivity() {
        LUtil.i(mTag);
        return super.getCallingActivity();
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        LUtil.i(mTag);
    }

    @Override
    public boolean isFinishing() {
        LUtil.i(mTag);
        return super.isFinishing();
    }

    @Override
    public boolean isDestroyed() {
        LUtil.i(mTag);
        return super.isDestroyed();
    }

    @Override
    public boolean isChangingConfigurations() {
        LUtil.i(mTag);
        return super.isChangingConfigurations();
    }

    @Override
    public void recreate() {
        super.recreate();
        LUtil.i(mTag);
    }

    @Override
    public void finish() {
        super.finish();
        LUtil.i(mTag);
    }

    @Override
    public void finishAffinity() {
        super.finishAffinity();
        LUtil.i(mTag);
    }

    @Override
    public void finishFromChild(Activity child) {
        super.finishFromChild(child);
        LUtil.i(mTag);
    }

    @Override
    public void finishAfterTransition() {
        super.finishAfterTransition();
        LUtil.i(mTag);
    }

    @Override
    public void finishActivity(int requestCode) {
        super.finishActivity(requestCode);
        LUtil.i(mTag);
    }

    @Override
    public void finishActivityFromChild(@NonNull Activity child, int requestCode) {
        super.finishActivityFromChild(child, requestCode);
        LUtil.i(mTag);
    }

    @Override
    public void finishAndRemoveTask() {
        super.finishAndRemoveTask();
        LUtil.i(mTag);
    }

    @Override
    public boolean releaseInstance() {
        LUtil.i(mTag);
        return super.releaseInstance();
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        LUtil.i(mTag);
    }

    @Override
    public PendingIntent createPendingResult(int requestCode, @NonNull Intent data, int flags) {
        LUtil.i(mTag);
        return super.createPendingResult(requestCode, data, flags);
    }

    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        super.setRequestedOrientation(requestedOrientation);
        LUtil.i(mTag);
    }

    @Override
    public int getRequestedOrientation() {
        LUtil.i(mTag);
        return super.getRequestedOrientation();
    }

    @Override
    public int getTaskId() {
        LUtil.i(mTag);
        return super.getTaskId();
    }

    @Override
    public boolean isTaskRoot() {
        LUtil.i(mTag);
        return super.isTaskRoot();
    }

    @Override
    public boolean moveTaskToBack(boolean nonRoot) {
        LUtil.i(mTag);
        return super.moveTaskToBack(nonRoot);
    }

    @NonNull
    @Override
    public String getLocalClassName() {
        LUtil.i(mTag);
        return super.getLocalClassName();
    }

    @Override
    public ComponentName getComponentName() {
        LUtil.i(mTag);
        return super.getComponentName();
    }

    @Override
    public SharedPreferences getPreferences(int mode) {
        LUtil.i(mTag);
        return super.getPreferences(mode);
    }

    @Override
    public boolean isLaunchedFromBubble() {
        LUtil.i(mTag);
        return super.isLaunchedFromBubble();
    }

    @Override
    public Object getSystemService(@NonNull String name) {
        LUtil.i(mTag);
        return super.getSystemService(name);
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        LUtil.i(mTag);
    }

    @Override
    public void setTitle(int titleId) {
        super.setTitle(titleId);
        LUtil.i(mTag);
    }

    @Override
    public void setTitleColor(int textColor) {
        super.setTitleColor(textColor);
        LUtil.i(mTag);
    }

    @Override
    public void setTaskDescription(ActivityManager.TaskDescription taskDescription) {
        super.setTaskDescription(taskDescription);
        LUtil.i(mTag);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        LUtil.i(mTag);
        return super.onCreateView(name, context, attrs);
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        LUtil.i(mTag);
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    public void dump(@NonNull String prefix, @Nullable FileDescriptor fd, @NonNull PrintWriter writer, @Nullable String[] args) {
        LUtil.i(mTag);
        super.dump(prefix, fd, writer, args);
    }

    @Override
    public boolean isImmersive() {
        LUtil.i(mTag);
        return super.isImmersive();
    }

    @Override
    public boolean setTranslucent(boolean translucent) {
        LUtil.i(mTag);
        return super.setTranslucent(translucent);
    }

    @Override
    public boolean requestVisibleBehind(boolean visible) {
        LUtil.i(mTag);
        return super.requestVisibleBehind(visible);
    }

    @Override
    public void onVisibleBehindCanceled() {
        super.onVisibleBehindCanceled();
        LUtil.i(mTag);
    }

    @Override
    public void onEnterAnimationComplete() {
        super.onEnterAnimationComplete();
        LUtil.i(mTag);
    }

    @Override
    public void setImmersive(boolean i) {
        super.setImmersive(i);
        LUtil.i(mTag);
    }

    @Override
    public void setVrModeEnabled(boolean enabled, @NonNull ComponentName requestedComponent) throws PackageManager.NameNotFoundException {
        super.setVrModeEnabled(enabled, requestedComponent);
        LUtil.i(mTag);
    }

    @Nullable
    @Override
    public ActionMode startActionMode(ActionMode.Callback callback) {
        LUtil.i(mTag);
        return super.startActionMode(callback);
    }

    @Nullable
    @Override
    public ActionMode startActionMode(ActionMode.Callback callback, int type) {
        LUtil.i(mTag);
        return super.startActionMode(callback, type);
    }

    @Override
    public void onActionModeStarted(ActionMode mode) {
        super.onActionModeStarted(mode);
        LUtil.i(mTag);
    }

    @Override
    public void onActionModeFinished(ActionMode mode) {
        super.onActionModeFinished(mode);
        LUtil.i(mTag);
    }

    @Override
    public boolean shouldUpRecreateTask(Intent targetIntent) {
        LUtil.i(mTag);
        return super.shouldUpRecreateTask(targetIntent);
    }

    @Override
    public boolean navigateUpTo(Intent upIntent) {
        LUtil.i(mTag);
        return super.navigateUpTo(upIntent);
    }

    @Override
    public boolean navigateUpToFromChild(Activity child, Intent upIntent) {
        LUtil.i(mTag);
        return super.navigateUpToFromChild(child, upIntent);
    }

    @Nullable
    @Override
    public Intent getParentActivityIntent() {
        LUtil.i(mTag);
        return super.getParentActivityIntent();
    }

    @Override
    public void setEnterSharedElementCallback(SharedElementCallback callback) {
        super.setEnterSharedElementCallback(callback);
        LUtil.i(mTag);
    }

    @Override
    public void setExitSharedElementCallback(SharedElementCallback callback) {
        super.setExitSharedElementCallback(callback);
        LUtil.i(mTag);
    }

    @Override
    public void postponeEnterTransition() {
        super.postponeEnterTransition();
        LUtil.i(mTag);
    }

    @Override
    public void startPostponedEnterTransition() {
        super.startPostponedEnterTransition();
        LUtil.i(mTag);
    }

    @Override
    public DragAndDropPermissions requestDragAndDropPermissions(DragEvent event) {
        LUtil.i(mTag);
        return super.requestDragAndDropPermissions(event);
    }

    @Override
    public void startLockTask() {
        super.startLockTask();
        LUtil.i(mTag);
    }

    @Override
    public void stopLockTask() {
        super.stopLockTask();
        LUtil.i(mTag);
    }

    @Override
    public void showLockTaskEscapeMessage() {
        super.showLockTaskEscapeMessage();
        LUtil.i(mTag);
    }

    @Override
    public void setRecentsScreenshotEnabled(boolean enabled) {
        super.setRecentsScreenshotEnabled(enabled);
        LUtil.i(mTag);
    }

    @Override
    public void setShowWhenLocked(boolean showWhenLocked) {
        super.setShowWhenLocked(showWhenLocked);
        LUtil.i(mTag);
    }

    @Override
    public void setInheritShowWhenLocked(boolean inheritShowWhenLocked) {
        super.setInheritShowWhenLocked(inheritShowWhenLocked);
        LUtil.i(mTag);
    }

    @Override
    public void setTurnScreenOn(boolean turnScreenOn) {
        super.setTurnScreenOn(turnScreenOn);
        LUtil.i(mTag);
    }

    @NonNull
    @Override
    public OnBackInvokedDispatcher getOnBackInvokedDispatcher() {
        LUtil.i(mTag);
        return super.getOnBackInvokedDispatcher();
    }

    public void toActivityStandard(View v) {
        LUtil.i(mTag);
        startActivity(new Intent(this, ActivityStandardAct.class));
    }
    public void toActivitySingleTop(View v) {
        LUtil.i(mTag);
        startActivity(new Intent(this, ActivitySingleTopAct.class));
    }
    public void toActivitySingleTask(View v) {
        LUtil.i(mTag);
        startActivity(new Intent(this, ActivitySingleTaskAct.class));
    }
    public void toActivitySingleTaskAffinity(View v) {
        LUtil.i(mTag);
        startActivity(new Intent(this, ActivitySingleTaskAffinityAct.class));
    }
    public void toActivitySingleInstance(View v) {
        LUtil.i(mTag);
        startActivity(new Intent(this, ActivitySingleInstanceAct.class));
    }

    public void toActivityStandardConfigChanges(View v) {
        LUtil.i(mTag);
        startActivity(new Intent(this, ActivityStandardConfigChangesAct.class));
    }
}
