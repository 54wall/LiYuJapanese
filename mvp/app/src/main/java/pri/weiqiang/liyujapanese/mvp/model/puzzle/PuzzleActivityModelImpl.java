package pri.weiqiang.liyujapanese.mvp.model.puzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pri.weiqaing.common.base.BaseApplication;
import pri.weiqaing.common.utils.ResourceUtils;
import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.manager.DBManager;
import pri.weiqiang.liyujapanese.mvp.bean.gojuon.GojuonItem;

public class PuzzleActivityModelImpl implements PuzzleActivityModel {

    @Override
    public String[] getOptions() {
        return new String[]{
                ResourceUtils.getString(BaseApplication.getInstance(), R.string.hiragana_rome),
                ResourceUtils.getString(BaseApplication.getInstance(), R.string.hiragana_katakana),
                ResourceUtils.getString(BaseApplication.getInstance(), R.string.katakana_rome)
        };
    }

    @Override
    public List<GojuonItem> getItems() {

        List<GojuonItem> items = new ArrayList<>();
        items.addAll(DBManager.getInstance().getQingYinWithoutHeader());

        Collections.shuffle(items);

        return items;
    }
}
