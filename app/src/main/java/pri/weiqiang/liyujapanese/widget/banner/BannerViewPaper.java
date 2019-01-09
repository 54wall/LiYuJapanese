package pri.weiqiang.liyujapanese.widget.banner;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by yiyi on 2017/1/9.
 */

public class BannerViewPaper extends ViewPager {

    private boolean scrollable = true;

    public BannerViewPaper(Context context) {
        super(context);
    }

    public BannerViewPaper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return this.scrollable && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return this.scrollable && super.onInterceptTouchEvent(ev);
    }

    public void setScrollable(boolean scrollable) {
        this.scrollable = scrollable;
    }
}
