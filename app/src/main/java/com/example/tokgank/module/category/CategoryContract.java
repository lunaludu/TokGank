package com.example.tokgank.module.category;


import com.example.tokgank.base.BasePresenter;
import com.example.tokgank.base.BaseView;
import com.example.tokgank.model.GankoEntity;

/**
 * Created by Hugh on 2018/4/27.
 */

public interface CategoryContract {
    interface ICategoryView extends BaseView {
        void getCategoryItem(GankoEntity gankoEntity);
        void addCategoryItem(GankoEntity gankoEntity);
        String getCategoryName();
//        int getPage();
    }
    interface ICategoryPresenter extends BasePresenter {
        void getCategoryItem(boolean isRefresh);
    }
}
