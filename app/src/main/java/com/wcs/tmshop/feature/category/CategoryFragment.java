package com.wcs.tmshop.feature.category;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wcs.tmshop.R;
import com.wcs.tmshop.base.BaseFragment;
import com.wcs.tmshop.network.TMShopClient;
import com.wcs.tmshop.network.core.UICallback;
import com.wcs.tmshop.network.entity.CategoryPrimary;
import com.wcs.tmshop.network.entity.CategoryRsp;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.OnItemClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 类描述：分类Fragment
 * 创建时间：2017-2-27 0:51
 */

public class CategoryFragment extends BaseFragment {

    @BindView(R.id.standard_toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.standard_toolbar)
    Toolbar toolbar;
    @BindView(R.id.list_category)
    ListView mListCategory;
    @BindView(R.id.list_children)
    ListView mListChildren;

    private List<CategoryPrimary> mData;
    private CategoryAdapter categoryAdapter;
    private ChildrenAdapter childrenAdapter;

    public static CategoryFragment newInstance() {
        return new CategoryFragment();
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.fragment_category;
    }

    @Override
    protected void initView() {

        initToolbar();

        //listView的展示
        categoryAdapter = new CategoryAdapter();
        mListCategory.setAdapter(categoryAdapter);
        //二级分类
        childrenAdapter = new ChildrenAdapter();
        mListChildren.setAdapter(childrenAdapter);

        //拿到数据
        if (mData != null) {
            //可以直接更新UI
            updataCategory();
        } else {
            //去进行网络请求拿到数据
            Call call = TMShopClient.getInstance().getCategory();
            call.enqueue(new UICallback() {
                @Override
                public void onFailureInUI(Call call, IOException e) {
                    Toast.makeText(getContext(), "请求失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponseInUI(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        CategoryRsp categoryRsp = new Gson().fromJson(response.body().string(), CategoryRsp.class);
                        if (categoryRsp.getmStatus().isSucceed()) {
                            mData = categoryRsp.getmData();
                            //数据有了之后，更新UI
                            Toast.makeText(getContext(), "成功" + categoryRsp.getmStatus().isSucceed(), Toast.LENGTH_SHORT).show();
                            //有了数据，数据给一级分类，默认显示第一条，二级分类才能展示
                            updataCategory();
                        }
                    }
                }
            });
        }

    }

    //更新分类数据
    private void updataCategory() {
        categoryAdapter.reset(mData);
        //切换展示二级分类
        chooseCategory(0);
    }

    //用于根据一级分类的选项，展示二级分类的内容
    private void chooseCategory(int position) {
        mListCategory.setItemChecked(position, true);//一级分类设置为选择
        childrenAdapter.reset(categoryAdapter.getItem(position).getChildren());
    }

    //数据的item的点击事件
    @OnItemClick(R.id.list_category)
    public void onItemClick(int position) {
        chooseCategory(position);

    }

    //点击二级分类
    @OnItemClick(R.id.list_children)
    public void onChilerenClick(int position) {
        //TODO 2017年3月1日14:00:55 会完善跳转页面
        String name = childrenAdapter.getItem(position).getmName();
        Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();
    }

    private void initToolbar() {
        //Fragment显示选项菜单
        setHasOptionsMenu(true);
        //处理toolbar设置
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        //actionbar不展示默认标题
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        //返回键
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbarTitle.setText(R.string.category_title);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_category, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == android.R.id.home) {//返回键
            getActivity().onBackPressed();
            return true;
        }
        if (itemId == R.id.menu_search) {
            //TODO：2017年2月27日13:30:38  后期会跳转到搜索的界面上
            Toast.makeText(getContext(), "点击了搜索", Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }
}
