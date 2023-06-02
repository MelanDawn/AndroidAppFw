package com.zs.androidappfw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.Nullable;

import com.zs.androidappfw.config.Config;
import com.zs.androidappfw.ui.MainActivity;
import com.zs.androidappfw.base.BaseActivity;
import com.zs.androidappfw.utils.LUtil;

import java.lang.ref.WeakReference;

/**
 * Created by shoes on 2017/10/25.
 *
 */

public class SplashActivity extends BaseActivity {

    private static final String TAG = "SplashActivity";

    private static final int MSG_SPLASH = Config.HANDLER_BASE_SPLASH;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new BetterHandler(SplashActivity.this).sendEmptyMessageDelayed(MSG_SPLASH, 1000);

        LUtil.v(TAG, "create successfully");
    }

    private static class BetterHandler extends Handler{

        private WeakReference<Activity> activityWeakReference;

        BetterHandler(Activity activity){
            activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Activity activity = activityWeakReference.get();
            if (activity != null) {
                if (msg.what == MSG_SPLASH){
                    activity.startActivity(new Intent(activity, MainActivity.class));
                    activity.finish();
                }
            }
        }
    }
}
