package com.example.tokgank.module.category;


import android.util.Log;

import com.example.tokgank.config.Config;
import com.example.tokgank.model.GankoEntity;
import com.example.tokgank.net.NetWork;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Hugh on 2018/4/27.
 */

public class CategoryPresenter implements CategoryContract.ICategoryPresenter{


    CategoryContract.ICategoryView iCategoryView;
    private Subscription subscription;
    private int mPage;

    public CategoryPresenter(CategoryContract.ICategoryView iCategoryView) {
        this.iCategoryView = iCategoryView;
    }

    @Override
    public void subscribe() {
        getCategoryItem(true);
        Log.d("HomeActivity","Loading..");
    }

    @Override
    public void onError() {
        if(subscription != null&&subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }

    @Override
    public void getCategoryItem(final boolean isRefresh) {

        mPage = (isRefresh == true)?1 : mPage++;

        subscription = NetWork.getInstance().getSerre()
                .getTopMovie(iCategoryView.getCategoryName(), Config.CATEGORY_COUNT,mPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GankoEntity>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GankoEntity gankoEntity) {
                        if (isRefresh){
                            iCategoryView.getCategoryItem(gankoEntity);
//                            iCategoryView.hideSwipe();
                        }else {
                            iCategoryView.addCategoryItem(gankoEntity);
                        }
                    }
                });
    }

}
