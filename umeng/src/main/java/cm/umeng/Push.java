package cm.umeng;

// Created by zhangs on 2018/10/29.

import com.umeng.message.PushAgent;

public class Push {

    public static void onAppStart() {
        PushAgent.getInstance(Umeng.appContext).onAppStart();
    }
}
