package pri.weiqiang.liyujapanese.network.baidu.service;

import com.blankj.utilcode.utils.EncryptUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import pri.weiqiang.liyujapanese.config.Constants;
import pri.weiqiang.liyujapanese.mvp.bean.BaiduTranslateBean;
import pri.weiqiang.liyujapanese.network.baidu.BaiduTranslateApi;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class BaiduTranslateServiceImpl implements BaiduService.TranslateService {

    private static Retrofit instance = null;

    public static synchronized Retrofit getInstance() {

        if (instance == null) {

            instance = new Retrofit.Builder()
                    .baseUrl(Constants.BAIDU_TRANSLATE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

        }
        return instance;
    }


    @Override
    public void translate(String q, String from, String to, Consumer<BaiduTranslateBean> consumer, Consumer<Throwable> throwable) {

        int salt = (int) (Math.random() * 99999);
        String sign = EncryptUtils.encryptMD5ToString(Constants.BAIDU_TRANSLATE_APPID + q + salt + Constants.BAIDU_TRANSLATE_SECRETKEY).toLowerCase();

        getInstance().create(BaiduTranslateApi.class)
                .request(q, from, to, Constants.BAIDU_TRANSLATE_APPID, salt, sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(consumer, throwable);

    }
}
