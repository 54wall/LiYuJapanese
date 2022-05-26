package pri.weiqiang.liyujapanese.network.pixiv;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface PixivIllustApi {

    String CONTENT_ILLUST = "illust";

    String MODE_DAILY = "daily";
    String MODE_WEEKLY = "weekly";
    String MODE_MONTHLY = "monthly";
    String MODE_ROOKIE = "rookie";
    String MODE_ORIGINAL = "original";
    String MODE_MALE = "male";
    String MODE_FEMALE = "female";


    String MODE_MEDIUM = "medium";

    @GET("/ranking.php")
    Observable<ResponseBody> requestIllusts(@Query("mode") String mode, @Query("content") String content);

    @GET("/member_illust.php")
    Observable<ResponseBody> requestIllust(@Query("mode") String mode, @Query("illust_id") int id);


}
