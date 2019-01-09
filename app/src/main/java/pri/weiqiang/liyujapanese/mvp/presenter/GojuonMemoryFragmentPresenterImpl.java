package pri.weiqiang.liyujapanese.mvp.presenter;

import java.util.List;

import io.reactivex.functions.Consumer;
import pri.weiqiang.liyujapanese.mvp.bean.GojuonMemory;
import pri.weiqiang.liyujapanese.mvp.model.BaseModel;
import pri.weiqiang.liyujapanese.mvp.model.GojuonMemoryFragmentModelImpl;
import pri.weiqiang.liyujapanese.mvp.view.BaseView;


public class GojuonMemoryFragmentPresenterImpl extends BasePresenter<BaseView.GojuonMemoryFragmentView> implements BasePresenter.GojuonMemoryFragmentPresenter {

    BaseModel.GojuonMemoryFragmentModel model;

    public GojuonMemoryFragmentPresenterImpl(BaseView.GojuonMemoryFragmentView view) {
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
}
