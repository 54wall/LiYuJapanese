package pri.weiqiang.liyujapanese.network.pixiv.service;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import pri.weiqiang.liyujapanese.config.Constants;
import pri.weiqiang.liyujapanese.network.pixiv.PixivIllustApi;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class PixivIllustServiceImpl implements PixivService.IllustService {

    private static Retrofit instance = null;

    public static synchronized Retrofit getInstance() {

        if (instance == null) {

            instance = new Retrofit.Builder()
                    .baseUrl(Constants.PIXIV_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return instance;
    }


    @Override
    public void getIllusts(String mode, Consumer<ResponseBody> consumer) {

        getInstance().create(PixivIllustApi.class)
                .requestIllusts(mode, PixivIllustApi.CONTENT_ILLUST)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);

    }

    @Override
    public void getIllust(int id, Consumer<ResponseBody> consumer) {

        getInstance().create(PixivIllustApi.class)
                .requestIllust(PixivIllustApi.MODE_MEDIUM, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);

    }
}
