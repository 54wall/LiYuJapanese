package pri.weiqiang.liyujapanese.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import butterknife.BindView;
import pri.weiqaing.common.base.BaseFragment;
import pri.weiqaing.common.base.mvp.BasePresenter;
import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.mvp.bean.pixivIllust.PixivIllustTab;
import pri.weiqiang.liyujapanese.mvp.presenter.pixivIllust.PixivIllustTabFragmentPresenterImpl;
import pri.weiqiang.liyujapanese.mvp.view.pixivIllust.PixivIllustTabFragmentView;
import pri.weiqiang.liyujapanese.ui.adapter.PixivIllustTabPagerAdapter;


public class PixivIllustTabFragment extends BaseFragment implements PixivIllustTabFragmentView {

    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    BasePresenter.PixivIllustTabFragmentPresenter presenter;


    @Override
    protected int getViewId() {
        return R.layout.fragment_pixivillust_tab;
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {

        presenter = new PixivIllustTabFragmentPresenterImpl(this);

        initTabLayout();
        initViewPager();

    }

    private void initTabLayout() {
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initViewPager() {
        // doo something
    }


    @Override
    protected void doAction() {

        presenter.initPixivIllustTabFragment();

        Log.i(TAG, "doAction: OK");

    }

    @Override
    public void setData(List<PixivIllustTab> data) {
        mViewPager.setAdapter(new PixivIllustTabPagerAdapter(getChildFragmentManager(), data));
    }

    @Override
    public void showAlertDialog(int titleId, int messageId, int positiveTextId, DialogInterface.OnClickListener positiveButtonListener, int negativeTextId, DialogInterface.OnClickListener negativeButtonListener) {
        new AlertDialog.Builder(getContext())
                .setTitle(titleId)
                .setMessage(messageId)
                .setCancelable(false)
                .setPositiveButton(positiveTextId, positiveButtonListener)
                .setNegativeButton(negativeTextId, negativeButtonListener)
                .setIcon(R.drawable.ic_lightbulb_outline)
                .create()
                .show();
    }

    @Override
    public void showMsg(String msg) {
        showSnackBar(mTabLayout, msg);
    }

    @Override
    public void showMsg(int msg) {
        showSnackBar(mTabLayout, msg);
    }


}
