package com.zs.androidappfw.ui.view.viewgroup;

import android.os.Bundle;

import androidx.annotation.Nullable;
import android.widget.TabHost;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseActivity;
import com.zs.androidappfw.utils.LUtil;


// Created by zhangs on 2019/2/28.

public class TabWidgetAct extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_tab_widget);

        test();
    }

    private void test() {
        TabHost tabHost = findViewById(R.id.tab_host);
        /* Call setup() before adding tabs if loading TabHost using findViewById()
         * However: You do not need to call setup() after getTabHost() in TabActivity
         * 通过findViewById()加载的TabHost在做其他操作之前应立即调用setup().
         * 如果是在TabActivity中调用getTabHost()加载的，不需要调用setup()
         * */
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("one").setIndicator("水平图").setContent(R.id.tab_widget_demo_1_content_1));
        tabHost.addTab(tabHost.newTabSpec("two").setIndicator("竖直图").setContent(R.id.tab_widget_demo_1_content_2));

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                // 这个字符串的值时TabSpec的tag，这两个例子中是 one、two中的一个。
                LUtil.d(TAG, "onTabChanged param = " + s);
            }
        });
    }
}
