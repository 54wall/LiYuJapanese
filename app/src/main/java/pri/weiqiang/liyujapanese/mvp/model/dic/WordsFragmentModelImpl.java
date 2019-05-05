package pri.weiqiang.liyujapanese.mvp.model.dic;

import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import pri.weiqiang.liyujapanese.manager.DBManager;
import pri.weiqiang.liyujapanese.mvp.bean.dic.Word;


public class WordsFragmentModelImpl implements WordsFragmentModel {

    private String TAG = WordsFragmentModel.class.getSimpleName();

    @Override
    public void getData(Consumer<List<Word>> consumer, final String lesson) {

        Disposable disposable = Observable.create(new ObservableOnSubscribe<List<Word>>() {
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
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void unsubscribe() {
        Log.e(TAG, "unsubscrible");
        mCompositeDisposable.clear();
    }

    @Override
    public List<WordsFragmentModel> getData() {
        return null;
    }
}
