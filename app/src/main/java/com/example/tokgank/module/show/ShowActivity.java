package com.example.tokgank.module.show;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.tokgank.R;
import com.example.tokgank.base.BaseActivity;
import com.example.tokgank.utils.PermisionUtils;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;

/**
 * Created by Hugh on 2018/5/23.
 */

public class ShowActivity extends BaseActivity {


    Context mContext;

    @BindView(R.id.show_photo)
    PhotoView mPhotoView;
    @BindView(R.id.web_caidan)
    Button mButton;
    @BindView(R.id.web_title)
    Button mButton1;

    @Override
    protected int provideContentViewId() {
        return R.layout.show_pic;
    }

    @Override
    public void initView() {
        mContext = getApplicationContext();
        getSupportActionBar().hide();



        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        final Intent intent = getIntent();
        final String url = intent.getStringExtra("URL");




        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        PermisionUtils.verifyStoragePermissions(ShowActivity.this);
                        Bitmap bitmap = null;

                        try{
                            bitmap = Glide.with(mContext)
                                    .load(url)
                                    .asBitmap()
                                    .into(1000,1000)
                                    .get();
                            if (bitmap != null){
                                saveImage(mContext,bitmap);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                }).start();

                Toast.makeText(ShowActivity.this,"图片下载成功",Toast.LENGTH_LONG);

            }
        });






        Glide.with(this).load(url).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                return false;
            }
        }).diskCacheStrategy(DiskCacheStrategy.ALL).into(mPhotoView);

        mPhotoView.setOnPhotoTapListener(new OnPhotoTapListener() {
            @Override
            public void onPhotoTap(ImageView view, float x, float y) {
                finish();
            }
        });

    }

    private void saveImage(Context context,Bitmap bitmap){
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsoluteFile();
//        String fileName="FIlEE";
        File appDir = new File(file.getPath(),"/IMAGE");
        if (!appDir.exists()){
            appDir.mkdirs();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File currentFile = new File(appDir,fileName + ".jpg");
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(currentFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }finally {
            try{
                fos.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }


    }


}
