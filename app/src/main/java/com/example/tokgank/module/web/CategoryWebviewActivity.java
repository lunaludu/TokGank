package com.example.tokgank.module.web;


import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.example.tokgank.R;
import com.example.tokgank.base.BaseActivity;

import butterknife.BindView;

public class CategoryWebviewActivity extends BaseActivity {

    public static final String TITLE="DSS";
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.web_desc)
    TextView textView;
    @BindView(R.id.web_title)
    Button button;


    @Override
    protected int provideContentViewId() {
        return R.layout.web_desc;
    }


    @Override
    public void initView() {

        getSupportActionBar().hide();
        String url = getIntent().getStringExtra("url");
        String desc = getIntent().getStringExtra("desc");

        textView.setText(desc);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Log.d("CategoryWebviewActivity","Back...");
            }
        });

        webView.loadUrl(url);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Log.d("HomeActivity","404");
            }
        });
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK&&webView.canGoBack()){
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }
}
