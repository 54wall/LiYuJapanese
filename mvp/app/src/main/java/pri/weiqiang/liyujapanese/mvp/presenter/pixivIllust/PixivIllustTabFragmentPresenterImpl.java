package pri.weiqiang.liyujapanese.mvp.presenter.pixivIllust;

import android.content.DialogInterface;

import pri.weiqaing.common.base.BaseApplication;
import pri.weiqaing.common.base.mvp.BasePresenter;
import pri.weiqaing.common.config.Constants;
import pri.weiqaing.common.manager.SharedPreferenceManager;
import pri.weiqiang.liyujapanese.mvp.model.pixivIllust.PixivIllustTabFragmentModel;
import pri.weiqiang.liyujapanese.mvp.model.pixivIllust.PixivIllustTabFragmentModelImpl;
import pri.weiqiang.liyujapanese.mvp.view.pixivIllust.PixivIllustTabFragmentView;

public class PixivIllustTabFragmentPresenterImpl extends BasePresenter<PixivIllustTabFragmentView> implements BasePresenter.PixivIllustTabFragmentPresenter {


    PixivIllustTabFragmentModel model;

    public PixivIllustTabFragmentPresenterImpl(PixivIllustTabFragmentView view) {
        super(view);
        model = new PixivIllustTabFragmentModelImpl();
    }

    @Override
    public void initPixivIllustTabFragment() {

        if (BaseApplication.ISWIFI) {

            view.setData(model.getData());


        } else {

            if (SharedPreferenceManager.getInstance().getBoolean(Constants.FLAG_TIPS_WIFI, true)) {

                view.showAlertDialog(pri.weiqiang.liyujapanese.R.string.warn,
                        pri.weiqiang.liyujapanese.R.string.warn_no_wifi, pri.weiqiang.liyujapanese.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferenceManager.getInstance().putBoolean(Constants.FLAG_TIPS_WIFI, false);
                                SharedPreferenceManager.getInstance().putBoolean(Constants.ALLOW_CONNECT_WITHOUT_WIFI, true);
                                dialog.dismiss();
                                view.setData(model.getData());
                            }
                        }, pri.weiqiang.liyujapanese.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferenceManager.getInstance().putBoolean(Constants.FLAG_TIPS_WIFI, false);
                                SharedPreferenceManager.getInstance().putBoolean(Constants.ALLOW_CONNECT_WITHOUT_WIFI, false);
                                dialog.dismiss();
                                view.showMsg(pri.weiqiang.liyujapanese.R.string.loading_disallow_pixiv);
                            }
                        });

            } else {

                if (SharedPreferenceManager.getInstance().getBoolean(Constants.ALLOW_CONNECT_WITHOUT_WIFI, false)) {

                    view.setData(model.getData());

                } else {
                    view.showMsg(pri.weiqiang.liyujapanese.R.string.loading_disallow_pixiv);
                }

            }

        }


    }
}
