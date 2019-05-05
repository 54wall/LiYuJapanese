package pri.weiqiang.liyujapanese.mvp.model.newsapi;

import io.reactivex.functions.Consumer;
import pri.weiqiang.liyujapanese.mvp.bean.newsapi.NewsResponse;
import pri.weiqaing.common.base.mvp.BaseModel;

public interface NewsAPIFragmentModel extends BaseModel {
    void getHeadlinesByCountry(Consumer<NewsResponse> consumer, Consumer<Throwable> throwable, String country, String from, String to, String category, String pageSize, String apiKey);
}
