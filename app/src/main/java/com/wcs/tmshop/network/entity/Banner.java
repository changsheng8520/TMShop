package com.wcs.tmshop.network.entity;

import android.graphics.Picture;

import com.google.gson.annotations.SerializedName;

/**
 * 类描述：轮播图实体
 * 创建时间：2017-3-2 17:12
 */

public class Banner {


    @SerializedName("photo")
    public Picture picture;//轮播图图片
    /**
     * photo : {}
     * description :
     * url :
     */

    @SerializedName("description")
    public String description;//描述
    @SerializedName("url")
    public String url;//外链URL

    public Picture getPicture() {
        return picture;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }
}
