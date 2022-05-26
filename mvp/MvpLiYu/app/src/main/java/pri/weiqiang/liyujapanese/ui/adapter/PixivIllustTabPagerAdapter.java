package pri.weiqiang.liyujapanese.ui.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.List;

import pri.weiqiang.liyujapanese.mvp.bean.pixivIllust.PixivIllustTab;
import pri.weiqiang.liyujapanese.ui.fragment.PixivIllustFragment;


public class PixivIllustTabPagerAdapter extends BasePagerAdapter<PixivIllustTab> {

    public PixivIllustTabPagerAdapter(FragmentManager fm, List<PixivIllustTab> list) {
        super(fm, list);
    }

    @Override
    protected Fragment getFragment(PixivIllustTab pixivIllustTab) {
        return PixivIllustFragment.newInstance(pixivIllustTab.getMode());
    }

    @Override
    protected CharSequence getTitle(PixivIllustTab pixivIllustTab) {
        return pixivIllustTab.getTitle();
    }
}
