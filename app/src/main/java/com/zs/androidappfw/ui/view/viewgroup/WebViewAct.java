package com.zs.androidappfw.ui.view.viewgroup;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;


// Created by zhangs on 2019/3/13.

public class WebViewAct extends BaseTitleActivity {

    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_web_view);

        webView = findViewById(R.id.web_view_demo_1);
        // 采用当前WebView加载网页，否则调起第三方应用浏览器等
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.qq.com/");
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_vg_web_view;
    }

    public void prePage(View view) {
        if (webView.canGoBack()) {
            webView.goBack();
        }
    }

    public void nextPage(View view) {
        if (webView.canGoForward()) {
            webView.goForward();
        }
    }

    public void homePage(View view) {
        webView.loadUrl("https://www.qq.com/");
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}
