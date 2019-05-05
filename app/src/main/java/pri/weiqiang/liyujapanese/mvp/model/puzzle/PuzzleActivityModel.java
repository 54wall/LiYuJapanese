package pri.weiqiang.liyujapanese.mvp.model.puzzle;

import java.util.List;

import pri.weiqiang.liyujapanese.mvp.bean.gojuon.GojuonItem;

public interface PuzzleActivityModel {
    String[] getOptions();

    List<GojuonItem> getItems();
}
