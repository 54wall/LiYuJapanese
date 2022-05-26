package pri.weiqiang.liyujapanese.network.zhihu;

import io.reactivex.Observable;
import pri.weiqiang.liyujapanese.mvp.bean.zhihu.BeforeDailyEntity;
import pri.weiqiang.liyujapanese.mvp.bean.zhihu.LatestDailyEntity;
import pri.weiqiang.liyujapanese.mvp.bean.zhihu.StoryContentEntity;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface CommonApi {


    /**
     * 获取最新文章列表
     *
     * @return
     */
    @GET("news/latest")
    Observable<LatestDailyEntity> getLatestDaily();

    /**
     * 获取以前的文章列表
     *
     * @return
     */
    @GET("news/before/{date}")
    Observable<BeforeDailyEntity> getBeforeDaily(@Path("date") String date);

    /**
     * 获取相应文章内容
     *
     * @param storyId
     * @return
     */
    @GET("news/{storyId}")
    Observable<StoryContentEntity> getStoryContent(@Path("storyId") int storyId);

}
