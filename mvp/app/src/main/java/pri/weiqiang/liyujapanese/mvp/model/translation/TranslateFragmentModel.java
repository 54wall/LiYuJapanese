package pri.weiqiang.liyujapanese.mvp.model.translation;

import java.util.List;

import io.reactivex.functions.Consumer;
import pri.weiqiang.liyujapanese.mvp.bean.translation.BaiduTranslateBean;
import pri.weiqiang.liyujapanese.mvp.bean.translation.TranslateSpinnerItem;

public interface TranslateFragmentModel {
    List<TranslateSpinnerItem> getFromList();

    List<TranslateSpinnerItem> getToList();

    void translate(String q, String from, String to, Consumer<BaiduTranslateBean> consumer, Consumer<Throwable> throwable);
}
