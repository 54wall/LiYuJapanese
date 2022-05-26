package pri.weiqiang.liyujapanese.mvp.view.pixivIllust;

import android.content.DialogInterface;

import pri.weiqaing.common.base.mvp.BaseView;
import pri.weiqiang.liyujapanese.mvp.bean.pixivIllust.PixivIllustBean;

public interface PixivIllustFragmentView extends BaseView<PixivIllustBean> {
    void showMsg(int msg);

    void showMsg(String msg);

    void showProgress();

    void hideProgress();

    void showOptionsDialog(String[] options, DialogInterface.OnClickListener listener);

    void showImg(String url, int id);
}
