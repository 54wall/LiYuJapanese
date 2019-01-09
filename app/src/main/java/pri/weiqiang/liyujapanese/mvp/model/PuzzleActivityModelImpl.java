package pri.weiqiang.liyujapanese.mvp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pri.weiqiang.liyujapanese.MyApplication;
import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.manager.DBManager;
import pri.weiqiang.liyujapanese.mvp.bean.GojuonItem;
import pri.weiqiang.liyujapanese.utils.ResourceUtils;

public class PuzzleActivityModelImpl implements BaseModel.PuzzleActivityModel {

    @Override
    public String[] getOptions() {
        return new String[]{
                ResourceUtils.getString(MyApplication.getInstance(), R.string.hiragana_rome),
                ResourceUtils.getString(MyApplication.getInstance(), R.string.hiragana_katakana),
                ResourceUtils.getString(MyApplication.getInstance(), R.string.katakana_rome)
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
