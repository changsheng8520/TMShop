package com.wcs.tmshop.network;

import com.google.gson.Gson;
import com.wcs.tmshop.network.entity.CategoryRsp;
import com.wcs.tmshop.network.entity.HomeBannerRsp;
import com.wcs.tmshop.network.entity.HomeCategoryRsp;

import org.junit.Test;

import okhttp3.Call;
import okhttp3.Response;

import static org.junit.Assert.assertTrue;

/**
 * 类描述：测试接口
 * 创建时间：2017-2-24 7:23
 */
public class TMShopClientTest {
    @Test
    public void getCategory() throws Exception {
        Call call = TMShopClient.getInstance().getCategory();
        Response response = call.execute();
        String string = response.body().string();
        CategoryRsp categoryRsp = new Gson().fromJson(string, CategoryRsp.class);
        //断言方法
        assertTrue(categoryRsp.getmStatus().isSucceed());
    }

    @Test
    public void getHomeBanner() throws Exception{
        Call call = TMShopClient.getInstance().getHomeBanner();
        Response response = call.execute();
        String string = response.body().string();
        HomeBannerRsp bannerRsp = new Gson().fromJson(string, HomeBannerRsp.class);
        assertTrue(bannerRsp.getStatus().isSucceed());

    }

    @Test
    public void getHomeCategory() throws Exception{
        Call call = TMShopClient.getInstance().getHomeCategory();
        Response response = call.execute();
        String string = response.body().string();
        HomeCategoryRsp categoryRsp = new Gson().fromJson(string, HomeCategoryRsp.class);
        assertTrue(categoryRsp.getStatus().isSucceed());
    }


}