package com.wcs.tmshop.network.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 类描述：简单商品对象
 * 创建时间：2017-3-2 17:16
 */

public class SimpleGoods {


    /**
     * goods_id : 32
     * name : 标题
     * id:商品ID
     * market_price : 商场价格
     * shop_price : 商店价格
     * promote_price : 促销价格
     * brief : 简要
     * img : {图片对象}
     */
    @SerializedName("id")
    public int id;
    @SerializedName("goods_id")
    public int goodsId;
    @SerializedName("name")
    public String name;
    @SerializedName("market_price")
    public String marketPrice;
    @SerializedName("shop_price")
    public String shopPrice;
    @SerializedName("promote_price")
    public String promotePrice;
    @SerializedName("brief")
    public String brief;
    @SerializedName("img")
    public String img;

    public int getId() {
        if (id != 0)return id;
        return goodsId;
    }

    public int getGoodsId() {

        return goodsId;
    }

    public String getName() {
        return name;
    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public String getShopPrice() {
        return shopPrice;
    }

    public String getPromotePrice() {
        return promotePrice;
    }

    public String getBrief() {
        return brief;
    }

    public String getImg() {
        return img;
    }
}
