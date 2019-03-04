package pri.weiqiang.liyujapanese.mvp.model;

import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import pri.weiqiang.liyujapanese.config.Constants;
import pri.weiqiang.liyujapanese.manager.DBManager;
import pri.weiqiang.liyujapanese.mvp.bean.GojuonItem;


public class GojuonFragmentModelImpl implements BaseModel.GojuonFragmentModel {

    private String TAG = GojuonFragmentModel.class.getSimpleName();

    @Override
    public void getData(final int category, Consumer<List<GojuonItem>> consumer) {

        Disposable disposable = Observable.create(new ObservableOnSubscribe<List<GojuonItem>>() {
            @Override
            public void subscribe(ObservableEmitter<List<GojuonItem>> emitter) throws Exception {
                List<GojuonItem> list;
                switch (category) {

                    case Constants.CATEGORY_QINGYIN:
                        list = DBManager.getInstance().getQingYin();
                        break;
                    case Constants.CATEGORY_ZHUOYIN:
                        list = DBManager.getInstance().getZhuoYin();
                        break;
                    case Constants.CATEGORY_AOYIN:
                        list = DBManager.getInstance().getAoYin();
                        break;
                    default:
                        list = null;
                        break;

                }
                if (list == null) {
                    emitter.onError(new Exception());
                } else {
                    emitter.onNext(list);
                }
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void unsubscribe() {
        Log.e(TAG, "unsubscribe()");
        mCompositeDisposable.clear();
    }

    @Override
    public List<GojuonFragmentModel> getData() {
        return null;
    }
}
