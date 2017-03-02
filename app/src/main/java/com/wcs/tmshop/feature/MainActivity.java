package com.wcs.tmshop.feature;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.wcs.tmshop.R;
import com.wcs.tmshop.base.BaseActivity;
import com.wcs.tmshop.base.TestFragment;
import com.wcs.tmshop.feature.category.CategoryFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements OnTabSelectListener{

    @BindView(R.id.bottom_bar)
    BottomBar bottomBar;

    private TestFragment mHomeFragment;//首页
    private CategoryFragment mCategoryFragment;//分类
    private TestFragment mCartFragment;//购物车
    private TestFragment mMineFragment;//我的

    private Fragment mCurrentFragment;


    //视图的初始化操作
    protected void initView() {
        //recover恢复因系统重启造成的FragmentManager里面的Fragment
        //可以看一下Fragmentmanager里面是不是已经有了fragment
        cecoverFragment();
        //设置导航选择监听事件
        bottomBar.setOnTabSelectListener(this);
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onTabSelected(@IdRes int tabId) {
        switch (tabId){
            case R.id.tab_home:
                Toast.makeText(this, "首页", Toast.LENGTH_SHORT).show();
                if (mHomeFragment == null){
                    mHomeFragment = TestFragment.newInstance("HomeFragment");
                }
                //切换fragment
                switchFragment(mHomeFragment);
                break;
            case R.id.tab_category:
                Toast.makeText(this, "分类", Toast.LENGTH_SHORT).show();
                if (mCategoryFragment == null){
                    mCategoryFragment = CategoryFragment.newInstance();
                }
                switchFragment(mCategoryFragment);
                break;
            case R.id.tab_cart:
                Toast.makeText(this, "购物车", Toast.LENGTH_SHORT).show();
                if (mCartFragment == null){
                    mCartFragment = TestFragment.newInstance("CartFragment");
                }
                switchFragment(mCartFragment);
                break;
            case R.id.tab_mine:
                Toast.makeText(this, "我的", Toast.LENGTH_SHORT).show();
                if (mMineFragment == null){
                    mMineFragment = TestFragment.newInstance("MineFragment");
                }
                switchFragment(mMineFragment);
                break;
            default:
                throw new UnsupportedOperationException("unsupport");

        }
    }

    //切换fragment
    private void switchFragment(Fragment fragment) {
        //show hide add
        if (mCurrentFragment == fragment)return;

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (mCurrentFragment != null){
            transaction.hide(mCurrentFragment);
        }

        if (fragment.isAdded()){
            //如果已经添加到FragmentManager里面，就展示
            transaction.show(fragment);
        }else {
            //为了方便找到Fragment，我们是可以设置Tag
            String tag ;
            if (fragment instanceof TestFragment){
                tag = ((TestFragment)fragment).getArgumentText();
            }else {
                //把类名作为tag
                tag = fragment.getClass().getName();
            }
            //添加fragment并设置Tag
            transaction.add(R.id.layout_container,fragment,tag);
        }

        transaction.commit();

        mCurrentFragment = fragment;
    }
    //恢复因系统重启造成的Fragmentmanager里面恢复的Fragment
    private void cecoverFragment() {
        FragmentManager manager = getSupportFragmentManager();
        mHomeFragment = (TestFragment) manager.findFragmentByTag("HomeFragment");
        mCategoryFragment = (CategoryFragment) manager.findFragmentByTag(CategoryFragment.class.getName());
        mCartFragment = (TestFragment) manager.findFragmentByTag("CartFragment");
        mMineFragment = (TestFragment) manager.findFragmentByTag("MineFragment");
    }

    @Override
    public void onBackPressed() {

        if (mCurrentFragment != mHomeFragment){
            //如果不是在首页，就切换到首页上
            bottomBar.selectTabWithId(R.id.tab_home);
            return;
        }
        //是首页，我们不去关闭，退到后台
        moveTaskToBack(true);
    }
}
