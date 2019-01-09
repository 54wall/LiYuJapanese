package pri.weiqiang.liyujapanese.comparator;

import java.util.Comparator;

import pri.weiqiang.liyujapanese.mvp.bean.GojuonItem;

public class GojuonItemComporator implements Comparator<GojuonItem> {

    @Override
    public int compare(GojuonItem o1, GojuonItem o2) {

        if (o1.getRow() < o2.getRow()) {
            return -1;
        } else if (o1.getRow() == o2.getRow()) {

            if (o1.getColumn() <= o2.getColumn()) {
                return -1;
            } else
                return 1;

        } else {
            return 1;
        }
    }
}
