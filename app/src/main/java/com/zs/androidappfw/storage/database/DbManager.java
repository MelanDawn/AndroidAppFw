package com.zs.androidappfw.storage.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zs.androidappfw.config.Config;
import com.zs.androidappfw.utils.LUtil;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by shoes on 2017/10/28.
 *
 */

public class DbManager {
    private static final String TAG = "DbManager";

    private static final String DB_NAME = Config.DB_NAME;
    private static final int DB_VERSION = Config.DB_VERSION;

    private AtomicInteger mDbCount = new AtomicInteger(0);
    private SQLiteDatabase mDb;
    private DbHelper mDbHelper;

    private static DbManager sInstance = null;
    private DbManager(Context context){
        mDbHelper = new DbHelper(context, DB_NAME, null, DB_VERSION);
    }

    public static DbManager getInstance(Context context) {
        if (sInstance == null){
            synchronized (DbManager.class){
                if (sInstance == null){
                    sInstance = new DbManager(context);
                }
            }
        }
        return sInstance;
    }

    public synchronized SQLiteDatabase openDb(){
        if (mDbCount.incrementAndGet() == 1) {
            mDb = mDbHelper.getWritableDatabase();
        }
        return mDb;
    }

    public synchronized void closeDb(){
        if (mDbCount.decrementAndGet() == 0){
            mDb.close();
        }
    }

    private class DbHelper extends SQLiteOpenHelper {
        DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context, name, factory, version);
        }

        DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version,
                 DatabaseErrorHandler errorHandler){
            super(context, name, factory, version, errorHandler);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            LUtil.d(TAG, "DbHelper onCreate!");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            LUtil.d(TAG, "DbHelper onUpgrade!");
        }
    }
}
