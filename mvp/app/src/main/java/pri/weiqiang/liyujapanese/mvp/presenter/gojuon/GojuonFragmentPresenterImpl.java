package pri.weiqiang.liyujapanese.mvp.presenter.gojuon;

import android.util.Log;

import java.util.List;

import io.reactivex.functions.Consumer;
import pri.weiqaing.common.base.mvp.BasePresenter;
import pri.weiqiang.liyujapanese.mvp.bean.gojuon.GojuonItem;
import pri.weiqiang.liyujapanese.mvp.model.gojuon.GojuonFragmentModel;
import pri.weiqiang.liyujapanese.mvp.model.gojuon.GojuonFragmentModelImpl;
import pri.weiqiang.liyujapanese.mvp.view.gojuon.GojuonFragmentView;


public class GojuonFragmentPresenterImpl extends BasePresenter<GojuonFragmentView> implements BasePresenter.GojuonFragmentPresenter {

    GojuonFragmentModel model;
    private String TAG = GojuonFragmentPresenterImpl.class.getSimpleName();

    public GojuonFragmentPresenterImpl(GojuonFragmentView view) {
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

    @Override
    public void unsubscribe() {
        Log.e(TAG, "unsubscribe()");
        model.unsubscribe();
    }
}
