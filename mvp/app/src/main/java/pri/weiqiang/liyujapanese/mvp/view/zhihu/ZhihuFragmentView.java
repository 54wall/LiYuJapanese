package pri.weiqiang.liyujapanese.mvp.view.zhihu;

import pri.weiqiang.liyujapanese.mvp.bean.zhihu.BeforeDailyEntity;

public interface ZhihuFragmentView {
    <T> void refreshHomeList(T t);

    void loadBeforeDaily(BeforeDailyEntity beforeDailyEntity);
}
