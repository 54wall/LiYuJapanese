package pri.weiqiang.liyujapanese.mvp.presenter;

import pri.weiqiang.liyujapanese.MyApplication;
import pri.weiqiang.liyujapanese.mvp.model.BaseModel;
import pri.weiqiang.liyujapanese.mvp.model.GojuonTabFragmentModelImpl;
import pri.weiqiang.liyujapanese.mvp.view.BaseView;

public class GojuonTabFragmentPresenterImpl extends BasePresenter<BaseView.GojuonTabFragmentView> implements BasePresenter.GojuonTabFragmentPresenter {

    BaseModel.GojuonTabFragmentModel model;

    public GojuonTabFragmentPresenterImpl(BaseView.GojuonTabFragmentView view) {
        super(view);
        model = new GojuonTabFragmentModelImpl();
    }

    @Override
    public void initGojuonTabFragment() {
        view.setData(model.getData());
        view.scrollViewPager(MyApplication.CURRENT_ITEM);
    }
}
