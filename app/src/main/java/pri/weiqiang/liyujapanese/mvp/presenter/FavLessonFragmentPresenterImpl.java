package pri.weiqiang.liyujapanese.mvp.presenter;


import android.util.Log;

import java.util.List;

import io.reactivex.functions.Consumer;
import pri.weiqiang.liyujapanese.mvp.bean.LessonFav;
import pri.weiqiang.liyujapanese.mvp.model.BaseModel;
import pri.weiqiang.liyujapanese.mvp.model.FavLessonFragmentModelImpl;
import pri.weiqiang.liyujapanese.mvp.view.BaseView;


/**
 * Created by weiqiang on 2018/3/25.
 */

public class FavLessonFragmentPresenterImpl extends BasePresenter<BaseView.FavLessonFragmentView> implements BasePresenter.FavLessonFragmentPresenter {

    private final String TAG = getClass().getSimpleName();
    BaseModel.FavLessonFragmentModel model;

    public FavLessonFragmentPresenterImpl(BaseView.FavLessonFragmentView view) {
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


}

