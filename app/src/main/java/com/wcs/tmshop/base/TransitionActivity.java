package com.wcs.tmshop.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.wcs.tmshop.R;

/**
 * 类描述：有转场动画的基类
 * 创建时间：2017-3-1 23:22
 */

public class TransitionActivity extends AppCompatActivity {

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        //设置转场动画
        setTransitionAnimation(true);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        setTransitionAnimation(true);
    }

    @Override
    public void finish() {
        super.finish();
        //转场动画
        setTransitionAnimation(false);
    }

    public void finishWithDefault(){
        super.finish();
    }

    private void setTransitionAnimation(boolean isNewActivity) {
        if (isNewActivity){
            //新页面从右边进入
            overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
        }else {
            //回到上个页面
            overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
        }
    }
}
