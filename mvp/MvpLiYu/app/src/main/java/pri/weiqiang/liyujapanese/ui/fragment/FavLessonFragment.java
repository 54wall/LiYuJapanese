package pri.weiqiang.liyujapanese.ui.fragment;

import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import pri.weiqaing.common.base.BaseFragment;
import pri.weiqaing.common.base.mvp.BasePresenter;
import pri.weiqaing.common.config.Constants;
import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.mvp.bean.dic.LessonFav;
import pri.weiqiang.liyujapanese.mvp.presenter.dic.FavLessonFragmentPresenterImpl;
import pri.weiqiang.liyujapanese.mvp.view.dic.FavLessonFragmentView;
import pri.weiqiang.liyujapanese.ui.activity.MainActivity;
import pri.weiqiang.liyujapanese.ui.adapter.StaggeredGridAdapter;
import pri.weiqiang.liyujapanese.ui.adapter.stagger.OnItemClickLitener;

/**
 * Created by weiqiang on 2018/10/8.
 */

public class FavLessonFragment extends BaseFragment implements FavLessonFragmentView {

    private static final String TAG = FavLessonFragment.class.getSimpleName();
    StaggeredGridAdapter staggeredGridAdapter;
    BasePresenter.FavLessonFragmentPresenter presenter;
    String lesson;
    @BindView(R.id.rv_lesson)
    RecyclerView mRecyclerView;
    private List<LessonFav> lessonFavList;//瀑布流交换位置时使用
    ItemTouchHelper.Callback callback = new ItemTouchHelper.Callback() {

        /**这个方法是用来设置我们拖动的方向以及侧滑的方向的*/
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            //设置拖拽方向为上下左右
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                    ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            //设置侧滑方向为从左到右和从右到左都可以
            final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
            //将方向参数设置进去
            return makeMovementFlags(dragFlags, swipeFlags);
        }

        /**
         * @param recyclerView
         * @param viewHolder 拖动的ViewHolder
         * @param target 目标位置的ViewHolder
         * @return
         */
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            int fromPosition = viewHolder.getAdapterPosition();//得到拖动ViewHolder的position
            int toPosition = target.getAdapterPosition();//得到目标ViewHolder的position
            if (fromPosition < toPosition) {
                //分别把中间所有的item的位置重新交换
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(lessonFavList, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(lessonFavList, i, i - 1);
                }
            }
            staggeredGridAdapter.notifyItemMoved(fromPosition, toPosition);
            //返回true表示执行拖动
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            staggeredGridAdapter.notifyItemRemoved(position);
        }

        @Override
        public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                //滑动时改变Item的透明度
                final float alpha = 1 - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
                viewHolder.itemView.setAlpha(alpha);
                viewHolder.itemView.setTranslationX(dX);
            }
        }

    };

    public static FavLessonFragment newInstance(Boolean isExpandable) {

        Bundle argument = new Bundle();
        argument.putBoolean(Constants.FLAG_IS_EXPANDABLE, isExpandable);
        FavLessonFragment fragment = new FavLessonFragment();
        fragment.setArguments(argument);

        return fragment;

    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_fav_lesson;
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        Log.e(TAG, "lesson:" + lesson);
        presenter = new FavLessonFragmentPresenterImpl(this);
    }

    @Override
    protected void doAction() {
        presenter.initFavLessonFragment();
    }

    @Override
    public void setData(List<LessonFav> data) {
        Log.e(TAG, "setData!!!!!!");
        lessonFavList = data;
        staggeredGridAdapter = new StaggeredGridAdapter(getActivity(), data);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(staggeredGridAdapter);
        staggeredGridAdapter.setOnItemClickLitener(new OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                staggeredGridAdapter.notifyItemRemoved(position);

                ((MainActivity) getActivity()).switchFavWord(staggeredGridAdapter.getItemLessonId(position), false);
            }

            @Override
            public void onItemLongClick(View view, final int position) {

            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void setRecyclerView() {
        Log.e(TAG, "setRecyclerView!!!!!!");
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy:presenter.unsubscribe();");
        super.onDestroy();
        // 将所有的 observer 取消订阅
        presenter.unsubscribe();
    }
}
