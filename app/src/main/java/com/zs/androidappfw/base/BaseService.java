package com.zs.androidappfw.base;

import android.app.Service;

public abstract class BaseService extends Service {
    protected final String mTag = this.getClass().getSimpleName();
}
