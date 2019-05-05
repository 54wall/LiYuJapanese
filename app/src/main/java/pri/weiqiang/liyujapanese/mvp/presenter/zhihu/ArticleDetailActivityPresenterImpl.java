package pri.weiqiang.liyujapanese.mvp.presenter.zhihu;

import android.util.Log;

import io.reactivex.functions.Consumer;
import pri.weiqaing.common.base.mvp.BasePresenter;
import pri.weiqiang.liyujapanese.mvp.bean.zhihu.StoryContentEntity;
import pri.weiqiang.liyujapanese.mvp.model.zhihu.ArticleDetailActivityModel;
import pri.weiqiang.liyujapanese.mvp.model.zhihu.ArticleDetailActivityModelImpl;
import pri.weiqiang.liyujapanese.mvp.view.zhihu.ArticleDetailActivityView;

/**
 * Created by weiqiang on 2018/4/10.
 */

public class ArticleDetailActivityPresenterImpl extends BasePresenter<ArticleDetailActivityView> implements BasePresenter.ArticleDetailActivityPresenter {

    private final String TAG = getClass().getSimpleName();

    ArticleDetailActivityModel model;

    public ArticleDetailActivityPresenterImpl(ArticleDetailActivityView view) {
        super(view);
        model = new ArticleDetailActivityModelImpl();
    }

    @Override
    public void showContent(int id) {
        model.getContent(new Consumer<StoryContentEntity>() {
            @Override
            public void accept(StoryContentEntity storyContentEntity) {
                view.showContent(storyContentEntity);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e(TAG, "showContent-throwable:" + throwable.toString());
            }
        }, id);
    }
}
