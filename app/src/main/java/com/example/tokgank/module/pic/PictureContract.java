package com.example.tokgank.module.pic;


import com.example.tokgank.base.BasePresenter;
import com.example.tokgank.base.BaseView;
import com.example.tokgank.model.Fuli;

/**
 * Created by Hugh on 2018/5/22.
 */

public class PictureContract {
    interface IPictureView extends BaseView {
       void getPictureItem(Fuli fuli);
        void addPictureItem(Fuli fuli);
        void showSwipe();
        void hideSwipe();
        String getPictureName();
    }

    interface IPicturePresenter extends BasePresenter {
        void getPictureItem(boolean isRefresh);
        void getPiceture();
    }
}
