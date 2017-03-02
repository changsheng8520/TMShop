package com.wcs.tmshop.network.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 类描述：商品分类基类
 * 创建时间：
 */

public class CategoryBase {

    @SerializedName("id")private int mId;
    @SerializedName("name")private String mName;

    public int getmId() {
        return mId;
    }

    public String getmName() {
        return mName;
    }
}
