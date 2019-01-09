package pri.weiqiang.liyujapanese.mvp.presenter;

import pri.weiqiang.liyujapanese.MyApplication;
import pri.weiqiang.liyujapanese.mvp.model.BaseModel;
import pri.weiqiang.liyujapanese.mvp.model.GojuonMemoryTabFragmentModelImpl;
import pri.weiqiang.liyujapanese.mvp.view.BaseView;

public class GojuonMemoryTabFragmentPresenterImpl extends BasePresenter<BaseView.GojuonMemoryTabFragmentView> implements BasePresenter.GojuonMemoryTabFragmentPresenter {

    BaseModel.GojuonMemoryTabFragmentModel model;

    public GojuonMemoryTabFragmentPresenterImpl(BaseView.GojuonMemoryTabFragmentView view) {
        super(view);
        model = new GojuonMemoryTabFragmentModelImpl();
    }

    @Override
    public void initGojuonMemoryTabFragment() {
        view.setData(model.getData());
        view.scrollViewPager(MyApplication.CURRENT_ITEM);
    }
}
