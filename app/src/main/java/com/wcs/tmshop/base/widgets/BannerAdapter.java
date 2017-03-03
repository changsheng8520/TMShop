package com.wcs.tmshop.base.widgets;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wcs.tmshop.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类描述：轮播图适配器,为了更加的广泛应用，我们可以写成通用的
 * 数据是不是确定的，但是视图是确定的
 * 创建时间：2017-3-3 10:49
 */

public abstract class BannerAdapter<T> extends PagerAdapter {


    private List<T> dataSet = new ArrayList<>();

    public void reset(List<T> data){
        dataSet.clear();
        if (data != null)dataSet.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        ViewHolder viewHolder = (ViewHolder) object;
        return view == viewHolder.itemView;
    }

    //初始化视图
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_banner, container, false);
        container.addView(view);
        ViewHolder viewHolder = new ViewHolder(view);
        //绑定视图和数据
        bind(viewHolder,dataSet.get(position));
        return viewHolder;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewHolder viewHolder = (ViewHolder) object;
        container.removeView(viewHolder.itemView);
    }


    public static class ViewHolder {
        @BindView(R.id.image_banner_item)
        public ImageView imageView;

        private View itemView;

        public ViewHolder(View view) {
            this.itemView = view;
            ButterKnife.bind(this, view);
        }
    }

    protected abstract void bind(ViewHolder holder,T data);
}
