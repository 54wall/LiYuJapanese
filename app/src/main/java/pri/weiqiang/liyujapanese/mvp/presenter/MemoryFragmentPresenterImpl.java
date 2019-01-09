package pri.weiqiang.liyujapanese.mvp.presenter;

import pri.weiqiang.liyujapanese.config.Constants;
import pri.weiqiang.liyujapanese.mvp.model.BaseModel;
import pri.weiqiang.liyujapanese.mvp.model.MemoryFragmentModelImpl;
import pri.weiqiang.liyujapanese.mvp.view.BaseView;

public class MemoryFragmentPresenterImpl extends BasePresenter<BaseView.MemoryFragmentView> implements BasePresenter.MemoryFragmentPresenter {

    BaseModel.MemoryFragmentModel model;

    public MemoryFragmentPresenterImpl(BaseView.MemoryFragmentView view) {
        super(view);
        model = new MemoryFragmentModelImpl();
    }

    @Override
    public void initMemoryFragment() {
        setDate(Constants.CATEGORY_QINGYIN);
    }

    @Override
    public void loadMore(int categiry) {

        setDate(categiry);
        view.showMsg(pri.weiqiang.liyujapanese.R.string.one_more_time);
    }

    @Override
    public void setDate(int category) {

        switch (category) {

            case Constants.CATEGORY_QINGYIN:

                view.setData(model.getQingYinWithoutHeader());

                break;
            case Constants.CATEGORY_ZHUOYIN:

                view.setData(model.getZhuoYinWithoutHeader());

                break;
            case Constants.CATEGORY_AOYIN:

                view.setData(model.getAoYinWithoutHeader());

                break;
            default:
                break;


        }

    }


}
