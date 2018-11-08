package com.example.tokgank.adapter;


import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tokgank.R;
import com.example.tokgank.model.Fuli;
import com.example.tokgank.widget.MyApplication;

import java.util.List;

/**
 * Created by Hugh on 2018/5/31.
 */

public class FuliRecyclerAdapter extends BaseQuickAdapter<Fuli.ResultsBean,BaseViewHolder> {

    public FuliRecyclerAdapter(@LayoutRes int layoutResId, @Nullable List<Fuli.ResultsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Fuli.ResultsBean item) {

        Glide.with(MyApplication.getContext())
                .load(item.getUrl())
                .skipMemoryCache(true)
                .error(R.drawable.asfile2)
                .crossFade()
                .into((ImageView) helper.getView(R.id.fuli_image));
    }
}
