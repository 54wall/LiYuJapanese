package pri.weiqiang.liyujapanese.mvp.view.memory;

import pri.weiqaing.common.base.mvp.BaseView;
import pri.weiqiang.liyujapanese.mvp.bean.gojuon.GojuonItem;

public interface MemoryFragmentView extends BaseView<GojuonItem> {
    void showMsg(int msg);

    void showMsg(String msg);

    void hideFabMenu();
}
