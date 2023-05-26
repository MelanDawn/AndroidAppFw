package com.zs.androidappfw.ui.activity.view.tag;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.ui.base.BaseActivity;
import com.zs.androidappfw.utils.LUtil;

public class RequestFocusAct extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_tag_request_focus);

        LUtil.d(TAG, "LinearLayout获取了焦点？ ： " + findViewById(R.id.tag_request_focus_ll).isFocusable());
        LUtil.d(TAG, "TextView获取了焦点？ ： " + findViewById(R.id.tag_request_focus_tv).isFocusable());
        LUtil.d(TAG, "第一个EditText获取了焦点？ ： " + findViewById(R.id.tag_request_focus_et_1).isFocusable());
        LUtil.d(TAG, "第二个EditText获取了焦点？ ： " + findViewById(R.id.tag_request_focus_et_2).isFocusable());
    }
}
