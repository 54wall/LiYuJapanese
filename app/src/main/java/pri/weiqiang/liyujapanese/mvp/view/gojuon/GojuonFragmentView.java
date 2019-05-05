package pri.weiqiang.liyujapanese.mvp.view.gojuon;

import pri.weiqiang.liyujapanese.mvp.bean.gojuon.GojuonItem;
import pri.weiqaing.common.base.mvp.BaseView;

public interface GojuonFragmentView extends BaseView<GojuonItem> {
    void setRecyclerView(int type);
}
