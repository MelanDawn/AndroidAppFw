package com.zs.androidappfw.ui.advanced;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.zs.androidappfw.base.BaseTitleActivity;

import java.io.IOException;

public class ShellAct extends BaseTitleActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getTitleResId() {
        return 0;
    }

    public void aa() throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("ps -A");
        process.getInputStream();
    }
}
