package pri.weiqiang.liyujapanese.ui.fragment;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import pri.weiqiang.liyujapanese.MyApplication;
import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.config.Constants;
import pri.weiqiang.liyujapanese.manager.GifManager;
import pri.weiqiang.liyujapanese.manager.SoundPoolManager;
import pri.weiqiang.liyujapanese.mvp.bean.GojuonGif;
import pri.weiqiang.liyujapanese.mvp.bean.GojuonMemory;
import pri.weiqiang.liyujapanese.mvp.presenter.BasePresenter;
import pri.weiqiang.liyujapanese.mvp.presenter.GojuonMemoryFragmentPresenterImpl;
import pri.weiqiang.liyujapanese.mvp.view.BaseView;
import pri.weiqiang.liyujapanese.ui.adapter.GojuonMemoryRecyclerAdapter;
import pri.weiqiang.liyujapanese.utils.ResourceUtils;
import pri.weiqiang.liyujapanese.widget.dialog.ImageDialog;

public class GojuonMemoryFragment extends BaseFragment implements BaseView.GojuonMemoryFragmentView {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    BasePresenter.GojuonMemoryFragmentPresenter presenter;
    GojuonMemoryRecyclerAdapter adapter;
    int category_gojuon_memory = 0;
    private String TAG = GojuonMemoryFragment.class.getSimpleName();

    public static GojuonMemoryFragment newInstance(int type) {

        Bundle argument = new Bundle();
        argument.putInt(Constants.CATEGORY_GOJUON_MEMORY, type);

        GojuonMemoryFragment fragment = new GojuonMemoryFragment();
        fragment.setArguments(argument);

        return fragment;

    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_gojuon;
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {

        category_gojuon_memory = getArguments().getInt(Constants.CATEGORY_GOJUON_MEMORY);

        presenter = new GojuonMemoryFragmentPresenterImpl(this);

    }


    @Override
    protected void doAction() {
        presenter.initGojuonMemoryFragment(category_gojuon_memory);
    }

    @Override
    public void setData(List<GojuonMemory> data) {
        Log.e(TAG, "setData!!");
        adapter = new GojuonMemoryRecyclerAdapter(category_gojuon_memory, data);
        adapter.setOnItemClickListener(new GojuonMemoryRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onClick(GojuonMemory bean) {
                Log.e(TAG, "onItemClick bean:" + bean.getRome() + "：" + bean.getKatakana());
                SoundPoolManager.getInstance().play(bean.getRome());
            }

        });


        adapter.setOnItemLongClickListener(new GojuonMemoryRecyclerAdapter.OnItemLongClickListener() {
            @Override
            public void onLongClick(GojuonMemory item) {
                Log.e(TAG, "onLongClick item:" + item.getRome() + ":" + item.getKatakana());
                GojuonGif gif = GifManager.getInstance().getJPGif(item.getRome());
                if (gif != null) {
                    if (MyApplication.TYPE_MING == Constants.TYPE_HIRAGANA) {
                        new ImageDialog.Builder(getContext())
                                .setResId(gif.getHiragana())
                                .override((int) ResourceUtils.getDimension(getContext(), R.dimen.dialog_width),
                                        (int) ResourceUtils.getDimension(getContext(), R.dimen.dialog_height))
                                .create()
                                .show();
                    } else {
                        new ImageDialog.Builder(getContext())
                                .setResId(gif.getKatakana())
                                .override((int) ResourceUtils.getDimension(getContext(), R.dimen.dialog_width),
                                        (int) ResourceUtils.getDimension(getContext(), R.dimen.dialog_height))
                                .create()
                                .show();
                    }
                }


            }
        });

        mRecyclerView.setAdapter(adapter);

    }

    @Override
    public void setRecyclerView(int type) {

        switch (type) {
            case Constants.GOJUON_CHENGYU:
                RecyclerView.LayoutManager layoutManager2 = new GridLayoutManager(getContext(), Constants.COLUMN_CHENGYU);
                mRecyclerView.setLayoutManager(layoutManager2);
                break;
            case Constants.GOJUON_MEMORY:
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(linearLayoutManager);
                break;
            default:
                break;
        }


    }
}
