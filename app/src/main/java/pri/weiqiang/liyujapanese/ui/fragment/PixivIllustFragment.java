package pri.weiqiang.liyujapanese.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.List;

import butterknife.BindView;
import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.config.Constants;
import pri.weiqiang.liyujapanese.mvp.bean.PixivIllustBean;
import pri.weiqiang.liyujapanese.mvp.presenter.BasePresenter;
import pri.weiqiang.liyujapanese.mvp.presenter.PixivIllustFragmentPresenterImpl;
import pri.weiqiang.liyujapanese.mvp.view.BaseView;
import pri.weiqiang.liyujapanese.rxbus.RxBus;
import pri.weiqiang.liyujapanese.rxbus.event.EventContainer;
import pri.weiqiang.liyujapanese.rxbus.event.PhotoViewEvent;
import pri.weiqiang.liyujapanese.ui.adapter.PixivIllustRecyclerAdapter;


public class PixivIllustFragment extends BaseFragment implements BaseView.PixivIllustFragmentView {


    @BindView(R.id.layout_root)
    LinearLayout mRootLayout;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;

    String mode_illust;

    BasePresenter.PixivIllustFragmentPresenter presenter;

    public static PixivIllustFragment newInstance(String mode) {

        Bundle arguments = new Bundle();
        arguments.putString(Constants.MODE_ILLUST, mode);

        PixivIllustFragment fragment = new PixivIllustFragment();
        fragment.setArguments(arguments);
        return fragment;

    }


    @Override
    protected int getViewId() {
        return R.layout.fragment_pixivillust;
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {

        mode_illust = getArguments().getString(Constants.MODE_ILLUST);

        presenter = new PixivIllustFragmentPresenterImpl(this);

        initRecyclerView();
        initRefreshLayout();

    }

    private void initRecyclerView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(linearLayoutManager);

    }


    private void initRefreshLayout() {

        mRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.blue, R.color.orange, R.color.amber, R.color.green, R.color.purple);

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.reloadData(mode_illust);
            }
        });

    }


    @Override
    protected void doAction() {
        presenter.initPixivIllustFragment(mode_illust);
    }

    @Override
    public void showMsg(int msg) {
        showSnackBar(mRootLayout, msg);
    }

    @Override
    public void showMsg(String msg) {
        showSnackBar(mRootLayout, msg);
    }

    @Override
    public void showProgress() {
        mRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showOptionsDialog(String[] options, DialogInterface.OnClickListener listener) {

        new AlertDialog.Builder(getContext()).setItems(options, listener).create().show();

    }

    @Override
    public void showImg(String url, int id) {

        PhotoViewEvent event = new PhotoViewEvent(url, id);
        RxBus.getDefault().post(new EventContainer(EventContainer.TYPE_PHOTOVIEW, event));


    }


    @Override
    public void setData(List<PixivIllustBean> data) {

        PixivIllustRecyclerAdapter adapter = new PixivIllustRecyclerAdapter(getContext(), data);
        adapter.setOnItemClickListener(new PixivIllustRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PixivIllustBean bean) {
                presenter.onItemClick(bean);
            }
        });

        mRecyclerView.setAdapter(adapter);
        mRecyclerView.refreshDrawableState();

    }

}
