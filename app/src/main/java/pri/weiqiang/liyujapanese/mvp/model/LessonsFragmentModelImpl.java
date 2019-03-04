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
import pri.weiqiang.liyujapanese.manager.DBManager;
import pri.weiqiang.liyujapanese.mvp.bean.Book;


public class LessonsFragmentModelImpl implements BaseModel.LessonsFragmentModel {

    private String TAG = LessonsFragmentModel.class.getSimpleName();

    @Override
    public void getData(Consumer<List<Book>> consumer) {

        Disposable disposable = Observable.create(new ObservableOnSubscribe<List<Book>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Book>> emitter) throws Exception {
                List<Book> list;
                list = DBManager.getInstance().getBooks();
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
    public List<LessonsFragmentModel> getData() {
        return null;
    }
}
