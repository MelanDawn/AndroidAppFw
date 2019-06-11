package com.zs.androidappfw.ui.base;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

public abstract class BaseFragment extends Fragment {

    protected final String TAG = this.getClass().getSimpleName();

    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    public String getTAG() {
        return TAG;
    }

    protected void startActivity(Class<? extends BaseActivity> cls) {
        startActivity(new Intent(getActivity(), cls));
    }
}

