package com.wcs.tmshop.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 类描述：通用Activity的基类
 * 创建时间：2017-3-1 23:35
 */

public abstract class BaseActivity extends TransitionActivity {

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewLayout());
        unbinder = ButterKnife.bind(this);

        initView();
    }

    protected abstract void initView();

    @LayoutRes protected abstract int getContentViewLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        unbinder = null;
    }
}
