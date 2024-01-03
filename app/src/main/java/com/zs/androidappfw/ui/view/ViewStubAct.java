package com.zs.androidappfw.ui.view;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;

public class ViewStubAct extends BaseTitleActivity {

    ViewStub viewStub;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_view_stub);

        viewStub = findViewById(R.id.view_stub_vs);
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_view_view_stub;
    }

    public void inflateViewStub(View view) {
        viewStub.setVisibility(View.VISIBLE);
    }
}
