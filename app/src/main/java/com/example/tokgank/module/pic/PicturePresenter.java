package com.example.tokgank.module.pic;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.tokgank.config.Config;
import com.example.tokgank.model.Fuli;
import com.example.tokgank.net.NetWork;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Hugh on 2018/5/22.
 */

public class PicturePresenter implements PictureContract.IPicturePresenter{
    Bitmap mBitmap;

    private Context mContext;
    PictureContract.IPictureView mIPictureView;
    private Subscription subscription;
    private int mPage;


    public PicturePresenter(PictureContract.IPictureView mIPictureView,Context context) {
        this.mIPictureView=mIPictureView;
        this.mContext=context;
    }

    @Override
    public void subscribe() {
        getPictureItem(true);
    }

    @Override
    public void onError() {
        if(subscription!=null&&subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }


    @Override
    public void getPiceture() {

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try{
//                    String url="http://www.guolin.tech/book.png";
//                    FutureTarget<Bitmap> futureTarget=Glide.with(mContext)
//                            .load(url)
//                            .asBitmap()
//                            .into(20,20);
//                    mBitmap=futureTarget.get();
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        }).start();

    }

    @Override
    public void getPictureItem(final boolean isRefresh) {
        if (isRefresh){
            mPage=1;
        }else{
            mPage++;
        }

        subscription= NetWork.getInstance().getSerre()
                .getFuli("福利",20,mPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Fuli>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Fuli fuli) {
                        if (isRefresh){
                            mIPictureView.getPictureItem(fuli);
                            mIPictureView.hideSwipe();
                        }else {
                            mIPictureView.addPictureItem(fuli);
                            Log.d("HomeActivity","LOADMORE PIC");
                        }
                    }
                });
    }
}
