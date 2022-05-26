package pri.weiqiang.liyujapanese.mvp.presenter.gojuon;

import pri.weiqaing.common.base.BaseApplication;
import pri.weiqaing.common.base.mvp.BasePresenter;
import pri.weiqiang.liyujapanese.mvp.model.gojuon.GojuonTabFragmentModel;
import pri.weiqiang.liyujapanese.mvp.model.gojuon.GojuonTabFragmentModelImpl;
import pri.weiqiang.liyujapanese.mvp.view.gojuon.GojuonTabFragmentView;

public class GojuonTabFragmentPresenterImpl extends BasePresenter<GojuonTabFragmentView> implements BasePresenter.GojuonTabFragmentPresenter {

    GojuonTabFragmentModel model;

    public GojuonTabFragmentPresenterImpl(GojuonTabFragmentView view) {
        super(view);
        model = new GojuonTabFragmentModelImpl();
    }

    @Override
    public void initGojuonTabFragment() {
        view.setData(model.getData());
        view.scrollViewPager(BaseApplication.CURRENT_ITEM);
    }
}
