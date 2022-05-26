package pri.weiqiang.liyujapanese.mvp.presenter.newsapi;

import android.util.Log;

import io.reactivex.functions.Consumer;
import pri.weiqaing.common.base.mvp.BasePresenter;
import pri.weiqiang.liyujapanese.mvp.bean.newsapi.NewsResponse;
import pri.weiqiang.liyujapanese.mvp.model.newsapi.NewsAPIFragmentModel;
import pri.weiqiang.liyujapanese.mvp.model.newsapi.NewsAPIFragmentModelImpl;
import pri.weiqiang.liyujapanese.mvp.view.newsapi.NewsAPIFragmentView;

/**
 * Created by weiqiang on 2018/12/14.
 */

public class NewsAPIFragmentPresenterImpl extends BasePresenter<NewsAPIFragmentView> implements BasePresenter.NewsAPIFragmentPresenter {

    private final String TAG = getClass().getSimpleName();

    private NewsAPIFragmentModel model;

    public NewsAPIFragmentPresenterImpl(NewsAPIFragmentView view) {
        super(view);
        model = new NewsAPIFragmentModelImpl();
    }

    @Override
    public void getNews(String country, String from, String to, String category, String pageSize, String apiKey) {
        Log.e(TAG, "getNews getNews");
        model.getHeadlinesByCountry(new Consumer<NewsResponse>() {
            @Override
            public void accept(NewsResponse newsResponse) {
                Log.e(TAG, "news " + newsResponse.toString());
                if (newsResponse != null) {
                    view.refreshNews(newsResponse);
                }
            }

        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e(TAG, "getLatestDaily-Throwable:" + throwable.toString());
            }
        }, country, from, to, category, pageSize, apiKey);

    }

    @Override
    public void getNewsBefore(String country, String from, String to, String category, String pageSize, String apiKey) {
        Log.e(TAG, "getNews getNews");
        model.getHeadlinesByCountry(new Consumer<NewsResponse>() {
            @Override
            public void accept(NewsResponse newsResponse) {
                Log.e(TAG, "news " + newsResponse.toString());
                if (newsResponse != null) {
                    view.loadNewsBefore(newsResponse);
                }
            }

        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e(TAG, "getLatestDaily-Throwable:" + throwable.toString());
            }
        }, country, from, to, category, pageSize, apiKey);

    }

    @Override
    public void unsubscribe() {
        Log.e(TAG, "unsubscribe()");
        model.unsubscribe();
    }

}
