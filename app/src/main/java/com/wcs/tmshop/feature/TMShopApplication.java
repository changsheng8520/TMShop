package com.wcs.tmshop.feature;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * 类描述：
 * 创建人：
 * 创建时间：
 * 修改人：
 * 修改时间：
 * 修改备注
 */

public class TMShopApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {

            // 这个是用于分析内存的线程，我们不能再这里面初始化我们项目
            return;
        }
        LeakCanary.install(this);

        // 正常的app初始化
    }

}
