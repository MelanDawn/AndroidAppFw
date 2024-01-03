package com.zs.androidappfw.ui.view.tag;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;
import com.zs.androidappfw.utils.LUtil;

public class RequestFocusAct extends BaseTitleActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_tag_request_focus);

        LUtil.d(mTag, "LinearLayout获取了焦点？ ： " + findViewById(R.id.tag_request_focus_ll).isFocusable());
        LUtil.d(mTag, "TextView获取了焦点？ ： " + findViewById(R.id.tag_request_focus_tv).isFocusable());
        LUtil.d(mTag, "第一个EditText获取了焦点？ ： " + findViewById(R.id.tag_request_focus_et_1).isFocusable());
        LUtil.d(mTag, "第二个EditText获取了焦点？ ： " + findViewById(R.id.tag_request_focus_et_2).isFocusable());
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_tag_request_focus;
    }
}
