package com.wcs.tmshop.feature.home;

import com.wcs.tmshop.R;
import com.wcs.tmshop.base.BaseFragment;
import com.wcs.tmshop.base.widgets.BannerAdapter;
import com.wcs.tmshop.base.widgets.BannerLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 类描述：主页fragment
 * 创建时间：2017-3-3 11:09
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.layout_banner)
    BannerLayout layoutBanner;

    public static HomeFragment newInstance(){
        return new HomeFragment();
    }

    @Override
    protected void initView() {
        BannerAdapter<Object> bannerAdapter = new BannerAdapter<Object>() {
            @Override
            protected void bind(ViewHolder holder, Object data) {
                holder.imageView.setImageResource(R.drawable.image_holder_banner);
            }
        };

        layoutBanner.setAdapter(bannerAdapter);

        //添加模拟的数据
        List<Object> data = new ArrayList<>();
        data.add(new Object());
        data.add(new Object());
        data.add(new Object());
        data.add(new Object());
        data.add(new Object());
        bannerAdapter.reset(data);
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.fragment_home;
    }

}
