package pri.weiqiang.liyujapanese.mvp.model;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import pri.weiqiang.liyujapanese.manager.DBManager;
import pri.weiqiang.liyujapanese.mvp.bean.Book;


public class LessonsFragmentModelImpl implements BaseModel.LessonsFragmentModel {


    @Override
    public void getData(Consumer<List<Book>> consumer) {

        Observable.create(new ObservableOnSubscribe<List<Book>>() {
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
    }
}
