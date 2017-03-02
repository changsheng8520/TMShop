package scrollview.wcs.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 类描述：
 * 创建人：
 * 创建时间：
 * 修改人：
 * 修改时间：
 * 修改备注
 */

public class MyListView extends ListView {
    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //重写该方法，达到使ListView可以正确的嵌套在ScrollView中

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //自定义绘制规则
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
