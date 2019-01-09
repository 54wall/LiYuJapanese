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
import pri.weiqiang.liyujapanese.mvp.bean.GojuonItem;


public class GojuonFragmentModelImpl implements BaseModel.GojuonFragmentModel {


    @Override
    public void getData(final int category, Consumer<List<GojuonItem>> consumer) {

        Observable.create(new ObservableOnSubscribe<List<GojuonItem>>() {
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
    }
}
