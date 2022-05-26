package pri.weiqiang.liyujapanese.mvp.model.gojuon;

import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import pri.weiqaing.common.config.Constants;
import pri.weiqiang.liyujapanese.manager.DBManager;
import pri.weiqiang.liyujapanese.mvp.bean.gojuon.GojuonMemory;


public class GojuonMemoryFragmentModelImpl implements GojuonMemoryFragmentModel {

    private String TAG = GojuonMemoryFragmentModel.class.getSimpleName();

    @Override
    public void getData(final int category, Consumer<List<GojuonMemory>> consumer) {

        Disposable disposable = Observable.create(new ObservableOnSubscribe<List<GojuonMemory>>() {
            @Override
            public void subscribe(ObservableEmitter<List<GojuonMemory>> emitter) throws Exception {
                List<GojuonMemory> list;
                switch (category) {

                    case Constants.GOJUON_MEMORY:
                        list = DBManager.getInstance().getGojuonMemory();
                        break;
                    case Constants.GOJUON_CHENGYU:
                        list = DBManager.getInstance().getGojuonChengyu();
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
    public List<GojuonMemoryFragmentModel> getData() {
        return null;
    }
}
