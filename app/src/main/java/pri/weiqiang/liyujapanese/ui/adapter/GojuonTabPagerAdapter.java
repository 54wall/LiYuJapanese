package pri.weiqiang.liyujapanese.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

import pri.weiqiang.liyujapanese.mvp.bean.GojuonTab;
import pri.weiqiang.liyujapanese.ui.fragment.GojuonFragment;


public class GojuonTabPagerAdapter extends BasePagerAdapter<GojuonTab> {

    public GojuonTabPagerAdapter(FragmentManager fm, List<GojuonTab> list) {
        super(fm, list);
    }

    @Override
    protected Fragment getFragment(GojuonTab gojuonTab) {
        return GojuonFragment.newInstance(gojuonTab.getType());
    }

    @Override
    protected CharSequence getTitle(GojuonTab gojuonTab) {
        return gojuonTab.getTitle();
    }
}
