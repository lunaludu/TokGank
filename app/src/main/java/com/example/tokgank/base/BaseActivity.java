package com.example.tokgank.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity{

    protected abstract int provideContentViewId();
    public void initView(){};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(provideContentViewId());
        ButterKnife.bind(this);

        initView();
    }
//
//    public void hideActionBar(){
//        if (getSupportActionBar()!=null){
//            getSupportActionBar().hide();
//        }
//    }
}
