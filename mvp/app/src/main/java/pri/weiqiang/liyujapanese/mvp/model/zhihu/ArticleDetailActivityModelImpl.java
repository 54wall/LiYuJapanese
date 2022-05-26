package pri.weiqiang.liyujapanese.mvp.model.zhihu;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import pri.weiqiang.liyujapanese.mvp.bean.zhihu.StoryContentEntity;
import pri.weiqiang.liyujapanese.network.zhihu.Networks;


public class ArticleDetailActivityModelImpl implements ArticleDetailActivityModel {


    @Override
    public void getContent(Consumer<StoryContentEntity> consumer, Consumer<Throwable> throwable, int id) {
        Networks.getInstance().getCommonApi().getStoryContent(id)//产生被观察者
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer, throwable);
    }
}
