package pri.weiqiang.liyujapanese.mvp.view.translation;

import java.util.List;

import pri.weiqiang.liyujapanese.mvp.bean.translation.TranslateSpinnerItem;

public interface TranslateFragmentView {
    void showMsg(String msg);

    void showMsg(int msg);

    String getSrcText();

    void setSrcEditText(String text);

    String getDstText();

    void setDstTextView(String text);

    void setFromSpinner(List<TranslateSpinnerItem> list);

    void setToSpinner(List<TranslateSpinnerItem> list);
}
