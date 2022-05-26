package pri.weiqiang.liyujapanese.mvp.model.zhihu;

import io.reactivex.functions.Consumer;
import pri.weiqiang.liyujapanese.mvp.bean.zhihu.StoryContentEntity;

public interface ArticleDetailActivityModel {
    void getContent(Consumer<StoryContentEntity> consumer, Consumer<Throwable> throwable, int id);
}
