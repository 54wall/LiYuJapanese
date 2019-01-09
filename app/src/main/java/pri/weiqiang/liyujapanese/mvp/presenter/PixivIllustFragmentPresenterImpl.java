package pri.weiqiang.liyujapanese.mvp.presenter;

import android.content.DialogInterface;

import java.util.List;

import io.reactivex.functions.Consumer;
import pri.weiqiang.liyujapanese.mvp.bean.PixivIllustBean;
import pri.weiqiang.liyujapanese.mvp.model.BaseModel;
import pri.weiqiang.liyujapanese.mvp.model.PixivIllustFragmentModelImpl;
import pri.weiqiang.liyujapanese.mvp.view.BaseView;
import pri.weiqiang.liyujapanese.utils.ActivityUtils;


public class PixivIllustFragmentPresenterImpl extends BasePresenter<BaseView.PixivIllustFragmentView> implements BasePresenter.PixivIllustFragmentPresenter {

    BaseModel.PixivIllustFragmentModel model;


    public PixivIllustFragmentPresenterImpl(BaseView.PixivIllustFragmentView view) {
        super(view);
        model = new PixivIllustFragmentModelImpl();
    }

    @Override
    public void initPixivIllustFragment(String mode) {

        reloadData(mode);

    }

    @Override
    public void reloadData(String mode) {

        view.showProgress();

        model.getIllusts(mode, new Consumer<List<PixivIllustBean>>() {
                    @Override
                    public void accept(List<PixivIllustBean> list) throws Exception {
                        view.setData(list);
                        view.hideProgress();
                    }

                }
        );

    }

    @Override
    public void onItemClick(final PixivIllustBean bean) {

        view.showOptionsDialog(model.getOptions(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which) {

                    case 0:
                        ActivityUtils.openUrl(bean.getLink());
                        break;
                    case 1:
                        view.showImg(bean.getImg_240x480(), bean.getId());
                        break;
                    case 2:
                        ActivityUtils.share(bean.getLink());
                        break;
                    default:
                        break;
                }

                dialog.dismiss();
            }
        });


    }
}
