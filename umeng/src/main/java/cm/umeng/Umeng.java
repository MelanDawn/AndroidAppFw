package cm.umeng;

// Created by zhangs on 2018/10/26.

import android.content.Context;
import android.util.Log;

import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

public class Umeng {

    static Context appContext;

    public static void init(Context context) {
        appContext = context;
        // debug mode
        UMConfigure.setLogEnabled(true);
        // init umeng. ref: http://message.umeng.com/app/5bd28840b465f5782c000033/management/information
        UMConfigure.init(context, UMConfigure.DEVICE_TYPE_PHONE, "83dbc63583c9e8e62a3e6d943222f884");

        //MobclickAgent.setSecret();
        MobclickAgent.setCatchUncaughtExceptions(true);
        MobclickAgent.setScenarioType(context, MobclickAgent.EScenarioType.E_UM_NORMAL);
        //
        //MobclickAgent.setSessionContinueMillis(30);

       // 注册 Umeng push
        PushAgent pushAgent = PushAgent.getInstance(appContext);
        pushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                Log.i("Umeng", "device token = " + deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                Log.d("Umeng", "device token s = " + s + " s1 = " + s1);
            }
        });
    }
}
