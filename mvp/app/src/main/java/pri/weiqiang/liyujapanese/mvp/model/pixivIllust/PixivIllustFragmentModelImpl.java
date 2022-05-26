package pri.weiqiang.liyujapanese.mvp.model.pixivIllust;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import pri.weiqaing.common.base.BaseApplication;
import pri.weiqaing.common.config.Constants;
import pri.weiqaing.common.utils.ResourceUtils;
import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.mvp.bean.pixivIllust.PixivIllustBean;
import pri.weiqiang.liyujapanese.network.pixiv.service.PixivIllustServiceImpl;
import pri.weiqiang.liyujapanese.network.pixiv.service.PixivService;


public class PixivIllustFragmentModelImpl implements PixivIllustFragmentModel {

    PixivService.IllustService service;
    private String TAG = PixivIllustFragmentModel.class.getSimpleName();

    public PixivIllustFragmentModelImpl() {
        service = new PixivIllustServiceImpl();
    }

    private static List<PixivIllustBean> handleResponseBody(ResponseBody responseBody) {

        List<PixivIllustBean> list = new ArrayList<>();

        Document document = null;

        try {
            document = Jsoup.parse(responseBody.string());
        } catch (IOException e) {
            e.printStackTrace();
        }

        responseBody.close();

        if (document != null) {

            Elements elements = document.getElementsByClass("ranking-item");

            for (int i = 0; i < elements.size(); i++) {

                final PixivIllustBean bean = new PixivIllustBean();

                Element element = elements.get(i);

                bean.setId(Integer.parseInt(element.attr("data-id")));
                bean.setTitle(element.attr("data-title"));
                bean.setAuthor(element.attr("data-user-name"));
                bean.setDate(element.attr("data-date"));

                Element link_element = element.getElementsByClass("ranking-image-item").first().select("a").first();
                if (link_element != null) {
                    bean.setLink(Constants.PIXIV_URL + "/" + link_element.attr("href"));
                }

                Element thumbnail_element = element.getElementsByClass("_layout-thumbnail").first().select("img").first();

                if (thumbnail_element != null) {
                    bean.setImg_240x480(thumbnail_element.attr("data-src"));

                    if (bean.getImg_240x480() != null) {

                        bean.setImg_600x600(bean.getImg_240x480().replace("/c/240x480/", "/c/600x600/"));
                        bean.setImg_1200x1200(bean.getImg_240x480().replace("/c/240x480/", "/c/1200x1200/"));
                        bean.setImg_original(bean.getImg_240x480().replace("/c/240x480/img-master/", "/img-original/").replace("_master1200", ""));

                    }
                }

                list.add(bean);
            }
        }

        return list;
    }

    @Override
    public void getIllusts(final String mode, Consumer<List<PixivIllustBean>> consumer) {

        Disposable disposable = Observable.create(new ObservableOnSubscribe<List<PixivIllustBean>>() {
            @Override
            public void subscribe(final ObservableEmitter<List<PixivIllustBean>> emitter) throws Exception {
                service.getIllusts(mode, new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        List<PixivIllustBean> list = handleResponseBody(responseBody);
                        if (list == null) {
                            emitter.onError(new Exception());
                        } else {
                            emitter.onNext(list);
                        }
                    }
                });

                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
        mCompositeDisposable.add(disposable);
    }

    @Override
    public String[] getOptions() {

        return new String[]{ResourceUtils.getString(BaseApplication.getInstance(), R.string.source_img),
                ResourceUtils.getString(BaseApplication.getInstance(), R.string.thumbnail),
                ResourceUtils.getString(BaseApplication.getInstance(), R.string.share)};

    }


    @Override
    public void unsubscribe() {
        Log.e(TAG, "unsubscrible");
        mCompositeDisposable.clear();
    }

    @Override
    public List<PixivIllustFragmentModel> getData() {
        return null;
    }
}
