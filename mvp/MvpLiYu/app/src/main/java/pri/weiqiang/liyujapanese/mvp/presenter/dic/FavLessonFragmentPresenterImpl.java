package pri.weiqiang.liyujapanese.mvp.presenter.dic;


import android.util.Log;

import java.util.List;

import io.reactivex.functions.Consumer;
import pri.weiqaing.common.base.mvp.BasePresenter;
import pri.weiqiang.liyujapanese.mvp.bean.dic.LessonFav;
import pri.weiqiang.liyujapanese.mvp.model.dic.FavLessonFragmentModel;
import pri.weiqiang.liyujapanese.mvp.model.dic.FavLessonFragmentModelImpl;
import pri.weiqiang.liyujapanese.mvp.view.dic.FavLessonFragmentView;


/**
 * Created by weiqiang on 2018/3/25.
 */

public class FavLessonFragmentPresenterImpl extends BasePresenter<FavLessonFragmentView> implements BasePresenter.FavLessonFragmentPresenter {

    private final String TAG = getClass().getSimpleName();
    FavLessonFragmentModel model;

    public FavLessonFragmentPresenterImpl(FavLessonFragmentView view) {
        super(view);
        model = new FavLessonFragmentModelImpl();
    }

    @Override
    public void initFavLessonFragment() {
        view.setRecyclerView();

        model.getData(new Consumer<List<LessonFav>>() {
            @Override
            public void accept(List<LessonFav> list) throws Exception {
                Log.e(TAG, "list.size:" + list.size());
                view.setData(list);

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e(TAG, "model.getData-throwable:" + throwable.toString());
            }
        });
    }

    @Override
    public void unsubscribe() {
        Log.e(TAG, "unsubscribe()");
        model.unsubscribe();
    }


}

