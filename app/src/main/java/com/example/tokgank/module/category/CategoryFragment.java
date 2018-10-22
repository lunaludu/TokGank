package com.example.tokgank.module.category;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.tokgank.R;
import com.example.tokgank.adapter.GankoRecyclerAdapter;
import com.example.tokgank.base.BaseFragment;
import com.example.tokgank.model.GankoEntity;
import com.example.tokgank.module.web.WebActivity;
import com.example.tokgank.widget.RecyclerViewHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by Hugh on 2018/4/27.
 */

public class CategoryFragment extends BaseFragment implements CategoryContract.ICategoryView,SwipeRefreshLayout.OnRefreshListener{



    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    CategoryContract.ICategoryPresenter iCategoryPresenter;
    private GankoRecyclerAdapter loadMoreAdapter;
    List<GankoEntity.ResultsBean> resultsBeen=new ArrayList<>();
    int mCurrentCounter;
    boolean isErr=false;
    int mPage=1;

    private String categoryName;
    public static final String CATEGORY_NAME="com.example.hogowen.module.category.CategoryFragment.CATEGORY_NAME";



    public static CategoryFragment newInstance(String mCategoryName){
        CategoryFragment categoryFragment=new CategoryFragment();
        Bundle bundle=new Bundle();
        bundle.putString(CATEGORY_NAME,mCategoryName);
        categoryFragment.setArguments(bundle);
        return categoryFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.category_item;
    }


    @Override
    protected void onVisible() {
        if (!getPrepared()) {
            iCategoryPresenter = new CategoryPresenter(this);
            iCategoryPresenter.subscribe();
        }
    }

    @Override
    protected void init() {
        swipeRefreshLayout.setOnRefreshListener(this);
        categoryName=getArguments().getString(CATEGORY_NAME);
        onVisible();
        initdata();
    }

    @Override
    public void getCategoryItem(GankoEntity gankoEntity) {
//        第一个会回调方法，请求数据
        resultsBeen= gankoEntity.getResults();
        initdata();
    }

    @Override
    public void addCategoryItem(GankoEntity gankoEntity) {
        resultsBeen.addAll(gankoEntity.getResults());
        if(resultsBeen.size()%10!=0){
            resultsBeen.remove(resultsBeen.size()-1);
        }
    }


    @Override
    public String getCategoryName() {
        //返回列表名称
        return categoryName;
    }


    public void initdata(){
        //getView()获取view
        final LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
//        layoutManager.findLastCompletelyVisibleItemPosition();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new RecyclerViewHelper(getActivity(),LinearLayoutManager.HORIZONTAL));
        loadMoreAdapter=new GankoRecyclerAdapter(R.layout.category_item_list,resultsBeen);
        loadMoreAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(getContext(),WebActivity.class);
                intent.putExtra("url",resultsBeen.get(position).getUrl());
                intent.putExtra("desc",resultsBeen.get(position).getDesc());
                getContext().startActivity(intent);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState==recyclerView.SCROLL_STATE_IDLE&&(layoutManager.findLastVisibleItemPosition()==layoutManager.getItemCount()-1)&&!isErr){
                    isErr=true;
                }
            }

        });

//        loadMoreAdapter.setEnableLoadMore(true);
//        loadMoreAdapter.setPreLoadNumber(5);
        loadMoreAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {


                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (mCurrentCounter>=500){
                            loadMoreAdapter.loadMoreEnd();
                        }else {
                            if (isErr){
                                mPage++;
                                iCategoryPresenter.getCategoryItem(false);
                                mCurrentCounter=loadMoreAdapter.getData().size();
                                loadMoreAdapter.loadMoreComplete();
                            }else {
                                isErr=true;
                                loadMoreAdapter.loadMoreFail();
                            }
                        }
                    }
                },1000);
            }
        },recyclerView);

        recyclerView.setAdapter(loadMoreAdapter);
    }


    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(iCategoryPresenter!=null){
            iCategoryPresenter.onError();
        }
    }
}
