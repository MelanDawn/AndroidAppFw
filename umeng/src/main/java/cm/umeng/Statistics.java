package cm.umeng;

// Created by zhangs on 2018/10/29.

import android.content.Context;

import com.umeng.analytics.MobclickAgent;

import java.util.Map;

public class Statistics {

    /* count event start */
    public static void onEvent(String eventId) {
        MobclickAgent.onEvent(Umeng.appContext, eventId);
    }

    public static void onEvent(String eventId, String tag) {
        MobclickAgent.onEvent(Umeng.appContext, eventId, tag);
    }

    public static void onEvent(String eventId, Map<String, String> fileds) {
        MobclickAgent.onEvent(Umeng.appContext, eventId, fileds);
    }
    /* count event end */

    /* calculation event start */
    public static void onEventValue(String eventId, Map<String, String> fileds, int du) {
        MobclickAgent.onEventValue(Umeng.appContext, eventId, fileds, du);
    }
    /* calculation event start */

    /*
     * 1、确保在所有的Activity中都调用 MobclickAgent.onResume() 和onPause()方法，
     * 这两个调用不会阻塞应用程序的主线程，也不会影响应用程序的性能。
     *
     * 2、注意：如果您的Activity之间有继承或者控制关系，请不要同时在父和子Activity中重复添加nPause和nResume方法，
     * 否则会造成重复统计，导致启动次数异常增高。(例如：使用TabHost、TabActivity、ActivityGroup时)。
     *
     * 3、当应用在后台运行超过30秒（默认）再回到前台，将被认为是两次独立的Session(启动)，
     * 例如：用户回到home，或进入其他程序，经过一段时间后再返回之前的应用。即被认为是两个独立的Session。
     * 30秒的限制可以通过 MobclickAgent.setSessionContinueMillis(40 * 000); 修改限制
     * */
    public static void onResume(Context context) {
        MobclickAgent.onResume(context);
    }

    public static void onPause(Context context) {
        MobclickAgent.onPause(context);
    }

    // 手动统计
    /*
    MobclickAgent.onPageStart(String viewName);
    MobclickAgent.onPageEnd(String viewName);
    https://developer.umeng.com/docs/66632/detail/66889#h2-u9875u9762u7EDFu8BA19
    */
    public  static void onPageStart(String page) {
        MobclickAgent.onPageStart(page);
    }

    public static void onPageEnd(String page) {
        MobclickAgent.onPageEnd(page);
    }
}
