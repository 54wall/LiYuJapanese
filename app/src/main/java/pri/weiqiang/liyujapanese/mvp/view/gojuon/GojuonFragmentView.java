package pri.weiqiang.liyujapanese.mvp.view.gojuon;

import pri.weiqaing.common.base.mvp.BaseView;
import pri.weiqiang.liyujapanese.mvp.bean.gojuon.GojuonItem;

public interface GojuonFragmentView extends BaseView<GojuonItem> {
    void setRecyclerView(int type);
}
