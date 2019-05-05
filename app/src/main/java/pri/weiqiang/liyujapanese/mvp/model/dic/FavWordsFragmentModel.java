package pri.weiqiang.liyujapanese.mvp.model.dic;

import java.util.List;

import io.reactivex.functions.Consumer;
import pri.weiqiang.liyujapanese.mvp.bean.dic.Word;
import pri.weiqaing.common.base.mvp.BaseModel;

public interface FavWordsFragmentModel extends BaseModel {
    void getData(Consumer<List<Word>> consumer, Consumer<Throwable> throwable, String lessonId);
}
