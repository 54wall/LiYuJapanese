package pri.weiqiang.liyujapanese.mvp.model.zhihu;

import io.reactivex.functions.Consumer;
import pri.weiqiang.liyujapanese.mvp.bean.zhihu.BeforeDailyEntity;
import pri.weiqiang.liyujapanese.mvp.bean.zhihu.LatestDailyEntity;
import pri.weiqaing.common.base.mvp.BaseModel;

public interface ZhihuFragmentModel extends BaseModel {
    void getLatestDaily(Consumer<LatestDailyEntity> consumer, Consumer<Throwable> throwable);

    void getBeforeDaily(Consumer<BeforeDailyEntity> consumer, Consumer<Throwable> throwable, String date);
}
