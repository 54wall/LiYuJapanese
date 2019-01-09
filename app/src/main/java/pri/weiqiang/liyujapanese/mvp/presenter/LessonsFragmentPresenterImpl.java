package pri.weiqiang.liyujapanese.mvp.presenter;

import android.util.Log;

import java.util.List;

import io.reactivex.functions.Consumer;
import pri.weiqiang.liyujapanese.mvp.bean.Book;
import pri.weiqiang.liyujapanese.mvp.model.BaseModel;
import pri.weiqiang.liyujapanese.mvp.model.LessonsFragmentModelImpl;
import pri.weiqiang.liyujapanese.mvp.view.BaseView;

/**
 * Created by weiqiang on 2018/3/16.
 */

public class LessonsFragmentPresenterImpl extends BasePresenter<BaseView.LessonsFragmentView> implements BasePresenter.LessonsFragmentPresenter {

    private final String TAG = getClass().getSimpleName();

    BaseModel.LessonsFragmentModel model;

    public LessonsFragmentPresenterImpl(BaseView.LessonsFragmentView view) {
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
}
