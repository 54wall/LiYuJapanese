package pri.weiqiang.liyujapanese.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import pri.weiqaing.common.base.BaseFragment;
import pri.weiqaing.common.base.mvp.BasePresenter;
import pri.weiqaing.common.config.Constants;
import pri.weiqaing.common.utils.DateUtils;
import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.mvp.bean.newsapi.DisplaybleNews;
import pri.weiqiang.liyujapanese.mvp.bean.newsapi.NewsResponse;
import pri.weiqiang.liyujapanese.mvp.presenter.newsapi.NewsAPIFragmentPresenterImpl;
import pri.weiqiang.liyujapanese.mvp.view.newsapi.NewsAPIFragmentView;
import pri.weiqiang.liyujapanese.ui.adapter.newsapi.NewsApiHYArticleListAdapter;
import pri.weiqiang.liyujapanese.ui.adapter.newsapi.SectionItem;

/**
 * Created by weiqiang on 2018/12/11.
 */

public class NewsAPIFragment extends BaseFragment implements NewsAPIFragmentView {

    private static final String TAG = NewsAPIFragment.class.getSimpleName();
    public BasePresenter.NewsAPIFragmentPresenter presenter;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout mSwipeRefresh;
    private NewsApiHYArticleListAdapter mArticleListAdapter;
    private List<DisplaybleNews> mArticleList;
    private String mdate;
    private String country;
    private String category;


    public static NewsAPIFragment newInstance() {
        NewsAPIFragment fragment = new NewsAPIFragment();
        return fragment;

    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_news_api;
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(false);
        presenter = new NewsAPIFragmentPresenterImpl(this);
        mArticleList = new ArrayList<>();
        mArticleListAdapter = new NewsApiHYArticleListAdapter(getContext(), mArticleList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mArticleListAdapter);
        mSwipeRefresh.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mdate = DateUtils.getCurDate();
                Log.e(TAG, "onRefresh()+mdate:" + mdate);
                country = Constants.NEWS_COUNTRY;
                category = Constants.NEWS_CATEGORY_GENERAL;
                presenter.getNews(country, mdate, mdate, category, Constants.API_NEWS_PAGE, Constants.API_NEWS_KEY);
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                Log.e(TAG, "onScrollStateChanged-getChildCount:" + recyclerView.getLayoutManager().getChildCount());
                if (recyclerView.getLayoutManager().getChildCount() != 0) {
                    View lastchildView = recyclerView.getLayoutManager().getChildAt(recyclerView.getLayoutManager().getChildCount() - 1);
                    int lastChildBottomY = lastchildView.getBottom();
                    int recyclerBottomY = recyclerView.getBottom() - recyclerView.getPaddingBottom();
                    int lastPosition = recyclerView.getLayoutManager().getPosition(lastchildView);

                    if (lastChildBottomY == recyclerBottomY && newState == RecyclerView.SCROLL_STATE_IDLE
                            && lastPosition == recyclerView.getLayoutManager().getItemCount() - 1) {
                        mdate = DateUtils.getSpecifiedDayBefore(mdate);
                        Log.e(TAG, "前一天:" + mdate);
                        presenter.getNewsBefore(country, mdate, mdate, category, Constants.API_NEWS_PAGE, Constants.API_NEWS_KEY);
                    }
                }
            }
        });
    }

    @Override
    protected void doAction() {
        mdate = DateUtils.getCurDate();
        country = Constants.NEWS_COUNTRY;
        category = Constants.NEWS_CATEGORY_GENERAL;
        presenter.getNews(country, mdate, mdate, category, Constants.API_NEWS_PAGE, Constants.API_NEWS_KEY);

    }

    public void updateNewsAPI(String category) {
        Log.e(TAG, "updateNewsAPI category=" + category);
        presenter.getNews(country, mdate, mdate, category, Constants.API_NEWS_PAGE, Constants.API_NEWS_KEY);
    }


    @Override
    public <T> void refreshNews(T t) {
        mArticleList.clear();
        NewsResponse newsResponse = (NewsResponse) t;
        Log.e(TAG, "refreshNews mdate:" + mdate);
        mArticleList.add(new SectionItem(mdate));
        mArticleList.addAll(newsResponse.getArticles());
        mArticleListAdapter.notifyDataSetChanged();
        mRecyclerView.scrollToPosition(0);
        mSwipeRefresh.setRefreshing(false);
    }

    @Override
    public <T> void loadNewsBefore(T t) {
        Log.e(TAG, "loadNewsBefore mdate:" + mdate);
        NewsResponse newsResponse = (NewsResponse) t;
        mArticleList.add(new SectionItem(mdate));
        mArticleList.addAll(newsResponse.getArticles());
        mArticleListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy:presenter.unsubscribe();");
        super.onDestroy();
        // 将所有的 observer 取消订阅
        presenter.unsubscribe();
    }

}
