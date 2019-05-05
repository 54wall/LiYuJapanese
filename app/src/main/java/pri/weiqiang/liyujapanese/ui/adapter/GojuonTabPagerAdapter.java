package pri.weiqiang.liyujapanese.ui.adapter;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import pri.weiqiang.liyujapanese.mvp.bean.gojuon.GojuonTab;
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
