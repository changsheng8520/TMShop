package com.wcs.tmshop.feature.category;

import android.view.View;
import android.widget.TextView;

import com.wcs.tmshop.R;
import com.wcs.tmshop.base.BaseListAdapter;
import com.wcs.tmshop.network.entity.CategoryPrimary;

import butterknife.BindView;

/**
 * 类描述：一级分类的适配器
 * 创建时间：2017-3-1 11:36
 */

public class CategoryAdapter extends BaseListAdapter<CategoryPrimary,CategoryAdapter.ViewHolder> {

    @Override
    protected int getItemViewLayout() {
        return R.layout.item_primary_category;
    }

    @Override
    protected ViewHolder getItemViewHolder(View view) {
        return new ViewHolder(view);
    }


    class ViewHolder extends BaseListAdapter.ViewHolder{
        @BindView(R.id.text_category)
        TextView textCategory;

        public ViewHolder(View itemView) {
            super(itemView);
        }


        @Override
        protected void bind(int position) {
            textCategory.setText(getItem(position).getmName());
        }
    }
}
