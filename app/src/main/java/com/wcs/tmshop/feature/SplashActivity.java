package com.wcs.tmshop.feature;

import android.animation.Animator;
import android.content.Intent;
import android.widget.ImageView;

import com.wcs.tmshop.R;
import com.wcs.tmshop.base.BaseActivity;

import butterknife.BindView;

/**
 * 类描述：
 * 创建人：
 * 创建时间：
 * 修改人：
 * 修改时间：
 * 修改备注
 */

public class SplashActivity extends BaseActivity implements Animator.AnimatorListener{

    @BindView(R.id.image_splash)
    ImageView imageSplash;

    @Override
    protected void initView() {
        imageSplash.setAlpha(0.2f);

        imageSplash.animate()
                .alpha(1.0f)
                .setDuration(4000)
                .setListener(this)
                .start();
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_splash;
    }

    // 动画开始的时候调用
    @Override
    public void onAnimationStart(Animator animation) {

    }

    // 动画结束的时候调用
    @Override
    public void onAnimationEnd(Animator animation) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finishWithDefault();
    }

    // 动画取消的时候调用
    @Override
    public void onAnimationCancel(Animator animation) {

    }

    // 动画重复播放的时候调用
    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
