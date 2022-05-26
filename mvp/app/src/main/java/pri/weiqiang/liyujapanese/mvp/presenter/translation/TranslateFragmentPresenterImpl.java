package pri.weiqiang.liyujapanese.mvp.presenter.translation;

import android.util.Log;

import io.reactivex.functions.Consumer;
import pri.weiqaing.common.base.BaseApplication;
import pri.weiqaing.common.base.mvp.BasePresenter;
import pri.weiqaing.common.manager.ClipboardManager;
import pri.weiqaing.common.utils.StringUtils;
import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.mvp.bean.translation.BaiduTranslateBean;
import pri.weiqiang.liyujapanese.mvp.model.translation.TranslateFragmentModel;
import pri.weiqiang.liyujapanese.mvp.model.translation.TranslateFragmentModelImpl;
import pri.weiqiang.liyujapanese.mvp.view.translation.TranslateFragmentView;
import pri.weiqiang.liyujapanese.network.baidu.BaiduTranslateApi;


public class TranslateFragmentPresenterImpl extends BasePresenter<TranslateFragmentView> implements BasePresenter.TranslateFragmentPresenter {

    TranslateFragmentModel model;
    private String TAG = TranslateFragmentPresenterImpl.class.getSimpleName();

    public TranslateFragmentPresenterImpl(TranslateFragmentView view) {
        super(view);
        model = new TranslateFragmentModelImpl();
    }

    @Override
    public void initTranslateFragment() {

        view.setFromSpinner(model.getFromList());
        view.setToSpinner(model.getToList());

    }

    @Override
    public void checkFromLanguate(int from) {
        BaseApplication.FROM_LAN = from;
    }

    @Override
    public void checkToLanguage(int to) {
        BaseApplication.TO_LAN = to;
    }

    @Override
    public void checkImageViewClick(int id) {

        switch (id) {

            case R.id.iv_src_copy:

                String srcText = view.getSrcText();
                if (!StringUtils.isNullOrEmpty(srcText)) {
                    ClipboardManager.getInstance().setText("label", view.getSrcText());
                    view.showMsg(R.string.copy_successfully);
                }
                break;
            case R.id.iv_src_paste:
                view.setSrcEditText(ClipboardManager.getInstance().getText());
                break;
            case R.id.iv_src_clear:
                view.setSrcEditText("");
                break;
            case R.id.iv_dst_copy:

                String dstText = view.getDstText();
                if (!StringUtils.isNullOrEmpty(dstText)) {
                    ClipboardManager.getInstance().setText("label", view.getDstText());
                    view.showMsg(R.string.copy_successfully);
                }
                break;
            case R.id.iv_dst_clear:
                view.setDstTextView("");
                break;
            default:
                break;
        }

    }

    @Override
    public void doTranslate() {

        String srcText = view.getSrcText();

        if (!StringUtils.isNullOrEmpty(srcText)) {

            String from;

            switch (BaseApplication.FROM_LAN) {

                case 0:
                    from = BaiduTranslateApi.AUTO;
                    break;
                case 1:
                    from = BaiduTranslateApi.ZH;
                    break;
                case 2:
                    from = BaiduTranslateApi.EN;
                    break;
                case 3:
                    from = BaiduTranslateApi.JP;
                    break;
                default:
                    from = BaiduTranslateApi.AUTO;
                    break;

            }

            String to;

            switch (BaseApplication.TO_LAN) {

                case 0:
                    to = BaiduTranslateApi.ZH;
                    break;
                case 1:
                    to = BaiduTranslateApi.EN;
                    break;
                case 2:
                    to = BaiduTranslateApi.JP;
                    break;
                default:
                    to = BaiduTranslateApi.ZH;
                    break;

            }

            model.translate(view.getSrcText(), from, to, new Consumer<BaiduTranslateBean>() {

                @Override
                public void accept(BaiduTranslateBean baiduTranslateBean) throws Exception {
                    if (baiduTranslateBean.getError_code() == null) {
                        view.setDstTextView(baiduTranslateBean.getTrans_result()[0].getDst());
                    } else {
                        view.showMsg(baiduTranslateBean.getError_msg());
                    }
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    Log.e(TAG, "model.translate-throwable:" + throwable.toString());
                }
            });
        }


    }
}
