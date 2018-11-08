package com.example.tokgank.module.flash;


import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tokgank.HomeActivity;
import com.example.tokgank.R;
import com.example.tokgank.base.BaseActivity;
import com.example.tokgank.config.Config;

import butterknife.BindView;

/**
 * Created by Hugh on 2018/4/30.
 */

public class FlashActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.flash_imageView)
    ImageView fImageView;
    @BindView(R.id.flash_imageView_t)
    ImageView dImageView;
    @BindView(R.id.flash_textview)
    TextView fTextView;


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_flash;
    }

    @Override
    public void initView() {
        initViews();
    }



    private void initViews(){
        Glide.with(this)
                .load(Config.FLASH_URL)
//                .placeholder(R.drawable.asfile)
//                .error(R.drawable.asfile)
                .into(fImageView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toHomeActivity();
                finish();
            }
        },2000);
    }


    private void toHomeActivity(){
        Intent intent=new Intent(this,HomeActivity.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View view) {
        toHomeActivity();
    }
}