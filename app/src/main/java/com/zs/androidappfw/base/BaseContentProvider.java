package com.zs.androidappfw.base;

import android.content.ContentProvider;

public abstract class BaseContentProvider extends ContentProvider {
    protected final String mTag = this.getClass().getSimpleName();
}
