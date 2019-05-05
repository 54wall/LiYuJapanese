package pri.weiqiang.liyujapanese.network.zhihu;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import pri.weiqaing.common.config.Constants;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Networks {

    private static final int DEFAULT_TIMEOUT = 5;

    private static Retrofit retrofit;

    private static CommonApi mCommonApi;

    private static ThemeApi mThemeApi;

    private static Networks mNetworks;

    public static Networks getInstance() {
        if (mNetworks == null) {
            mNetworks = new Networks();
        }
        return mNetworks;
    }

    public CommonApi getCommonApi() {
        return mCommonApi == null ? configRetrofit(CommonApi.class) : mCommonApi;
    }


    public ThemeApi getThemeApi() {
        return mThemeApi == null ? configRetrofit(ThemeApi.class) : mThemeApi;
    }

    /*使用范型可以返回ThemeApi 或者 CommonApi不用每个都写一个方法*/
    private <T> T configRetrofit(Class<T> service) {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.ZHIHU_BASE_URL)
                .client(configClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(service);
    }

    private OkHttpClient configClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        return okHttpClient.build();
    }

}
