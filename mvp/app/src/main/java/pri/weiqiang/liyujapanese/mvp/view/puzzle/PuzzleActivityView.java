package pri.weiqiang.liyujapanese.mvp.view.puzzle;

import android.content.DialogInterface;

import java.util.List;

import pri.weiqiang.liyujapanese.mvp.bean.gojuon.GojuonItem;

public interface PuzzleActivityView {
    void setData(GojuonItem current, List<GojuonItem> jams);

    void showSelectDialog(String[] selection);

    void showResultDialog(int title, String msg, int icon,
                          int pbt, DialogInterface.OnClickListener pbl,
                          int nbt, DialogInterface.OnClickListener nbl);

    void showDialog(int icon, int title, String msg);

    void setTitle(String title);

    void setTitle(int title);

    void addCount();

    void clearCount();

    void showMsg(int msg);

    void showMsg(String msg);
}
