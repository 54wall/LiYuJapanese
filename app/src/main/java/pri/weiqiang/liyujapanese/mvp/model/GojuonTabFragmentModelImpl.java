package pri.weiqiang.liyujapanese.mvp.model;

import java.util.ArrayList;
import java.util.List;

import pri.weiqiang.liyujapanese.MyApplication;
import pri.weiqiang.liyujapanese.config.Constants;
import pri.weiqiang.liyujapanese.mvp.bean.GojuonTab;
import pri.weiqiang.liyujapanese.utils.ResourceUtils;

public class GojuonTabFragmentModelImpl implements BaseModel.GojuonTabFragmentModel {

    @Override
    public List<GojuonTab> getData() {

        List<GojuonTab> list = new ArrayList<>();
        String qingyin = ResourceUtils.getString(MyApplication.getInstance(), pri.weiqiang.liyujapanese.R.string.qingyin);
        String zhuoyin = ResourceUtils.getString(MyApplication.getInstance(), pri.weiqiang.liyujapanese.R.string.zhuoyin);
        String aoyin = ResourceUtils.getString(MyApplication.getInstance(), pri.weiqiang.liyujapanese.R.string.aoyin);

        list.add(new GojuonTab(Constants.CATEGORY_QINGYIN, qingyin));
        list.add(new GojuonTab(Constants.CATEGORY_ZHUOYIN, zhuoyin));
        list.add(new GojuonTab(Constants.CATEGORY_AOYIN, aoyin));

        return list;
    }
}
