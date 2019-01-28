package pri.weiqiang.liyujapanese.ui.adapter;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import pri.weiqiang.liyujapanese.mvp.bean.BannerItem;
import pri.weiqiang.liyujapanese.ui.fragment.BannerFragment;

public class BannerPagerAdapter extends BasePagerAdapter<BannerItem> {

    public BannerPagerAdapter(FragmentManager fm, List<BannerItem> list) {
        super(fm, list);
    }

    @Override
    protected Fragment getFragment(BannerItem bannerItem) {
        return BannerFragment.newInstance(bannerItem);
    }

    @Override
    protected CharSequence getTitle(BannerItem bannerItem) {
        return bannerItem.getTitle();
    }
}
