package pri.weiqiang.liyujapanese.network.baidu.service;

import io.reactivex.functions.Consumer;
import pri.weiqiang.liyujapanese.mvp.bean.BaiduTranslateBean;


public interface BaiduService {

    interface TranslateService {

        void translate(String q, String from, String to, Consumer<BaiduTranslateBean> consumer, Consumer<Throwable> throwable);

    }


}
