package pri.weiqiang.liyujapanese.mvp.presenter.gojuon;

import android.util.Log;

import java.util.List;

import io.reactivex.functions.Consumer;
import pri.weiqaing.common.base.mvp.BasePresenter;
import pri.weiqiang.liyujapanese.mvp.bean.gojuon.GojuonMemory;
import pri.weiqiang.liyujapanese.mvp.model.gojuon.GojuonMemoryFragmentModel;
import pri.weiqiang.liyujapanese.mvp.model.gojuon.GojuonMemoryFragmentModelImpl;
import pri.weiqiang.liyujapanese.mvp.view.gojuon.GojuonMemoryFragmentView;


public class GojuonMemoryFragmentPresenterImpl extends BasePresenter<GojuonMemoryFragmentView> implements BasePresenter.GojuonMemoryFragmentPresenter {

    GojuonMemoryFragmentModel model;
    private String TAG = GojuonFragmentPresenterImpl.class.getSimpleName();

    public GojuonMemoryFragmentPresenterImpl(GojuonMemoryFragmentView view) {
        super(view);
        model = new GojuonMemoryFragmentModelImpl();
    }


    @Override
    public void initGojuonMemoryFragment(int type) {
        view.setRecyclerView(type);
        model.getData(type, new Consumer<List<GojuonMemory>>() {
            @Override
            public void accept(List<GojuonMemory> gojuonMemories) throws Exception {
                view.setData(gojuonMemories);
            }
        });
    }

    @Override
    public void unsubscribe() {
        Log.e(TAG, "unsubscribe()");
        model.unsubscribe();
    }
}
