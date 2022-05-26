package pri.weiqiang.liyujapanese.mvp.model.memory;

import java.util.List;

import pri.weiqiang.liyujapanese.mvp.bean.gojuon.GojuonItem;

public interface MemoryFragmentModel {
    List<GojuonItem> getQingYinWithoutHeader();

    List<GojuonItem> getZhuoYinWithoutHeader();

    List<GojuonItem> getAoYinWithoutHeader();
}
