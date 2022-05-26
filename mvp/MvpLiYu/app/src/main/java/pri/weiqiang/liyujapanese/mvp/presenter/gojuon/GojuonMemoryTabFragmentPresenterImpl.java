package pri.weiqiang.liyujapanese.mvp.presenter.gojuon;

import pri.weiqaing.common.base.BaseApplication;
import pri.weiqaing.common.base.mvp.BasePresenter;
import pri.weiqiang.liyujapanese.mvp.model.gojuon.GojuonMemoryTabFragmentModel;
import pri.weiqiang.liyujapanese.mvp.model.gojuon.GojuonMemoryTabFragmentModelImpl;
import pri.weiqiang.liyujapanese.mvp.view.gojuon.GojuonMemoryTabFragmentView;

public class GojuonMemoryTabFragmentPresenterImpl extends BasePresenter<GojuonMemoryTabFragmentView> implements BasePresenter.GojuonMemoryTabFragmentPresenter {

    GojuonMemoryTabFragmentModel model;

    public GojuonMemoryTabFragmentPresenterImpl(GojuonMemoryTabFragmentView view) {
        super(view);
        model = new GojuonMemoryTabFragmentModelImpl();
    }

    @Override
    public void initGojuonMemoryTabFragment() {
        view.setData(model.getData());
        view.scrollViewPager(BaseApplication.CURRENT_ITEM);
    }
}
