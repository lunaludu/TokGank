package com.example.tokgank.ui;


import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.example.tokgank.R;
import com.example.tokgank.base.BaseFragment;
import com.example.tokgank.module.web.SearchActivity;

import butterknife.BindView;

public class VedioFragment extends BaseFragment {

    @BindView(R.id.button_query)
    Button mButton;
//    @BindView(R.id.web_vedios)
    WebView mWebView;

    @Override
    protected boolean getUserVis() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_discover;
    }

    @Override
    protected void onVisible() {

    }

    @Override
    protected void init() {
       mButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(getContext(), SearchActivity.class);
               startActivity(intent);
           }
       });
    }
}
