package com.example.tokgank.module.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.tokgank.R;
import com.example.tokgank.base.BaseActivity;

import butterknife.BindView;

public class SearchActivity extends BaseActivity{
    @BindView(R.id.web_vedios)
    WebView mWebView;



    @Override
    protected int provideContentViewId() {
        return R.layout.web_vedio;
    }

    @Override
    public void initView() {
        getSupportActionBar().hide();
//        super.initView();
        mWebView.loadUrl("http://www.baidu.com");
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
    }

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
}
