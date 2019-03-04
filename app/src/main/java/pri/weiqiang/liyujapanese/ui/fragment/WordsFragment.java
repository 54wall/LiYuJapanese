package pri.weiqiang.liyujapanese.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.animation.LayoutAnimationController;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.config.Constants;
import pri.weiqiang.liyujapanese.mvp.bean.Word;
import pri.weiqiang.liyujapanese.mvp.presenter.BasePresenter;
import pri.weiqiang.liyujapanese.mvp.presenter.WordsFragmentPresenterImpl;
import pri.weiqiang.liyujapanese.mvp.view.BaseView;
import pri.weiqiang.liyujapanese.ui.adapter.WordsRecyclerAdapter;
import pri.weiqiang.liyujapanese.utils.LayoutAnimationHelper;

/**
 * Created by weiqiang on 2018/3/16.
 */

public class WordsFragment extends BaseFragment implements BaseView.WordsFragmentView {

    private static final String TAG = WordsFragment.class.getSimpleName();
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.fab_more)
    FloatingActionButton mFabMore;
    BasePresenter.WordsFragmentPresenter presenter;
    WordsRecyclerAdapter adapter;
    String lesson;
    Boolean isExpandable;

    public static WordsFragment newInstance(String lesson, Boolean isExpandable) {

        Bundle argument = new Bundle();
        argument.putString(Constants.FLAG_LESSON, lesson);
        argument.putBoolean(Constants.FLAG_IS_EXPANDABLE, isExpandable);
        WordsFragment fragment = new WordsFragment();
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
        lesson = getArguments().getString(Constants.FLAG_LESSON);
        isExpandable = getArguments().getBoolean(Constants.FLAG_IS_EXPANDABLE);
        Log.e(TAG, "lesson:" + lesson);
        presenter = new WordsFragmentPresenterImpl(this, lesson);
        mSwipeRefresh.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.e(TAG, "onRefresh()");
                presenter.randomList();
                LayoutAnimationHelper.getInstance().playLayoutAnimation(mRecyclerView, LayoutAnimationHelper.getInstance().getAnimationSetFromLeft(), LayoutAnimationController.ORDER_RANDOM);
                mSwipeRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    protected void doAction() {
        presenter.initWordsFragment();
    }

    @Override
    public void setData(List<Word> data) {
        adapter = new WordsRecyclerAdapter(getContext(), data, isExpandable);
        mRecyclerView.setAdapter(adapter);
        LayoutAnimationHelper.getInstance().playLayoutAnimation(mRecyclerView, LayoutAnimationHelper.getInstance().getAnimationSetFromBottom(), LayoutAnimationController.ORDER_NORMAL);
        adapter.setOnItemClickListener(new WordsRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Word bean) {

            }
        });

    }

    @Override
    public void setRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy:presenter.unsubscribe();");
        super.onDestroy();
        // 将所有的 observer 取消订阅
        presenter.unsubscribe();
    }
}
