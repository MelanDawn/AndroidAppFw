package com.zs.androidappfw.ui.view.viewgroup;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ClientCertRequest;
import android.webkit.HttpAuthHandler;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.SafeBrowsingResponse;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;
import com.zs.androidappfw.utils.LUtil;


// Created by zhangs on 2019/3/13.

public class WebViewAct extends BaseTitleActivity {

    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_web_view);

        webView = findViewById(R.id.web_view_demo_1);
        // 采用当前WebView加载网页，否则调起第三方应用浏览器等
        webView.setWebViewClient(new MyWebViewClient());
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

    static class MyWebViewClient extends WebViewClient {
        private static final String TAG = "MyWebViewClient";
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            LUtil.i(TAG, request.getUrl());
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            LUtil.i(TAG, url);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            LUtil.i(TAG, url);
            super.onPageFinished(view, url);
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            LUtil.i(TAG, url);
            super.onLoadResource(view, url);
        }

        @Override
        public void onPageCommitVisible(WebView view, String url) {
            LUtil.i(TAG, url);
            super.onPageCommitVisible(view, url);
        }

        @Nullable
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            LUtil.i(TAG, url);
            return super.shouldInterceptRequest(view, url);
        }

        @Nullable
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            LUtil.i(TAG, request.getUrl());
            return super.shouldInterceptRequest(view, request);
        }

        @Override
        public void onTooManyRedirects(WebView view, Message cancelMsg, Message continueMsg) {
            LUtil.i(TAG, cancelMsg.what, continueMsg.what);
            super.onTooManyRedirects(view, cancelMsg, continueMsg);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            LUtil.i(TAG, errorCode, description, failingUrl);
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            LUtil.i(TAG, request.getUrl());
            super.onReceivedError(view, request, error);
        }

        @Override
        public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
            LUtil.i(TAG, request.getUrl());
            super.onReceivedHttpError(view, request, errorResponse);
        }

        @Override
        public void onFormResubmission(WebView view, Message dontResend, Message resend) {
            LUtil.i(TAG, dontResend.what, resend.what);
            super.onFormResubmission(view, dontResend, resend);
        }

        @Override
        public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
            LUtil.i(TAG, url);
            super.doUpdateVisitedHistory(view, url, isReload);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            LUtil.i(TAG);
            super.onReceivedSslError(view, handler, error);
        }

        @Override
        public void onReceivedClientCertRequest(WebView view, ClientCertRequest request) {
            LUtil.i(TAG, request.getHost());
            super.onReceivedClientCertRequest(view, request);
        }

        @Override
        public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
            LUtil.i(TAG, host, realm);
            super.onReceivedHttpAuthRequest(view, handler, host, realm);
        }

        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            LUtil.i(TAG, event.getKeyCode());
            return super.shouldOverrideKeyEvent(view, event);
        }

        @Override
        public void onUnhandledKeyEvent(WebView view, KeyEvent event) {
            super.onUnhandledKeyEvent(view, event);
            LUtil.i(TAG, event.getKeyCode());
        }

        @Override
        public void onScaleChanged(WebView view, float oldScale, float newScale) {
            super.onScaleChanged(view, oldScale, newScale);
            LUtil.i(TAG, oldScale, newScale);
        }

        @Override
        public void onReceivedLoginRequest(WebView view, String realm, @Nullable String account, String args) {
            super.onReceivedLoginRequest(view, realm, account, args);
            LUtil.i(TAG, account, args);
        }

        @Override
        public boolean onRenderProcessGone(WebView view, RenderProcessGoneDetail detail) {
            LUtil.i(TAG, detail.toString());
            return super.onRenderProcessGone(view, detail);
        }

        @Override
        public void onSafeBrowsingHit(WebView view, WebResourceRequest request, int threatType, SafeBrowsingResponse callback) {
            super.onSafeBrowsingHit(view, request, threatType, callback);
            LUtil.i(TAG, request.getUrl(), threatType, callback.toString());
        }
    }
}
