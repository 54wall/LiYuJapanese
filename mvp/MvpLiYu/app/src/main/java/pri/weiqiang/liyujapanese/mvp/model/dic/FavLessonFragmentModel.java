package pri.weiqiang.liyujapanese.mvp.model.dic;

import java.util.List;

import io.reactivex.functions.Consumer;
import pri.weiqaing.common.base.mvp.BaseModel;
import pri.weiqiang.liyujapanese.mvp.bean.dic.LessonFav;

public interface FavLessonFragmentModel extends BaseModel {
    void getData(Consumer<List<LessonFav>> consumer, Consumer<Throwable> throwable);
}
