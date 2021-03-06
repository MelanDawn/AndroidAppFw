package com.zs.androidappfw.ui.activity.view.tag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.zs.androidappfw.R;
import com.zs.androidappfw.ui.base.BaseActivity;
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
