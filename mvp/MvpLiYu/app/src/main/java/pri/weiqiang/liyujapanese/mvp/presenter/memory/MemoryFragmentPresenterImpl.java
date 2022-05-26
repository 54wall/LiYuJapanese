package pri.weiqiang.liyujapanese.mvp.presenter.memory;

import pri.weiqaing.common.base.mvp.BasePresenter;
import pri.weiqaing.common.config.Constants;
import pri.weiqiang.liyujapanese.mvp.model.memory.MemoryFragmentModel;
import pri.weiqiang.liyujapanese.mvp.model.memory.MemoryFragmentModelImpl;
import pri.weiqiang.liyujapanese.mvp.view.memory.MemoryFragmentView;

public class MemoryFragmentPresenterImpl extends BasePresenter<MemoryFragmentView> implements BasePresenter.MemoryFragmentPresenter {

    MemoryFragmentModel model;

    public MemoryFragmentPresenterImpl(MemoryFragmentView view) {
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
