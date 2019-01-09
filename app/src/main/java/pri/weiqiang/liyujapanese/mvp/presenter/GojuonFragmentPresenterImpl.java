package pri.weiqiang.liyujapanese.mvp.presenter;

import java.util.List;

import io.reactivex.functions.Consumer;
import pri.weiqiang.liyujapanese.mvp.bean.GojuonItem;
import pri.weiqiang.liyujapanese.mvp.model.BaseModel;
import pri.weiqiang.liyujapanese.mvp.model.GojuonFragmentModelImpl;
import pri.weiqiang.liyujapanese.mvp.view.BaseView;


public class GojuonFragmentPresenterImpl extends BasePresenter<BaseView.GojuonFragmentView> implements BasePresenter.GojuonFragmentPresenter {

    BaseModel.GojuonFragmentModel model;

    public GojuonFragmentPresenterImpl(BaseView.GojuonFragmentView view) {
        super(view);

        model = new GojuonFragmentModelImpl();
    }

    @Override
    public void initGojuonFragment(int type) {

        view.setRecyclerView(type);
        model.getData(type, new Consumer<List<GojuonItem>>() {
            @Override
            public void accept(List<GojuonItem> gojuonItems) throws Exception {
                view.setData(gojuonItems);
            }
        });

    }
}
