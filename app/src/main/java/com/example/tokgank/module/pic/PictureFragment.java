package com.example.tokgank.module.pic;


import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.tokgank.R;
import com.example.tokgank.adapter.FuliRecyclerAdapter;
import com.example.tokgank.base.BaseFragment;
import com.example.tokgank.model.Fuli;
import com.example.tokgank.module.show.ShowActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Hugh on 2018/4/27.
 */

public class PictureFragment extends BaseFragment implements PictureContract.IPictureView{

    @BindView(R.id.reecView)
    RecyclerView mRecyclerView;
    PictureContract.IPicturePresenter mIPicturePresenter;
    SwipeRefreshLayout swipeRefreshLayout;
    int mCurrentCounter;
    boolean isErr = true;
    boolean isssLoad = true;
    int mPage = 1;

    private FuliRecyclerAdapter mFuliAdapter;
    List<Fuli.ResultsBean> mResultsBeen = new ArrayList<>();


    @Override
    protected boolean getUserVis() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_picture;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && isssLoad){
            Log.d("HomeActivity","onds");
            isssLoad = false;
            initPicData();
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    protected void init() {

        mIPicturePresenter = new PicturePresenter(this,getContext());

        mIPicturePresenter.subscribe();
//        onVisible();
//        initPicData();

    }

    @Override
    protected void onVisible() {
//        if (!getPrepared()){
//        mIPicturePresenter=new PicturePresenter(this);
//        mIPicturePresenter.subscribe();
//        }
    }

    @Override
    public void getPictureItem(Fuli fuli) {
            mResultsBeen = fuli.getResults();
//            initPicData();
    }

    @Override
    public void addPictureItem(Fuli fuli) {
//        mResultsBeens.addAll(fuli.getResults());
        mResultsBeen.addAll(fuli.getResults());

    }


    @Override
    public void showSwipe() {
        swipeRefreshLayout.setRefreshing(true);
    }


    @Override
    public void hideSwipe() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public String getPictureName() {
        return null;
    }

    public void initPicData(){

        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        mFuliAdapter = new FuliRecyclerAdapter(R.layout.fuli_item,mResultsBeen);

        mFuliAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String url = mResultsBeen.get(position).getUrl();
                Intent intent = new Intent(getActivity(), ShowActivity.class);
                intent.putExtra("URL",url);
                getActivity().startActivity(intent);
            }
        });

        mFuliAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

                mRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (mCurrentCounter>=mResultsBeen.size()){
                            mFuliAdapter.loadMoreEnd();
                            Log.d("HomeActivity","LOAD.end");
                        }else {
                            if (isErr){
                                mPage++;
                                mIPicturePresenter.getPictureItem(false);
                                mCurrentCounter = mFuliAdapter.getData().size();
                                mFuliAdapter.loadMoreComplete();

                                Log.d("HomeActivity","LOAD.complete");
                            }else {
                                isErr = true;
//                                Toast.makeText(getActivity(),R.string.network_err,Toast.LENGTH_LONG).show();
                                mFuliAdapter.loadMoreFail();
                                Log.d("HomeActivity","LOAD.fail");
                            }
                        }
                    }
                },1000);

            }
        },mRecyclerView);

        mRecyclerView.setAdapter(mFuliAdapter);
    }


}
