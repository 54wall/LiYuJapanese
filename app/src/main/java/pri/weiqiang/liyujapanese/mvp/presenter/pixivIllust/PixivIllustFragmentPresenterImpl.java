package pri.weiqiang.liyujapanese.mvp.presenter.pixivIllust;

import android.content.DialogInterface;
import android.util.Log;

import java.util.List;

import io.reactivex.functions.Consumer;
import pri.weiqaing.common.base.mvp.BasePresenter;
import pri.weiqaing.common.utils.ActivityUtils;
import pri.weiqiang.liyujapanese.mvp.bean.pixivIllust.PixivIllustBean;
import pri.weiqiang.liyujapanese.mvp.model.pixivIllust.PixivIllustFragmentModel;
import pri.weiqiang.liyujapanese.mvp.model.pixivIllust.PixivIllustFragmentModelImpl;
import pri.weiqiang.liyujapanese.mvp.view.pixivIllust.PixivIllustFragmentView;


public class PixivIllustFragmentPresenterImpl extends BasePresenter<PixivIllustFragmentView> implements PixivIllustFragmentPresenter {

    PixivIllustFragmentModel model;
    private String TAG = PixivIllustFragmentPresenterImpl.class.getSimpleName();


    public PixivIllustFragmentPresenterImpl(PixivIllustFragmentView view) {
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

    @Override
    public void unsubscribe() {
        Log.e(TAG, "unsubscribe()");
        model.unsubscribe();
    }
}
