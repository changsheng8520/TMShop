package com.wcs.tmshop.network.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 类描述：首页轮播图接口响应体
 * 创建时间：2017-3-2 16:46
 */

public class HomeBannerRsp {


    @SerializedName("status")
    public Status status;
    @SerializedName("data")
    public DataBean data;

    public Status getStatus() {
        return status;
    }

    public DataBean getData() {
        return data;
    }

    public static class StatusBean {
    }

    public static class DataBean {
        @SerializedName("player")
        public List<Banner> banners;
        @SerializedName("promote_goods")
        public List<SimpleGoods> goodsList;

        public List<Banner> getBanners() {
            return banners;
        }

        public List<SimpleGoods> getGoodsList() {
            return goodsList;
        }
    }
}
