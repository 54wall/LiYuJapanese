package pri.weiqiang.liyujapanese.mvp.model;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;
import pri.weiqiang.liyujapanese.MyApplication;
import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.mvp.bean.BaiduTranslateBean;
import pri.weiqiang.liyujapanese.mvp.bean.TranslateSpinnerItem;
import pri.weiqiang.liyujapanese.network.baidu.service.BaiduService;
import pri.weiqiang.liyujapanese.network.baidu.service.BaiduTranslateServiceImpl;
import pri.weiqiang.liyujapanese.utils.ResourceUtils;


public class TranslateFragmentModelImpl implements BaseModel.TranslateFragmentModel {

    BaiduService.TranslateService service;

    public TranslateFragmentModelImpl() {
        super();
        service = new BaiduTranslateServiceImpl();
    }

    @Override
    public List<TranslateSpinnerItem> getFromList() {

        List<TranslateSpinnerItem> fromList = new ArrayList<>();
        fromList.add(new TranslateSpinnerItem(0, ResourceUtils.getString(MyApplication.getInstance(), R.string.auto_check), false));
        fromList.add(new TranslateSpinnerItem(R.drawable.ic_china, ResourceUtils.getString(MyApplication.getInstance(), R.string.chinese), true));
        fromList.add(new TranslateSpinnerItem(R.drawable.ic_uk, ResourceUtils.getString(MyApplication.getInstance(), R.string.english), true));
        fromList.add(new TranslateSpinnerItem(R.drawable.ic_japan, ResourceUtils.getString(MyApplication.getInstance(), R.string.japanese), true));

        return fromList;
    }

    @Override
    public List<TranslateSpinnerItem> getToList() {

        List<TranslateSpinnerItem> toList = new ArrayList<>();
        toList.add(new TranslateSpinnerItem(R.drawable.ic_china, ResourceUtils.getString(MyApplication.getInstance(), R.string.chinese), true));
        toList.add(new TranslateSpinnerItem(R.drawable.ic_uk, ResourceUtils.getString(MyApplication.getInstance(), R.string.english), true));
        toList.add(new TranslateSpinnerItem(R.drawable.ic_japan, ResourceUtils.getString(MyApplication.getInstance(), R.string.japanese), true));

        return toList;
    }

    @Override
    public void translate(String q, String from, String to, Consumer<BaiduTranslateBean> consumer, Consumer<Throwable> throwable) {

        service.translate(q, from, to, consumer, throwable);

    }


}
