package com.example.tokgank.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Hugh on 2018/5/30.
 */




public abstract class BaseFragment extends Fragment {


    private Unbinder unbinder;
    protected abstract int getLayoutId();
    protected abstract void init();
//    protected abstract void loadData();
    protected abstract void onVisible();
    protected abstract boolean getUserVis();
//    protected abstract boolean getPrepared();

    public boolean isVisible;

    public boolean isPrepared = false;



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVis()){
            isVisible = true;
            onVisible();
        }else {
            isVisible = false;
            onInVisible();
        }
    }

    protected void onInVisible(){

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayoutId() != 0){
            return inflater.inflate(getLayoutId(),container,false);
//            return null;
        }else {return super.onCreateView(inflater,container,savedInstanceState);}

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this,view);
        init();
        isPrepared = true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

   protected boolean getPrepared(){
       return isPrepared;
   }
}