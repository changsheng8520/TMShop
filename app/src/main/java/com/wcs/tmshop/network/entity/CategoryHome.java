package com.wcs.tmshop.network.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 类描述：
 * 创建时间：2017-3-2 23:50
 */

public class CategoryHome extends CategoryBase {

    @SerializedName("goods")
    private List<SimpleGoods> hotGoodsList;//首页分类的推荐商品

    public List<SimpleGoods> getHotGoodsList() {
        return hotGoodsList;
    }
}
