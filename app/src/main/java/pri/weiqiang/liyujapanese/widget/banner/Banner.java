package pri.weiqiang.liyujapanese.widget.banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.loader.ImageLoaderInterface;

/**
 * Created by yiyi on 2017/1/4.
 */

public class Banner extends FrameLayout implements ViewPager.OnPageChangeListener {

    private static final String TAG = "Banner";

    private int indicatorSize;
    private int mIndicatorWidth;
    private int mIndicatorHeight;
    private int mIndicatorMargin;
    private int bannerStyle = BannerConfig.CIRCLE_INDICATOR;
    private int delayTime = BannerConfig.TIME;
    private int scrollTime = BannerConfig.DURATION;
    private boolean isAutoPlay = BannerConfig.IS_AUTO_PLAY;
    private boolean isScroll = BannerConfig.IS_SCROLL;
    private int mIndicatorSelectedResId = R.drawable.shape_radius_selected;
    private int mIndicatorUnselectedResId = R.drawable.shape_radius_unselected;
    private int titleHeight;
    private int titleBackground;
    private int titleTextColor;
    private int titleTextSize;
    private int count = 0;
    private int currentItem;
    private int lastPosition = 1;

    private Context mContext;

    private BannerPagerAdapter mAdapter;

    private List<View> imageViews;
    private List<ImageView> indicatorImages;
    private List imageUrls;
    private List<String> titles;

    private BannerViewPaper mViewPaper;
    private TextView bannerTitle;
    private LinearLayout indicator;
    private BannerScroller mScroller;
    private DisplayMetrics dm;

    private ImageLoaderInterface imageLoader;

    private OnBannerClickListener mOnBannerClickListener;
    private ViewPager.OnPageChangeListener mOnPageChangeListener;

    private WeakHandler handler = new WeakHandler();
    private final Runnable task = new Runnable() {
        @Override
        public void run() {
            if (count > 1 && isAutoPlay) {
                currentItem = currentItem % (count + 1) + 1;
                if (currentItem == 1) {
                    mViewPaper.setCurrentItem(currentItem, false);
                    handler.post(task);
                } else {
                    mViewPaper.setCurrentItem(currentItem);
                    handler.postDelayed(task, delayTime);
                }
            }
        }
    };

    public Banner(Context context) {
        this(context, null);
    }

    public Banner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Banner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        titles = new ArrayList<>();
        imageUrls = new ArrayList();
        imageViews = new ArrayList<>();
        indicatorImages = new ArrayList<>();

        dm = context.getResources().getDisplayMetrics();
        indicatorSize = dm.widthPixels / 80;
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        imageViews.clear();
        View view = LayoutInflater.from(context).inflate(R.layout.banner, this, true);
        mViewPaper = view.findViewById(R.id.viewPager);
        bannerTitle = view.findViewById(R.id.banner_title);
        indicator = view.findViewById(R.id.indicator);

        handleTypedArray(context, attrs);
        initViewPaperScroll();
    }

    private void handleTypedArray(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Banner);
        mIndicatorWidth = typedArray.getDimensionPixelSize(R.styleable.Banner_indicator_width, indicatorSize);
        mIndicatorHeight = typedArray.getDimensionPixelSize(R.styleable.Banner_indicator_height, indicatorSize);
        mIndicatorMargin = typedArray.getDimensionPixelSize(R.styleable.Banner_indicator_margin, BannerConfig.PADDING_SIZE);
        mIndicatorSelectedResId = typedArray.getResourceId(R.styleable.Banner_indicator_drawable_selected, R.drawable.shape_radius_selected);
        mIndicatorUnselectedResId = typedArray.getResourceId(R.styleable.Banner_indicator_drawable_unselected, R.drawable.shape_radius_unselected);
        delayTime = typedArray.getInt(R.styleable.Banner_delay_time, BannerConfig.TIME);
        scrollTime = typedArray.getInt(R.styleable.Banner_scroll_time, BannerConfig.DURATION);
        isAutoPlay = typedArray.getBoolean(R.styleable.Banner_is_auto_play, BannerConfig.IS_AUTO_PLAY);
        titleBackground = typedArray.getColor(R.styleable.Banner_title_background, BannerConfig.TITLE_BACKGROUND);
        titleHeight = typedArray.getDimensionPixelSize(R.styleable.Banner_title_height, BannerConfig.TITLE_HEIGHT);
        titleTextColor = typedArray.getColor(R.styleable.Banner_title_textcolor, BannerConfig.TITLE_TEXT_COLOR);
        titleTextSize = typedArray.getDimensionPixelSize(R.styleable.Banner_title_textsize, BannerConfig.TITLE_TEXT_SIZE);
        typedArray.recycle();
    }

    private void initViewPaperScroll() {
        try {
            Field mField = ViewPager.class.getDeclaredField("mScroller");
            mField.setAccessible(true);
            mScroller = new BannerScroller(mViewPaper.getContext());
            mScroller.setDuration(scrollTime);
            mField.set(mViewPaper, mScroller);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public Banner isAutoPlay(boolean isAutoPlay) {
        this.isAutoPlay = isAutoPlay;
        return this;
    }

    public Banner setImageLoader(ImageLoaderInterface imageLoader) {
        this.imageLoader = imageLoader;
        return this;
    }

    public Banner setDelayTime(int delayTime) {
        this.delayTime = delayTime;
        return this;
    }

    public Banner setBannerTitles(List<String> titles) {
        this.titles = titles;
        return this;
    }

    public Banner setImages(List<?> imageUrls) {
        this.imageUrls = imageUrls;
        this.count = imageUrls.size();
        return this;
    }

    public void update(List<?> imageUrls, List<String> titles) {
        this.imageUrls.clear();
        this.titles.clear();
        this.imageUrls.addAll(imageUrls);
        this.titles.addAll(titles);
        this.count = this.imageUrls.size();
        start();
    }

    public void update(List<?> imageUrls) {
        this.imageUrls.clear();
        this.imageUrls.addAll(imageUrls);
        this.count = this.imageUrls.size();
        start();
    }

    public Banner start() {
        setImagesList(imageUrls);
        if (isAutoPlay) {
            startAutoPlay();
        }
        return this;
    }

    private void createIndicator() {
        indicatorImages.clear();
        indicator.removeAllViews();
        for (int i = 0; i < count; ++i) {
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mIndicatorWidth, mIndicatorHeight);
            params.leftMargin = mIndicatorMargin;
            params.rightMargin = mIndicatorMargin;
            if (i == 0) {
                imageView.setImageResource(mIndicatorSelectedResId);
            } else {
                imageView.setImageResource(mIndicatorUnselectedResId);
            }
            indicatorImages.add(imageView);
            indicator.addView(imageView, params);
        }
    }

    private void setData() {
        currentItem = 1;
        if (mAdapter == null) {
            mAdapter = new BannerPagerAdapter();
        }
        mViewPaper.setAdapter(mAdapter);
        mViewPaper.setFocusable(true);
        mViewPaper.setCurrentItem(currentItem);
        mViewPaper.addOnPageChangeListener(this);
        if (isScroll && count > 1) {
            mViewPaper.setScrollable(true);
        } else {
            mViewPaper.setScrollable(false);
        }
    }

    private void setImagesList(List<?> imageUrls) {
        if (imageUrls == null || imageUrls.size() <= 0) {
            Log.e(TAG, "please set the images data");
            return;
        }

        imageViews.clear();
        createIndicator();

        for (int i = 0; i <= count + 1; ++i) {
            View imageView = null;
            if (imageLoader != null) {
                imageView = imageLoader.creteImageView(mContext);
            }

            if (imageView == null) {
                imageView = new ImageView(mContext);
            }

            ((ImageView) imageView).setScaleType(ImageView.ScaleType.CENTER_CROP);
            Object url = null;
            // 无限轮播实现原理
            if (i == 0) {
                url = imageUrls.get(count - 1);
            } else if (i == count + 1) {
                url = imageUrls.get(0);
            } else {
                url = imageUrls.get(i - 1);
            }

            imageViews.add(imageView);
            if (imageLoader != null) {
                imageLoader.displayImage(mContext, url, imageView);
            } else {
                Log.e(TAG, "please set image loader");
            }
        }
        setData();
    }

    public void startAutoPlay() {
        handler.removeCallbacks(task);
        handler.postDelayed(task, delayTime);
    }

    public void stopAutoPlay() {
        handler.removeCallbacks(task);
    }

    /**
     * 返回真实的位置
     *
     * @param position
     * @return 下标从0开始
     */
    public int toRealPosition(int position) {
        int realPosition = (position - 1) % count;
        if (realPosition < 0) {
            realPosition += count;
        }
        return realPosition;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (isAutoPlay) {
            int action = ev.getAction();
            if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL
                    || action == MotionEvent.ACTION_OUTSIDE) {
                startAutoPlay();
            } else if (action == MotionEvent.ACTION_DOWN) {
                stopAutoPlay();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (mOnPageChangeListener != null) {
            mOnPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }
    }

    @Override
    public void onPageSelected(int position) {
        if (mOnPageChangeListener != null) {
            mOnPageChangeListener.onPageSelected(position);
        }
        indicatorImages.get((lastPosition - 1 + count) % count).setImageResource(mIndicatorUnselectedResId);
        indicatorImages.get((position - 1 + count) % count).setImageResource(mIndicatorSelectedResId);
        lastPosition = position;

        if (position == 0) position = count;
        if (position > count) position = 1;
        bannerTitle.setText(titles.get(position - 1));
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (mOnPageChangeListener != null) {
            mOnPageChangeListener.onPageScrollStateChanged(state);
        }
        currentItem = mViewPaper.getCurrentItem();
        switch (state) {
            case 0://No operation
                if (currentItem == 0) {
                    mViewPaper.setCurrentItem(count, false);
                } else if (currentItem == count + 1) {
                    mViewPaper.setCurrentItem(1, false);
                }
                break;
            case 1://start Sliding
                if (currentItem == count + 1) {
                    mViewPaper.setCurrentItem(1, false);
                } else if (currentItem == 0) {
                    mViewPaper.setCurrentItem(count, false);
                }
                break;
            case 2://end Sliding
                break;
        }
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        mOnPageChangeListener = onPageChangeListener;
    }

    public Banner setOnBannerClickListener(OnBannerClickListener listener) {
        this.mOnBannerClickListener = listener;
        return this;
    }

    public interface OnBannerClickListener {
        public void OnBannerClick(int position);
    }

    class BannerPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            container.addView(imageViews.get(position));
            View view = imageViews.get(position);
            if (mOnBannerClickListener != null) {
                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnBannerClickListener.OnBannerClick(toRealPosition(position));
                    }
                });
            }
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
