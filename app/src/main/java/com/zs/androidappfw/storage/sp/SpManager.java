package com.zs.androidappfw.storage.sp;

// Created by zhangs on 2018/11/28.

import android.content.Context;
import android.content.SharedPreferences;

import com.zs.androidappfw.AppLifecycle;
import com.zs.androidappfw.config.Config;

import java.util.HashSet;
import java.util.Set;

public class SpManager {

    public static SharedPreferences getSp() {
        return AppLifecycle.appContext.getSharedPreferences(Config.SP_DEFAULT, Context.MODE_PRIVATE);
    }

    public static SharedPreferences.Editor getEditor() {
        return getSp().edit();
    }

    public static void saveBoolean(String key, boolean value) {
        getEditor().putBoolean(key, value).apply();
    }

    public static void saveString(String key, String value) {
        getEditor().putString(key, value).apply();
    }

    public static void saveStringSet(String key, Set<String> value) {
        getEditor().putStringSet(key, value).apply();
    }

    public static Set<String> getStringSet(String key) {
        // Ref: https://stackoverflow.com/questions/14034803/misbehavior-when-trying-to-store-a-string-set-using-sharedpreferences
        /*
         * Note that you must not modify the set instance returned by this call.
         * The consistency of the stored data is not guaranteed if you do, nor is your ability to modify the instance at all.
         * https://developer.android.google.cn/reference/android/content/SharedPreferences#getString(java.lang.String,%20java.lang.String)
         * 由于不能直接修改 getStringSet() 返回的实例，因此将内容添加到新的Set中，对新的set实例的内容进行修改
         * */
        return new HashSet<String>(getSp().getStringSet(key, new HashSet<String>()));
    }

    public static boolean saveImmediately(String key, String value) {
        return getEditor().putString(key, value).commit();
    }
}
