package com.zs.androidappfw.ui.activity.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import android.view.View;
import android.view.ViewStub;

import com.zs.androidappfw.R;
import com.zs.androidappfw.ui.base.BaseActivity;

public class ViewStubAct extends BaseActivity {

    ViewStub viewStub;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_view_stub);

        viewStub = findViewById(R.id.view_stub_vs);
    }

    public void inflateViewStub(View view) {
        viewStub.setVisibility(View.VISIBLE);
    }
}
