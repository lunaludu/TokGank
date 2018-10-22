package com.example.tokgank.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tokgank.R;
import com.example.tokgank.model.GankoEntity;
import com.example.tokgank.widget.MyApplication;
import java.util.List;

/**
 * Created by Hugh on 2018/5/30.
 */

public class GankoRecyclerAdapter extends BaseQuickAdapter<GankoEntity.ResultsBean,BaseViewHolder>{

    public GankoRecyclerAdapter(@LayoutRes int layoutResId, @Nullable List<GankoEntity.ResultsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GankoEntity.ResultsBean item) {

        helper.setText(R.id.news_desc,item.getDesc())
                .addOnClickListener(R.id.news_type)
                .setText(R.id.news_author,item.getWho())
                .setText(R.id.news_type,item.getType());
        helper.setImageResource(R.id.image_view,R.drawable.asfile);


            String quality = "";
            if (item.getImages() != null && item.getImages().size() > 0) {


                Glide.with(MyApplication.getContext())
                        .load(item.getImages().get(0)+quality)
                        .skipMemoryCache(true)
                        .placeholder(R.mipmap.ic_more_gray1)
                        .error(R.drawable.asfile)
                        .crossFade()
                        .override(140,140)
                        .into((ImageView) helper.getView(R.id.image_view));
            } else { // 列表不显示图片
                Glide.with(MyApplication.getContext())
                        .load(R.drawable.asfile)
                        .skipMemoryCache(true)
                        .into((ImageView) helper.getView(R.id.image_view));
            }
    }



}
