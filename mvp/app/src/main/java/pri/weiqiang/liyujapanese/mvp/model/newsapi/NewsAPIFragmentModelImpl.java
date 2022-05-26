package pri.weiqiang.liyujapanese.mvp.model.newsapi;

import android.util.Log;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import pri.weiqiang.liyujapanese.mvp.bean.newsapi.NewsResponse;
import pri.weiqiang.liyujapanese.network.newsapi.NewsApiNetworks;


public class NewsAPIFragmentModelImpl implements NewsAPIFragmentModel {
    private String TAG = NewsAPIFragmentModelImpl.class.getSimpleName();

    @Override
    public void getHeadlinesByCountry(Consumer<NewsResponse> consumer, Consumer<Throwable> throwble, String country, String from, String to, String category, String pageSize, String apiKey) {
        Log.e(TAG, "getLatestNews");
        Disposable disposable = NewsApiNetworks.getInstance().getCommonApi().getHeadlinesByCountry(country, from, to, category, pageSize, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer, throwble);
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void unsubscribe() {
        Log.e(TAG, "unsubscrible");
        mCompositeDisposable.clear();
    }

    @Override
    public List<NewsAPIFragmentModel> getData() {
        return null;
    }
}
