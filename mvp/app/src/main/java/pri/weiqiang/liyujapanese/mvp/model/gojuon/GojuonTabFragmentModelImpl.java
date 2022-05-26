package pri.weiqiang.liyujapanese.mvp.model.gojuon;

import java.util.ArrayList;
import java.util.List;

import pri.weiqaing.common.base.BaseApplication;
import pri.weiqaing.common.config.Constants;
import pri.weiqaing.common.utils.ResourceUtils;
import pri.weiqiang.liyujapanese.mvp.bean.gojuon.GojuonTab;

public class GojuonTabFragmentModelImpl implements GojuonTabFragmentModel {

    @Override
    public void unsubscribe() {

    }

    @Override
    public List<GojuonTab> getData() {

        List<GojuonTab> list = new ArrayList<>();
        String qingyin = ResourceUtils.getString(BaseApplication.getInstance(), pri.weiqiang.liyujapanese.R.string.qingyin);
        String zhuoyin = ResourceUtils.getString(BaseApplication.getInstance(), pri.weiqiang.liyujapanese.R.string.zhuoyin);
        String aoyin = ResourceUtils.getString(BaseApplication.getInstance(), pri.weiqiang.liyujapanese.R.string.aoyin);

        list.add(new GojuonTab(Constants.CATEGORY_QINGYIN, qingyin));
        list.add(new GojuonTab(Constants.CATEGORY_ZHUOYIN, zhuoyin));
        list.add(new GojuonTab(Constants.CATEGORY_AOYIN, aoyin));

        return list;
    }
}
