package com.wcs.tmshop.network.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 类描述：首页商品分类接口响应体
 * 创建时间：2017-3-2 23:48
 */

public class HomeCategoryRsp {

    @SerializedName("status") private Status status;

    @SerializedName("data") private List<CategoryHome> datas;

    public Status getStatus() {
        return status;
    }

    public List<CategoryHome> getDatas() {
        return datas;
    }
}
