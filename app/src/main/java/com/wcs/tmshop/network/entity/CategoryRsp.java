package com.wcs.tmshop.network.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 类描述：暂时使用的商品分类的响应体
 * 创建时间：2017-2-24 6:59
 */

public class CategoryRsp {

    @SerializedName("data")
    private List<CategoryPrimary> mData;

    @SerializedName("status")
    private Status mStatus;

    public List<CategoryPrimary> getmData() {
        return mData;
    }

    public Status getmStatus() {
        return mStatus;
    }
}
