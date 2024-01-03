package com.zs.androidappfw.ui.lifecycle;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;
import com.zs.androidappfw.utils.LUtil;

public class ContentProviderLifecycleAct extends BaseTitleActivity {

    private String mNewId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_content_provider_lifecycle);
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_content_provider_lifecycle;
    }

    public void addToBook(View view) {
        Uri uri = Uri.parse(ContentProviderLifecycle.CONTENT + "/book");
        ContentValues values = new ContentValues();
        values.put("name", "A Clash of Kings");
        values.put("author", "George Martin");
        values.put("pages", 1040);
        values.put("price", 22);
        Uri newUri = getContentResolver().insert(uri, values);
        mNewId = newUri.getPathSegments().get(1);
    }

    public void queryFromBook(View view) {
        Uri uri = Uri.parse(ContentProviderLifecycle.CONTENT + "/book");
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                int price = cursor.getInt(cursor.getColumnIndex("price"));
                LUtil.d(mTag, "book name is " + name);
                LUtil.d(mTag, "book author is " + author);
                LUtil.d(mTag, "book pages is " + pages);
                LUtil.d(mTag, "book price is " + price);

            }
            cursor.close();
        }
    }

    public void updateBook(View view) {
        Uri uri = Uri.parse(ContentProviderLifecycle.CONTENT + "/book/" + mNewId);
        ContentValues values = new ContentValues();
        values.put("name", "A Storm of Swords");
        values.put("pages", 1216);
        values.put("price", 24);
        getContentResolver().update(uri, values, null, null);
    }

    public void deleteFromBook(View view) {
        Uri uri = Uri.parse(ContentProviderLifecycle.CONTENT + "/book/" + mNewId);
        getContentResolver().delete(uri, null, null);
    }
}
