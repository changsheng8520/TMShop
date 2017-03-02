package scrollview.wcs.listview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    private List<String> data;
    private ArrayAdapter<String> adapter;
    private Handler handler;
    private int visblelastindex;//可见的最后一条数据下标

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();

        listView = (ListView) findViewById(R.id.listview);

        data = new ArrayList<>();

        //添加假数据
        for (int i = 0; i < 50; i++) {
            data.add("第" + i + "条数据");
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);

        listView.setAdapter(adapter);


        //动态设置listview高度
        setListViewHeight(listView);

//        addMoreView(listView);

        listView.setOnScrollListener(listener);
    }

    private AbsListView.OnScrollListener listener = new AbsListView.OnScrollListener() {

        //当滚动状态改变时触发的方法
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            //最后一条数据的下标
            int lastIndex = adapter.getCount() - 1;
            //判断是否是静止状态，判断是否滑动到了底部（最后一条数据）
            if (scrollState == SCROLL_STATE_IDLE && visblelastindex == lastIndex){
                //加载数据
                loadMoreData();
                adapter.notifyDataSetChanged();
            }
        }
        //当listview滑动时触发的方法
        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            visblelastindex = firstVisibleItem + visibleItemCount - 1;
        }
    };


    private void addMoreView(ListView listView) {
        //拿到底部布局（加载耕读偶的View）
        View view = LayoutInflater.from(this).inflate(R.layout.item_more, null);

        listView.addFooterView(view);

        final Button btnMore = (Button) view.findViewById(R.id.btn);
        final ProgressBar prbMore = (ProgressBar) view.findViewById(R.id.progressBar);

        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                btnMore.setVisibility(View.INVISIBLE);
                prbMore.setVisibility(View.VISIBLE);
                //模拟请求网络数据，用handler发送一个延迟任务
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //加载更多
                        loadMoreData();
                        //视图操作，更新UI
                        btnMore.setVisibility(View.VISIBLE);
                        prbMore.setVisibility(View.INVISIBLE);
                        adapter.notifyDataSetChanged();
                    }
                }, 2000);
            }
        });
    }

    //加载更多
    private void loadMoreData() {
        for (int i = 0; i < 5; i++) {
            data.add("第" + i + "条数据（新）");
        }
    }

    public void setListViewHeight(ListView listView) {
        //获取listview对应的adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            //计算item的宽高（调用一个绘制的方法）
            listItem.measure(0, 0);
            //统计所有子项高度
            totalHeight += listItem.getMeasuredHeight();
        }

        //拿到listview布局参数
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        //获取分割线的总高度
        int dividerHeight = listView.getDividerHeight() * (listAdapter.getCount() - 1);
        params.height = totalHeight + dividerHeight;
        listView.setLayoutParams(params);
    }
}
