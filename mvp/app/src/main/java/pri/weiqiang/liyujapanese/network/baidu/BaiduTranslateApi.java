package pri.weiqiang.liyujapanese.network.baidu;

import io.reactivex.Observable;
import pri.weiqiang.liyujapanese.mvp.bean.translation.BaiduTranslateBean;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface BaiduTranslateApi {

    String AUTO = "auto";
    String ZH = "zh";
    String JP = "jp";
    String EN = "en";

    String OK = "52000";
    String ERROR_TIMEOUT = "52001";
    String ERROR_SYSTEM = "52002";
    String ERROR_APPID = "52003";
    String ERROR_PARAMS = "54000";
    String ERROR_ILLEGAL_IP = "58000";
    String ERROR_SIGN = "54001";
    String ERROR_FREQUENT = "54003";
    String ERROR_TRANSLATE = "58001";
    String ERROR_NO_MONEY = "54004";
    String ERROR_LONG_FREQUENT = "54005";


    @GET("/api/trans/vip/translate")
    Observable<BaiduTranslateBean> request(@Query("q") String q, @Query("from") String from, @Query("to") String to,
                                           @Query("appid") String appId, @Query("salt") int salt, @Query("sign") String sign);


}
