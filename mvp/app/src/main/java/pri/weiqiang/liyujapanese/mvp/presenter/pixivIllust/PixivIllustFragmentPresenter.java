package pri.weiqiang.liyujapanese.mvp.presenter.pixivIllust;

import pri.weiqiang.liyujapanese.mvp.bean.pixivIllust.PixivIllustBean;

public interface PixivIllustFragmentPresenter {
    void initPixivIllustFragment(String mode);

    void reloadData(String mode);

    void onItemClick(PixivIllustBean bean);

    void unsubscribe();
}
