package pri.weiqiang.liyujapanese.mvp.model;

import java.util.ArrayList;
import java.util.List;

import pri.weiqiang.liyujapanese.MyApplication;
import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.config.Constants;
import pri.weiqiang.liyujapanese.mvp.bean.GojuonTab;
import pri.weiqiang.liyujapanese.utils.ResourceUtils;

public class GojuonMemoryTabFragmentModelImpl implements BaseModel.GojuonMemoryTabFragmentModel {

    @Override
    public void unsubscribe() {

    }

    @Override
    public List<GojuonTab> getData() {

        List<GojuonTab> list = new ArrayList<>();

        String memory = ResourceUtils.getString(MyApplication.getInstance(), R.string.gojun_chengyu);
        String chengyu = ResourceUtils.getString(MyApplication.getInstance(), R.string.gojun_memory);

        list.add(new GojuonTab(Constants.GOJUON_MEMORY, memory));
        list.add(new GojuonTab(Constants.GOJUON_CHENGYU, chengyu));

        return list;
    }
}
