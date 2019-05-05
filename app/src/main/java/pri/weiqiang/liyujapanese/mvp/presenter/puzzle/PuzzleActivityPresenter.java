package pri.weiqiang.liyujapanese.mvp.presenter.puzzle;

import java.util.List;

import pri.weiqiang.liyujapanese.mvp.bean.gojuon.GojuonItem;

public interface PuzzleActivityPresenter {
    void initPuzzleActivity();

    void loadData();

    void checkTypeSelect(int which);

    void checkAnswerSelect(int id, GojuonItem current, List<GojuonItem> items);

    void checkMenuSelect(int id);
}
