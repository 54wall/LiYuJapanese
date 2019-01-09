package pri.weiqiang.liyujapanese.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;


public abstract class BasePagerAdapter<T> extends FragmentPagerAdapter {

    List<T> list;

    public BasePagerAdapter(FragmentManager fm, List<T> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return getFragment(list.get(position));
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return getTitle(list.get(position));
    }

    protected abstract Fragment getFragment(T t);

    protected abstract CharSequence getTitle(T t);
}
