package com.zs.androidappfw.ui.view.tag;

import android.os.Bundle;

import androidx.annotation.Nullable;
import android.widget.TextView;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseActivity;
import com.zs.androidappfw.utils.LUtil;

public class TagAct extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_tag_tag);

        TextView tv = findViewById(R.id.tag_tag_tv);
        LUtil.d(TAG, tv.getTag().toString());
        LUtil.d(TAG, tv.getTag(R.id.tag_tag_1).toString());
        LUtil.d(TAG, tv.getTag(R.id.tag_tag_2).toString());
    }
}
