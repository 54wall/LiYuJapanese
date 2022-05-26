package pri.weiqiang.liyujapanese.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import butterknife.BindView;
import pri.weiqaing.common.base.BaseFragment;
import pri.weiqaing.common.config.Constants;
import pri.weiqaing.common.rxbus.RxBus;
import pri.weiqaing.common.rxbus.event.EventContainer;
import pri.weiqaing.common.rxbus.event.PhotoViewEvent;
import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.mvp.bean.pixivIllust.PixivIllustBean;
import pri.weiqiang.liyujapanese.mvp.presenter.pixivIllust.PixivIllustFragmentPresenter;
import pri.weiqiang.liyujapanese.mvp.presenter.pixivIllust.PixivIllustFragmentPresenterImpl;
import pri.weiqiang.liyujapanese.mvp.view.pixivIllust.PixivIllustFragmentView;
import pri.weiqiang.liyujapanese.ui.adapter.PixivIllustRecyclerAdapter;


public class PixivIllustFragment extends BaseFragment implements PixivIllustFragmentView {

    @BindView(R.id.layout_root)
    LinearLayout mRootLayout;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;
    String mode_illust;
    PixivIllustFragmentPresenter presenter;
    private String TAG = PixivIllustFragment.class.getSimpleName();

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
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

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

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy:presenter.unsubscribe();");
        super.onDestroy();
        // 将所有的 observer 取消订阅
        presenter.unsubscribe();
    }
}
