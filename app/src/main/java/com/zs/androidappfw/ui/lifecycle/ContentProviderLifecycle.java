package com.zs.androidappfw.ui.lifecycle;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.content.pm.ProviderInfo;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zs.androidappfw.base.BaseContentProvider;
import com.zs.androidappfw.storage.database.DbManager;
import com.zs.androidappfw.utils.LUtil;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ContentProviderLifecycle extends BaseContentProvider {

    public static final String AUTHORITY = "com.zs.androidappfw.lifecycle.ContentProviderLifecycle";
    public static final String CONTENT = "content://" + AUTHORITY;

    private static final int BOOK_DIR = 0;
    private static final int BOOK_ITEM = 1;
    private static final int CATEGORY_DIR = 2;
    private static final int CATEGORY_ITEM = 3;
    private static final String URI_TYPE_BOOK_DIR =
            "vnd.android.cursor.dir/vnd.com.app.androidappfw.lifecycle.book";
    private static final String URI_TYPE_BOOK_ITEM =
            "vnd.android.cursor.item/vnd.com.app.androidappfw.lifecycle.book";
    private static final String URI_TYPE_CATEGORY_DIR =
            "vnd.android.cursor.dir/vnd.com.app.androidappfw.lifecycle.category";
    private static final String URI_TYPE_CATEGORY_ITEM =
            "vnd.android.cursor.item/vnd.com.app.androidappfw.lifecycle.category";

    private static final String TABLE_BOOK = "book";
    private static final String TABLE_CATEGORY = "category";

    private static final UriMatcher uriMatcher;

    private DbManager mDbManager;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "book", BOOK_DIR);
        uriMatcher.addURI(AUTHORITY, "book/#", BOOK_ITEM);
        uriMatcher.addURI(AUTHORITY, "category", CATEGORY_DIR);
        uriMatcher.addURI(AUTHORITY, "category/#", CATEGORY_ITEM);
    }

    @Override
    public boolean onCreate() {
        LUtil.i(mTag, getContext());
        mDbManager = DbManager.getInstance(getContext());
        SQLiteDatabase db = mDbManager.openDb();
        if (db == null) {
            return true;
        }
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS 'book' (" +
                    "'id' INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "'name' TEXT NOT NULL," +
                    "'author' TEXT NOT NULL," +
                    "'pages' INTEGER," +
                    "'price' INTEGER " +
                    ")");
        } catch (SQLException e) {
            LUtil.e(mTag, e);
        }
        mDbManager.closeDb();
        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        LUtil.i(mTag, uri, 5);
        SQLiteDatabase db = mDbManager.openDb();
        if (db == null) {
            return null;
        }

        Cursor cursor = null;
        int matchResult = uriMatcher.match(uri);
        LUtil.i(mTag, "uriMatch:", matchResult);
        switch (matchResult) {
            case BOOK_DIR:
                cursor = db.query(TABLE_BOOK, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                cursor = db.query(TABLE_BOOK, projection, "id = ?",
                        new String[]{ bookId }, null, null, sortOrder);
                break;
            case CATEGORY_DIR:
                cursor = db.query(TABLE_CATEGORY, projection, selection,
                        selectionArgs, null, null, sortOrder);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                cursor = db.query(TABLE_CATEGORY, projection, "id = ?",
                        new String[]{ categoryId }, null, null, sortOrder);
                break;
            default:
                break;

        }
        mDbManager.closeDb();
        return cursor;
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        LUtil.i(mTag, uri);
        SQLiteDatabase db = mDbManager.openDb();
        if (db == null) {
            return null;
        }

        Uri uriReturn = null;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
            case BOOK_ITEM:
                long newBookId = db.insert(TABLE_BOOK, null, values);
                uriReturn = Uri.parse(CONTENT + "/book/" + newBookId);
                break;
            case CATEGORY_DIR:
            case CATEGORY_ITEM:
                long newCategoryId = db.insert(TABLE_CATEGORY, null, values);
                uriReturn = Uri.parse(CONTENT + "/category/" + newCategoryId);
                break;
            default:
                break;
        }
        mDbManager.closeDb();
        return uriReturn;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        LUtil.i(mTag, uri);
        SQLiteDatabase db = mDbManager.openDb();
        if (db == null) {
            return 0;
        }

        int updatedRows = 0;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                updatedRows = db.update(TABLE_BOOK, values, selection, selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                updatedRows = db.update(TABLE_BOOK, values, "id = ?", new String[] { bookId });
                break;
            case CATEGORY_DIR:
                updatedRows = db.update(TABLE_CATEGORY, values, selection, selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                updatedRows = db.update(TABLE_CATEGORY, values, "id = ?",
                        new String[] { categoryId });
                break;
            default:
                break;
        }
        mDbManager.closeDb();
        return updatedRows;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        LUtil.i(mTag);
        SQLiteDatabase db = mDbManager.openDb();
        if (db == null) {
            return 0;
        }

        int deletedRows = 0;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                deletedRows = db.delete(TABLE_BOOK, selection, selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                deletedRows = db.delete(TABLE_BOOK, "id = ?", new String[] { bookId });
                break;
            case CATEGORY_DIR:
                deletedRows = db.delete(TABLE_CATEGORY, selection, selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                deletedRows = db.delete(TABLE_CATEGORY, "id = ?", new String[] { categoryId });
                break;
            default:
                break;
        }
        mDbManager.closeDb();
        return deletedRows;
    }

    @Override
    public String getType(@NonNull Uri uri) {
        LUtil.i(mTag);
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                return URI_TYPE_BOOK_DIR;
            case BOOK_ITEM:
                return URI_TYPE_BOOK_ITEM;
            case CATEGORY_DIR:
                return URI_TYPE_CATEGORY_DIR;
            case CATEGORY_ITEM:
                return URI_TYPE_CATEGORY_ITEM;
        }
        return null;
    }

    @Override
    public void onCallingPackageChanged() {
        super.onCallingPackageChanged();
        LUtil.i(mTag);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LUtil.i(mTag);
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

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder,
                        @Nullable CancellationSignal cancellationSignal) {
        LUtil.i(mTag, uri, 6);
        return super.query(uri, projection, selection, selectionArgs, sortOrder, cancellationSignal);
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable Bundle queryArgs,
                        @Nullable CancellationSignal cancellationSignal) {
        LUtil.i(mTag, uri, 4);
        return super.query(uri, projection, queryArgs, cancellationSignal);
    }

    @Nullable
    @Override
    public Uri canonicalize(@NonNull Uri url) {
        LUtil.i(mTag);
        return super.canonicalize(url);
    }

    @Nullable
    @Override
    public Uri uncanonicalize(@NonNull Uri url) {
        LUtil.i(mTag);
        return super.uncanonicalize(url);
    }

    @Override
    public boolean refresh(Uri uri, @Nullable Bundle extras, @Nullable CancellationSignal cancellationSignal) {
        LUtil.i(mTag);
        return super.refresh(uri, extras, cancellationSignal);
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values, @Nullable Bundle extras) {
        LUtil.i(mTag);
        return super.insert(uri, values, extras);
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        LUtil.i(mTag);
        return super.bulkInsert(uri, values);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable Bundle extras) {
        LUtil.i(mTag);
        return super.delete(uri, extras);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable Bundle extras) {
        LUtil.i(mTag);
        return super.update(uri, values, extras);
    }

    @Nullable
    @Override
    public ParcelFileDescriptor openFile(@NonNull Uri uri, @NonNull String mode) throws FileNotFoundException {
        LUtil.i(mTag);
        return super.openFile(uri, mode);
    }

    @Nullable
    @Override
    public ParcelFileDescriptor openFile(@NonNull Uri uri, @NonNull String mode,
                                         @Nullable CancellationSignal signal) throws FileNotFoundException {
        LUtil.i(mTag);
        return super.openFile(uri, mode, signal);
    }

    @Nullable
    @Override
    public AssetFileDescriptor openAssetFile(@NonNull Uri uri, @NonNull String mode) throws FileNotFoundException {
        LUtil.i(mTag);
        return super.openAssetFile(uri, mode);
    }

    @Nullable
    @Override
    public AssetFileDescriptor openAssetFile(@NonNull Uri uri, @NonNull String mode,
                                             @Nullable CancellationSignal signal) throws FileNotFoundException {
        LUtil.i(mTag);
        return super.openAssetFile(uri, mode, signal);
    }

    @Nullable
    @Override
    public String[] getStreamTypes(@NonNull Uri uri, @NonNull String mimeTypeFilter) {
        LUtil.i(mTag);
        return super.getStreamTypes(uri, mimeTypeFilter);
    }

    @Nullable
    @Override
    public AssetFileDescriptor openTypedAssetFile(@NonNull Uri uri, @NonNull String mimeTypeFilter,
                                                  @Nullable Bundle opts) throws FileNotFoundException {
        LUtil.i(mTag);
        return super.openTypedAssetFile(uri, mimeTypeFilter, opts);
    }

    @Nullable
    @Override
    public AssetFileDescriptor openTypedAssetFile(@NonNull Uri uri, @NonNull String mimeTypeFilter,
                                                  @Nullable Bundle opts, @Nullable CancellationSignal signal) throws FileNotFoundException {
        LUtil.i(mTag);
        return super.openTypedAssetFile(uri, mimeTypeFilter, opts, signal);
    }

    @NonNull
    @Override
    public <T> ParcelFileDescriptor openPipeHelper(@NonNull Uri uri, @NonNull String mimeType,
                                                   @Nullable Bundle opts, @Nullable T args, @NonNull PipeDataWriter<T> func) throws FileNotFoundException {
        LUtil.i(mTag);
        return super.openPipeHelper(uri, mimeType, opts, args, func);
    }

    @Override
    protected boolean isTemporary() {
        LUtil.i(mTag);
        return super.isTemporary();
    }

    @Override
    public void attachInfo(Context context, ProviderInfo info) {
        LUtil.i(mTag);
        super.attachInfo(context, info);
    }

    @NonNull
    @Override
    public ContentProviderResult[] applyBatch(@NonNull String authority,
                                              @NonNull ArrayList<ContentProviderOperation> operations) throws OperationApplicationException {
        LUtil.i(mTag);
        return super.applyBatch(authority, operations);
    }

    @NonNull
    @Override
    public ContentProviderResult[] applyBatch(@NonNull ArrayList<ContentProviderOperation> operations) throws OperationApplicationException {
        LUtil.i(mTag);
        return super.applyBatch(operations);
    }

    @Nullable
    @Override
    public Bundle call(@NonNull String authority, @NonNull String method, @Nullable String arg, @Nullable Bundle extras) {
        LUtil.i(mTag);
        return super.call(authority, method, arg, extras);
    }

    @Nullable
    @Override
    public Bundle call(@NonNull String method, @Nullable String arg, @Nullable Bundle extras) {
        LUtil.i(mTag);
        return super.call(method, arg, extras);
    }

    @Override
    public void shutdown() {
        LUtil.i(mTag);
        super.shutdown();
    }

    @Override
    public void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        LUtil.i(mTag);
        super.dump(fd, writer, args);
    }
}
