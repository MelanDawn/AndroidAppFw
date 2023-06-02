package com.zs.androidappfw.base;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    protected final String TAG = this.getClass().getSimpleName();

    protected Context mContext;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    public String getTAG() {
        return TAG;
    }

    protected void startActivity(Class<? extends BaseActivity> cls) {
        startActivity(new Intent(getActivity(), cls));
    }

    protected void startFragmentActivity(Class<? extends BaseFragmentActivity> cls) {
        startActivity(new Intent(getActivity(), cls));
    }
}

