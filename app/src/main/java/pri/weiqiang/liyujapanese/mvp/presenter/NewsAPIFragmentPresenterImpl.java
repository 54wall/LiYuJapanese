package pri.weiqiang.liyujapanese.mvp.presenter;

import android.util.Log;

import io.reactivex.functions.Consumer;
import pri.weiqiang.liyujapanese.mvp.bean.newsapi.NewsResponse;
import pri.weiqiang.liyujapanese.mvp.model.BaseModel;
import pri.weiqiang.liyujapanese.mvp.model.NewsAPIFragmentModelImpl;
import pri.weiqiang.liyujapanese.mvp.view.BaseView;

/**
 * Created by weiqiang on 2018/12/14.
 */

public class NewsAPIFragmentPresenterImpl extends BasePresenter<BaseView.NewsAPIFragmentView> implements BasePresenter.NewsAPIFragmentPresenter {

    private final String TAG = getClass().getSimpleName();

    private BaseModel.NewsAPIFragmentModel model;

    public NewsAPIFragmentPresenterImpl(BaseView.NewsAPIFragmentView view) {
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

}
