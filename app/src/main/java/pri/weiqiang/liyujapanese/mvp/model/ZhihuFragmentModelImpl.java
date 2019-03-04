package pri.weiqiang.liyujapanese.mvp.model;

import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import pri.weiqiang.liyujapanese.mvp.bean.zhihu.BeforeDailyEntity;
import pri.weiqiang.liyujapanese.mvp.bean.zhihu.LatestDailyEntity;
import pri.weiqiang.liyujapanese.network.zhihu.Networks;

import static pri.weiqiang.liyujapanese.mvp.model.BaseModel.mCompositeDisposable;


public class ZhihuFragmentModelImpl implements BaseModel.ZhihuFragmentModel {
    private String TAG = ZhihuFragmentModelImpl.class.getSimpleName();

    @Override
    public void getLatestDaily(Consumer<LatestDailyEntity> consumer, Consumer<Throwable> throwble) {


        mCompositeDisposable.add(
                Networks.getInstance().getCommonApi().getLatestDaily()//产生被观察者
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(consumer, throwble)
        );
    }

    @Override
    public void getBeforeDaily(Consumer<BeforeDailyEntity> consumer, Consumer<Throwable> throwble, String date) {

        mCompositeDisposable.add(
                Networks.getInstance().getCommonApi().getBeforeDaily(date)//产生被观察者
                        .subscribeOn(Schedulers.io())
                        .onErrorReturn(new Function<Throwable, BeforeDailyEntity>() {
                            @Override
                            public BeforeDailyEntity apply(Throwable throwable) throws Exception {
                                Log.e(TAG, "BeforeDailyEntity onErrorReturn");
                                return null;
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(consumer, throwble)
        );
    }

}
