package pri.weiqiang.liyujapanese.mvp.model;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import pri.weiqiang.liyujapanese.config.Constants;
import pri.weiqiang.liyujapanese.manager.DBManager;
import pri.weiqiang.liyujapanese.mvp.bean.GojuonMemory;


public class GojuonMemoryFragmentModelImpl implements BaseModel.GojuonMemoryFragmentModel {


    @Override
    public void getData(final int category, Consumer<List<GojuonMemory>> consumer) {

        Observable.create(new ObservableOnSubscribe<List<GojuonMemory>>() {
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
    }
}
