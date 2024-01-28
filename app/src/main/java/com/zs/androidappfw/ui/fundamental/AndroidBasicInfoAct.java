package com.zs.androidappfw.ui.fundamental;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;

public class AndroidBasicInfoAct extends BaseTitleActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_android_basic_info);
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_android_basic_info;
    }

    public void toBuild(View view) {
        startActivity(new Intent(this, BuildAct.class));
    }

    public void toDebug(View view) {
        startActivity(new Intent(this, DebugAct.class));
    }

    public void toEnvironment(View view) {
        startActivity(new Intent(this, EnvironmentAct.class));
    }

    public void toFileSystem(View view) {
        startActivity(new Intent(this, ContextFileSystemAct.class));
    }

    public void toLocaleList(View view) {
        startActivity(new Intent(this, LocaleListAct.class));
    }

    public void toProcess(View view) {
        startActivity(new Intent(this, ProcessAct.class));
    }

    public void toUserHandle(View view) {
        startActivity(new Intent(this, UserHandleAct.class));
    }
}
