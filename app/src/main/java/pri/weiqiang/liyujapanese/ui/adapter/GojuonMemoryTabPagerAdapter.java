package pri.weiqiang.liyujapanese.ui.adapter;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import pri.weiqiang.liyujapanese.mvp.bean.gojuon.GojuonTab;
import pri.weiqiang.liyujapanese.ui.fragment.GojuonMemoryFragment;


public class GojuonMemoryTabPagerAdapter extends BasePagerAdapter<GojuonTab> {

    public GojuonMemoryTabPagerAdapter(FragmentManager fm, List<GojuonTab> list) {
        super(fm, list);
    }

    @Override
    protected Fragment getFragment(GojuonTab gojuonTab) {
        return GojuonMemoryFragment.newInstance(gojuonTab.getType());
    }

    @Override
    protected CharSequence getTitle(GojuonTab gojuonTab) {
        return gojuonTab.getTitle();
    }
}
