package pri.weiqiang.liyujapanese.mvp.view.pixivIllust;

import android.content.DialogInterface;

import pri.weiqaing.common.base.mvp.BaseView;
import pri.weiqiang.liyujapanese.mvp.bean.pixivIllust.PixivIllustTab;

public interface PixivIllustTabFragmentView extends BaseView<PixivIllustTab> {
    void showAlertDialog(int titleId, int messageId,
                         int positiveTextId, DialogInterface.OnClickListener positiveButtonListener,
                         int negativeTextId, DialogInterface.OnClickListener negativeButtonListener);

    void showMsg(String msg);

    void showMsg(int msg);
}
