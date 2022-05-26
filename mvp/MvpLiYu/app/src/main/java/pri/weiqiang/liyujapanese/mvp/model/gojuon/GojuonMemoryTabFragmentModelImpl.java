package pri.weiqiang.liyujapanese.mvp.model.gojuon;

import java.util.ArrayList;
import java.util.List;

import pri.weiqaing.common.base.BaseApplication;
import pri.weiqaing.common.config.Constants;
import pri.weiqaing.common.utils.ResourceUtils;
import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.mvp.bean.gojuon.GojuonTab;

public class GojuonMemoryTabFragmentModelImpl implements GojuonMemoryTabFragmentModel {

    @Override
    public void unsubscribe() {

    }

    @Override
    public List<GojuonTab> getData() {

        List<GojuonTab> list = new ArrayList<>();

        String memory = ResourceUtils.getString(BaseApplication.getInstance(), R.string.gojun_chengyu);
        String chengyu = ResourceUtils.getString(BaseApplication.getInstance(), R.string.gojun_memory);

        list.add(new GojuonTab(Constants.GOJUON_MEMORY, memory));
        list.add(new GojuonTab(Constants.GOJUON_CHENGYU, chengyu));

        return list;
    }
}
