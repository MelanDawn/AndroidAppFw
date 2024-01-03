package com.zs.androidappfw.device;

// Created by zhangs on 2018/8/6.

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.zs.androidappfw.App;
import com.zs.androidappfw.utils.LUtil;

public class NetworkState {

    private static final String TAG = "NetworkState";

    private NetworkInfo mNetworkInfo = null;

    private static NetworkState sInstance = new NetworkState();

    private final BroadcastReceiver mNetworkReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
                NetworkInfo lastNetworkInfo = mNetworkInfo;
                updateNetworkStatus(context);

                //TODO 通过EventBus来解决监听问题
                //回调监听者的方法, 若监听者太多, 会有性能问题
                if (isNetworkAvailable() && !isNetworkAvailable(lastNetworkInfo)) {
                    //todo
                }

                if (isWifiAvailable()
                        && !isNetworkAvailable(lastNetworkInfo, ConnectivityManager.TYPE_WIFI)) {
                    //todo
                }
            }
        }
    };

    private void registerBroadcastReceiver(Context context){
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(mNetworkReceiver, filter);
    }

    private NetworkState(){
        LUtil.i(TAG, "init");

        registerBroadcastReceiver(App.appContext);
        updateNetworkStatus(App.appContext);
    }

    public static NetworkState getInstance(){
        return sInstance;
    }

    private void updateNetworkStatus(Context context){
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null){
            synchronized (NetworkState.this) {
                mNetworkInfo = cm.getActiveNetworkInfo();
            }
        } else {
            LUtil.e(TAG, "could not get ConnectivityManager, network state maybe wrong");
        }
    }

    public boolean isNetworkAvailable(){
        synchronized (NetworkState.this){
            return (mNetworkInfo != null) && mNetworkInfo.isConnected();
        }
    }

    public boolean isNetworkAvailable(int type) {
        synchronized (NetworkState.this){
            return (mNetworkInfo != null) && mNetworkInfo.isConnected()
                    && type == mNetworkInfo.getType();
        }
    }

    private boolean isNetworkAvailable(NetworkInfo networkInfo) {
        return (networkInfo != null) && networkInfo.isConnected();
    }

    private boolean isNetworkAvailable(NetworkInfo networkInfo, int type) {
        return (networkInfo != null) && networkInfo.isConnected()
                && ConnectivityManager.TYPE_WIFI == type;
    }

    public boolean isWifiAvailable() {
        synchronized (NetworkState.this){
            return (mNetworkInfo != null) && mNetworkInfo.isConnected()
                    && ConnectivityManager.TYPE_WIFI == mNetworkInfo.getType();
        }
    }
}
