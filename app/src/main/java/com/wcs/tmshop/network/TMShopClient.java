package com.wcs.tmshop.network;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 类描述：网络请求的客户端
 * 创建人：
 * 创建时间：
 * 修改人：
 * 修改时间：
 * 修改备注
 */

public class TMShopClient {

    public static final String BASE_URL = "http://106.14.32.204/eshop/emobile/?url=";

    private static TMShopClient tmShopClient;
    private final OkHttpClient okHttpClient;

    public static synchronized TMShopClient getInstance(){
        if (tmShopClient ==null){
            tmShopClient = new TMShopClient();
        }
        return tmShopClient;
    }

    private TMShopClient() {
        //日志拦截器的创建
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //okhttpClient的初始化
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    //构建一下商品分类的接口请求
    public Call getCategory(){
        Request request = new Request.Builder()
                .get()
                .url(BASE_URL+"/category")
                .build();
        return okHttpClient.newCall(request);
    }


}
