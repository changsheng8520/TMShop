package com.wcs.tmshop.network.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

/**
 * 类描述：商品一级分类
 * 创建时间：2017-2-24 6:52
 */

public class CategoryPrimary extends CategoryBase{

    @SerializedName("children")
    private List<CategoryBase> children = Collections.emptyList();

    public List<CategoryBase> getChildren() {
        return children;
    }
}
