package pri.weiqiang.liyujapanese.mvp.presenter;

import android.util.Log;

import io.reactivex.functions.Consumer;
import pri.weiqiang.liyujapanese.mvp.bean.zhihu.BeforeDailyEntity;
import pri.weiqiang.liyujapanese.mvp.bean.zhihu.LatestDailyEntity;
import pri.weiqiang.liyujapanese.mvp.model.BaseModel;
import pri.weiqiang.liyujapanese.mvp.model.ZhihuFragmentModelImpl;
import pri.weiqiang.liyujapanese.mvp.view.BaseView;

/**
 * Created by weiqiang on 2018/4/10.
 */

public class ZhihuFragmentPresenterImpl extends BasePresenter<BaseView.ZhihuFragmentView> implements BasePresenter.ZhihuFragmentPresenter {

    private final String TAG = getClass().getSimpleName();

    BaseModel.ZhihuFragmentModel model;

    public ZhihuFragmentPresenterImpl(BaseView.ZhihuFragmentView view) {
        super(view);
        model = new ZhihuFragmentModelImpl();
    }

    @Override
    public void initZhihuFragment() {
        Log.e(TAG, "initZhihuFragment getLatestDaily");
        model.getLatestDaily(new Consumer<LatestDailyEntity>() {
            @Override
            public void accept(LatestDailyEntity latestDailyEntity) {
                if (latestDailyEntity != null) {
                    view.refreshHomeList(latestDailyEntity);
                }
            }

        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e(TAG, "getLatestDaily-Throwable:" + throwable.toString());
            }
        });
    }

    @Override
    public void getBeforeDaily(String date) {
        Log.e(TAG, "getBeforeDaily");
        model.getBeforeDaily(new Consumer<BeforeDailyEntity>() {
            @Override
            public void accept(BeforeDailyEntity beforeDailyEntity) {
                view.loadBeforeDaily(beforeDailyEntity);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e(TAG, "getBeforeDaily-Throwable:" + throwable.toString());
            }
        }, date);
    }

    @Override
    public void unsubscribe() {
        Log.e(TAG, "unsubscribe()");
        model.unsubscribe();
    }
}
