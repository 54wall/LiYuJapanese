package pri.weiqiang.liyujapanese.mvp.model;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import pri.weiqiang.liyujapanese.manager.DBManager;
import pri.weiqiang.liyujapanese.mvp.bean.Word;


public class WordsFragmentModelImpl implements BaseModel.WordsFragmentModel {


    @Override
    public void getData(Consumer<List<Word>> consumer, final String lesson) {

        Observable.create(new ObservableOnSubscribe<List<Word>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Word>> emitter) throws Exception {
                List<Word> list = DBManager.getInstance().queryWord(lesson);
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
