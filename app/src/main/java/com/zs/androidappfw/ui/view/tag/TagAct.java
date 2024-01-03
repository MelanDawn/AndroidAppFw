package com.zs.androidappfw.ui.view.tag;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;
import com.zs.androidappfw.utils.LUtil;

public class TagAct extends BaseTitleActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_tag_tag);

        TextView tv = findViewById(R.id.tag_tag_tv);
        LUtil.d(mTag, tv.getTag().toString());
        LUtil.d(mTag, tv.getTag(R.id.tag_tag_1).toString());
        LUtil.d(mTag, tv.getTag(R.id.tag_tag_2).toString());
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_tag_tag;
    }
}
