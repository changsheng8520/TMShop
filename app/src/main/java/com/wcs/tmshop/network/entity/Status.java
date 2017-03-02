package com.wcs.tmshop.network.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 类描述：响应状态对象
 * 创建时间：2017-2-24 7:01
 */

public class Status {

    @SerializedName("succeed")
    private int succeed;
    @SerializedName("error_code")
    private int errorCode;
    @SerializedName("error_desc")
    private String errorDesc;

    public boolean isSucceed(){
        return succeed == 1;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }
}
