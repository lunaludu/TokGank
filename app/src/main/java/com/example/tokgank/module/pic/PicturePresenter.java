package com.example.tokgank.module.pic;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.tokgank.model.Fuli;
import com.example.tokgank.net.NetWork;

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
        this.mIPictureView = mIPictureView;
        this.mContext = context;
    }

    @Override
    public void subscribe() {
        getPictureItem(true);
    }

    @Override
    public void onError() {
        if(subscription != null&&subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }


    @Override
    public void getPiceture() {

    }

    @Override
    public void getPictureItem(final boolean isRefresh) {
//        if (isRefresh){
//            mPage=1;
//        }else{
//            mPage++;
//        }
        mPage = (isRefresh == true)?1 : mPage++;

        subscription = NetWork.getInstance().getSerre()
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
