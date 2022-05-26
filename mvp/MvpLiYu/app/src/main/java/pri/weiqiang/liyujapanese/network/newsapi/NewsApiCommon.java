package pri.weiqiang.liyujapanese.network.newsapi;

import io.reactivex.Observable;
import pri.weiqiang.liyujapanese.mvp.bean.newsapi.NewsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface NewsApiCommon {


    //https://newsapi.org/v2/top-headlines?from=2018-12-26&to=2018-12-26&country=jp&category=business&pageSize=6&apiKey=ef7c2f7191ec474cbdcd564382744e38
    //New Endpoint to fetch headlines.
    @GET("top-headlines")
    Observable<NewsResponse> getHeadlinesByCountry(@Query("country") String country,
                                                   @Query("from") String from,
                                                   @Query("to") String to,
                                                   @Query("category") String category,
                                                   @Query("pageSize") String pageSize,
                                                   @Query("apiKey") String apiKey);

    //New Endpoint to fetch search results.
    @GET("everything")
    Call<NewsResponse> getSearchResults(@Query("q") String query,
                                        @Query("sortBy") String sortBy,
                                        @Query("language") String language,
                                        @Query("apiKey") String apiKey);

}
