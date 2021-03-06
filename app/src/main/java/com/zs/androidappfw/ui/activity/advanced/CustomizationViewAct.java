package com.zs.androidappfw.ui.activity.advanced;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;

import com.zs.androidappfw.R;
import com.zs.androidappfw.ui.base.BaseActivity;
import com.zs.androidappfw.ui.widgets.CustomizedView;
import com.zs.androidappfw.ui.widgets.MusicSwitchView;

import java.lang.ref.WeakReference;

public class CustomizationViewAct extends BaseActivity {

    private ViewHandler mHandler = new ViewHandler(new WeakReference<>(CustomizationViewAct.this));

    private MusicSwitchView musicSwitchView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_customization_view);

        musicSwitchView = findViewById(R.id.customization_view_msv);
        musicSwitchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicSwitchView.getState() == MusicSwitchView.STATE_STARTED) {
                    musicSwitchView.pause();
                } else if (musicSwitchView.getState() == MusicSwitchView.STATE_PAUSED){
                    if (musicSwitchView.start()) {
                        mHandler.sendMessageDelayed(mHandler.obtainMessage(ViewHandler.MSG_UPDATE_PROGRESS, 10 , 0), 1000 );
                    }
                }
            }
        });
        findViewById(R.id.customization_view_request_layout_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicSwitchView != null) musicSwitchView.requestLayout();
            }
        });
        findViewById(R.id.customization_view_invalidate_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicSwitchView != null) musicSwitchView.invalidate();
            }
        });
        findViewById(R.id.customization_view_post_invalidate_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (musicSwitchView != null) musicSwitchView.postInvalidate();
                    }
                }).start();
            }
        });

        final CustomizedView customizedView = findViewById(R.id.customization_view_cv);
        findViewById(R.id.customization_view_no_attr_request_layout_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customizedView != null) customizedView.requestLayout();
            }
        });
        findViewById(R.id.customization_view_no_attr_invalidate_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customizedView != null) customizedView.invalidate();
            }
        });
        findViewById(R.id.customization_view_no_attr_post_invalidate_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (customizedView != null) customizedView.postInvalidate();
                    }
                }).start();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    static class ViewHandler extends Handler {

        static final int MSG_UPDATE_PROGRESS = 1;

        private int mProgress = 0;

        private WeakReference<CustomizationViewAct> mReference;

        ViewHandler(WeakReference<CustomizationViewAct> reference) {
            mReference = reference;
        }

        @Override
        public void handleMessage(Message msg) {
            CustomizationViewAct activity = mReference.get();
            if (activity != null) {
                switch (msg.what) {
                    case MSG_UPDATE_PROGRESS:
                        if (activity.musicSwitchView.getState() == MusicSwitchView.STATE_STARTED) {
                            mProgress++;
                            activity.musicSwitchView.setProgress(msg.arg1 * mProgress);
                            sendMessageDelayed(obtainMessage(MSG_UPDATE_PROGRESS, 10, 0), 1000);
                        }
                        break;
                    case 2:
                        break;
                }
            }
        }
    }
}
