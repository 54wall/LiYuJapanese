package pri.weiqiang.liyujapanese.mvp.model.gojuon;

import java.util.List;

import io.reactivex.functions.Consumer;
import pri.weiqaing.common.base.mvp.BaseModel;
import pri.weiqiang.liyujapanese.mvp.bean.gojuon.GojuonItem;

public interface GojuonFragmentModel extends BaseModel {
    void getData(int category, Consumer<List<GojuonItem>> consumer);
}
