package pri.weiqiang.liyujapanese.mvp.presenter.dic;

import android.util.Log;

import java.util.List;

import io.reactivex.functions.Consumer;
import pri.weiqaing.common.base.mvp.BasePresenter;
import pri.weiqiang.liyujapanese.mvp.bean.dic.Book;
import pri.weiqiang.liyujapanese.mvp.model.dic.LessonsFragmentModel;
import pri.weiqiang.liyujapanese.mvp.model.dic.LessonsFragmentModelImpl;
import pri.weiqiang.liyujapanese.mvp.view.dic.LessonsFragmentView;

/**
 * Created by weiqiang on 2018/3/16.
 */

public class LessonsFragmentPresenterImpl extends BasePresenter<LessonsFragmentView> implements BasePresenter.LessonsFragmentPresenter {

    private final String TAG = getClass().getSimpleName();

    LessonsFragmentModel model;

    public LessonsFragmentPresenterImpl(LessonsFragmentView view) {
        super(view);
        model = new LessonsFragmentModelImpl();
    }

    @Override
    public void initLessonsFragment() {
        view.initView();
        model.getData(new Consumer<List<Book>>() {
            @Override
            public void accept(List<Book> list) throws Exception {
                Log.i(TAG, "accept: OK");
                view.setData(list);
            }
        });
    }

    @Override
    public void unsubscribe() {
        Log.e(TAG, "unsubscribe()");
        model.unsubscribe();
    }
}
