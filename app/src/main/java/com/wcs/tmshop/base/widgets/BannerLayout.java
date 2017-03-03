package com.wcs.tmshop.base.widgets;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.wcs.tmshop.R;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * 类描述：自定义轮播图控件
 * 创建时间：2017-3-3 9:49
 * <p>
 * 1、自动轮播
 * 2、数据可随意设置（适配器的问题）
 * 3、自动和手动的冲突:触屏的时间+轮播时间(触屏开始 + 4秒轮播)
 */

public class BannerLayout extends RelativeLayout {


    @BindView(R.id.pager_banner)
    ViewPager pagerBanner;
    @BindView(R.id.indicator)
    CircleIndicator indicator;
    
    private static final long DURATION = 4000;
    private MyHandler myHandler;
    private Timer timer;
    private TimerTask task;
    private long resumeCycleTime;

    public BannerLayout(Context context) {
        super(context);
        init(context);
    }

    public BannerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BannerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    //布局的填充和初始化相关
    private void init(Context context) {
        //Merge 标签一定要设置Viewgroup和attachToRoot为ture
        LayoutInflater.from(context).inflate(R.layout.widget_banner_layout, this, true);
        ButterKnife.bind(this);

        myHandler = new MyHandler(this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        //在视图上显示出来的时候

        //计时器
        timer = new Timer();
        //定时发送一些时间:使用Handler来发送，并且处理
        task = new TimerTask(){
            @Override
            public void run() {
                //定时发送一些时间:使用Handler来发送，并且处理
                myHandler.sendEmptyMessage(0);
            }
        };
        //执行:  时间、延时时间、循环时间
        timer.schedule(task,DURATION,DURATION);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        //取消我们开启的计时任务
        timer.cancel();
        task.cancel();
        timer = null;
        task = null;
    }

    //事件分发  首先获取到我们触摸的时间
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        resumeCycleTime = System.currentTimeMillis() + DURATION;
        return super.dispatchTouchEvent(ev);
    }

    //切换到下一页的方法
    private void moveToNextPosition() {
        //看有没有设置适配器
        if (pagerBanner.getAdapter() == null){
            throw  new IllegalStateException("you need set a adapter");
        }
        //看适配器里面是不是有数据
        int count = pagerBanner.getAdapter().getCount();
        if (count == 0)return;

        //看是不是展示的最后一条
        if (pagerBanner.getCurrentItem() == count - 1){
            //切换到0,不设置平滑滚动
            pagerBanner.setCurrentItem(0,false);
        }else {
            pagerBanner.setCurrentItem(pagerBanner.getCurrentItem()+1,true);
        }
    }
    //设置适配器的方法
    public void setAdapter(BannerAdapter adapter){
        pagerBanner.setAdapter(adapter);
        indicator.setViewPager(pagerBanner);
        adapter.registerDataSetObserver(indicator.getDataSetObserver());

    }

    //为了防止内部类持有外部类的引用二造成内存泄漏，所以静态内部类+弱引用的方式
    private static class MyHandler extends Handler{

        private WeakReference<BannerLayout> weakReference;

        public MyHandler(BannerLayout banner) {
            weakReference = new WeakReference<BannerLayout>(banner);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            //接受到消息，处理：轮播图切换到下一页

            if (weakReference == null)return;
            BannerLayout bannerLayout = weakReference.get();
            if (bannerLayout == null)return;

            //触摸之后还没过寺庙，不去轮播
            if (System.currentTimeMillis() < bannerLayout.resumeCycleTime){
                return;
            }
            //切换到下一页
            bannerLayout.moveToNextPosition();


        }

    }
}
