package pri.weiqiang.liyujapanese.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.animation.LayoutAnimationController;

import java.util.List;

import butterknife.BindView;
import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.config.Constants;
import pri.weiqiang.liyujapanese.mvp.bean.Word;
import pri.weiqiang.liyujapanese.mvp.presenter.BasePresenter;
import pri.weiqiang.liyujapanese.mvp.presenter.FavWordsFragmentPresenterImpl;
import pri.weiqiang.liyujapanese.mvp.view.BaseView;
import pri.weiqiang.liyujapanese.ui.adapter.FavWordsRecyclerAdapter;
import pri.weiqiang.liyujapanese.utils.LayoutAnimationHelper;

/**
 * Created by weiqiang on 2018/3/25.
 */

public class FavWordsFragment extends BaseFragment implements BaseView.FavWordsFragmentView {

    private static final String TAG = FavWordsFragment.class.getSimpleName();
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.fab_more)
    FloatingActionButton mFabMore;
    BasePresenter.FavWordsFragmentPresenter presenter;
    FavWordsRecyclerAdapter adapter;
    String lesson;
    Boolean isExpandable;

    public static FavWordsFragment newInstance(String lessonId, Boolean isExpandable) {

        Bundle argument = new Bundle();
        argument.putBoolean(Constants.FLAG_IS_EXPANDABLE, isExpandable);
        argument.putString(Constants.DEFAULT_LESSON, lessonId);
        FavWordsFragment fragment = new FavWordsFragment();
        fragment.setArguments(argument);
        return fragment;

    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_words;
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        lesson = getArguments().getString(Constants.DEFAULT_LESSON);
        isExpandable = getArguments().getBoolean(Constants.FLAG_IS_EXPANDABLE);
        Log.e(TAG, "lesson:" + lesson);
        presenter = new FavWordsFragmentPresenterImpl(this, lesson);
        mSwipeRefresh.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.e(TAG, "onRefresh()");
                presenter.randomList();
                //playLayoutAnimation 增加方法 则会报警告static member accessed via instance reference
                //Shows references to static methods and fields via class instance rather than a  class itself
                //大意为，直接通过静态方法获得的方法而不是通过实例获得的方法，这样单例模式便失效了。
                LayoutAnimationHelper.getInstance().playLayoutAnimation(mRecyclerView, LayoutAnimationHelper.getInstance().getAnimationSetFromLeft(), LayoutAnimationController.ORDER_RANDOM);
                mSwipeRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    protected void doAction() {
        presenter.initFavWordsFragment();
    }

    @Override
    public void setData(List<Word> data) {
        adapter = new FavWordsRecyclerAdapter(getContext(), data, isExpandable);
        mRecyclerView.setAdapter(adapter);
        LayoutAnimationHelper.getInstance().playLayoutAnimation(mRecyclerView, LayoutAnimationHelper.getInstance().getAnimationSetFromBottom(), LayoutAnimationController.ORDER_NORMAL);
        adapter.setOnItemClickListener(new FavWordsRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Word bean) {

            }
        });
    }

    @Override
    public void setRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    public void setExpandable(boolean isExpandable) {
        this.isExpandable = isExpandable;
        adapter.setIsExpandable(isExpandable);
        adapter.notifyDataSetChanged();
    }
}
