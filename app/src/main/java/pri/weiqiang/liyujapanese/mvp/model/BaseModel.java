package pri.weiqiang.liyujapanese.mvp.model;

import android.util.Log;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import pri.weiqiang.liyujapanese.mvp.bean.BaiduTranslateBean;
import pri.weiqiang.liyujapanese.mvp.bean.Book;
import pri.weiqiang.liyujapanese.mvp.bean.GojuonItem;
import pri.weiqiang.liyujapanese.mvp.bean.GojuonMemory;
import pri.weiqiang.liyujapanese.mvp.bean.GojuonTab;
import pri.weiqiang.liyujapanese.mvp.bean.LessonFav;
import pri.weiqiang.liyujapanese.mvp.bean.PixivIllustBean;
import pri.weiqiang.liyujapanese.mvp.bean.PixivIllustTab;
import pri.weiqiang.liyujapanese.mvp.bean.TranslateSpinnerItem;
import pri.weiqiang.liyujapanese.mvp.bean.Word;
import pri.weiqiang.liyujapanese.mvp.bean.newsapi.NewsResponse;
import pri.weiqiang.liyujapanese.mvp.bean.zhihu.BeforeDailyEntity;
import pri.weiqiang.liyujapanese.mvp.bean.zhihu.LatestDailyEntity;
import pri.weiqiang.liyujapanese.mvp.bean.zhihu.StoryContentEntity;


public interface BaseModel<T> {


    //增加对RxJava2的生命周期管理，避免The result of subscribe is not used less... (Ctrl+F1)
    //Inspection info:Some methods have no side effects, an calling them without doing something without the result is suspicious.  Issue id: CheckResult
    CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    List<T> getData();

    interface MainActivityModel {
    }


    interface GojuonTabFragmentModel extends BaseModel<GojuonTab> {
    }

    interface GojuonMemoryTabFragmentModel extends BaseModel<GojuonTab> {
    }

    interface GojuonFragmentModel {
        void getData(int category, Consumer<List<GojuonItem>> consumer);
    }

    interface GojuonMemoryFragmentModel {
        void getData(int category, Consumer<List<GojuonMemory>> consumer);
    }

    interface WordsFragmentModel {
        void getData(Consumer<List<Word>> consumer, String lesson);
    }

    interface ZhihuFragmentModel {
        void getLatestDaily(Consumer<LatestDailyEntity> consumer, Consumer<Throwable> throwable);

        void getBeforeDaily(Consumer<BeforeDailyEntity> consumer, Consumer<Throwable> throwable, String date);
    }

    interface ArticleDetailActivityModel {
        void getContent(Consumer<StoryContentEntity> consumer, Consumer<Throwable> throwable, int id);
    }

    interface FavWordsFragmentModel {
        void getData(Consumer<List<Word>> consumer, Consumer<Throwable> throwable, final String lessonId);

    }

    interface FavLessonFragmentModel {
        void getData(Consumer<List<LessonFav>> consumer, Consumer<Throwable> throwable);
        void unsubscribe();

    }

    interface NewsAPIFragmentModel {
        void getHeadlinesByCountry(Consumer<NewsResponse> consumer, Consumer<Throwable> throwable, String country, String from, String to, String category, String pageSize, String apiKey);
    }

    interface LessonsFragmentModel {
        void getData(Consumer<List<Book>> consumer);
    }

    interface TranslateFragmentModel {

        List<TranslateSpinnerItem> getFromList();

        List<TranslateSpinnerItem> getToList();

        void translate(String q, String from, String to, Consumer<BaiduTranslateBean> consumer, Consumer<Throwable> throwable);

    }

    interface PixivIllustFragmentModel {

        void getIllusts(String mode, Consumer<List<PixivIllustBean>> consumer);

        String[] getOptions();

    }

    interface PixivIllustTabFragmentModel extends BaseModel<PixivIllustTab> {
    }

    interface MemoryFragmentModel {

        List<GojuonItem> getQingYinWithoutHeader();

        List<GojuonItem> getZhuoYinWithoutHeader();

        List<GojuonItem> getAoYinWithoutHeader();


    }

    interface PuzzleActivityModel {
        String[] getOptions();

        List<GojuonItem> getItems();
    }

}
