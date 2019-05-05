package pri.weiqaing.common.base.mvp;

import pri.weiqaing.common.rxbus.event.EventContainer;

public abstract class BasePresenter<T> {

    public final T view;

    public BasePresenter(T view) {
        this.view = view;
    }

    public interface MainActivityPresenter {
        void initMainActivity();

        void onRadioButtonChanged(int position);

        void onNavigationItemSelected(int id);

        void onBusEventInteraction(EventContainer eventContainer);

        void onMenuItemSelected(int id);

    }

    public interface GojuonTabFragmentPresenter {

        void initGojuonTabFragment();

    }

    public interface GojuonMemoryTabFragmentPresenter {

        void initGojuonMemoryTabFragment();

    }

    public interface WordsFragmentPresenter {

        void initWordsFragment();

        void randomList();

        void unsubscribe();

    }

    public interface NewsAPIFragmentPresenter {

        void getNews(String country, String from, String to, String category, String pageSize, String apiKey);

        void getNewsBefore(String country, String from, String to, String category, String pageSize, String apiKey);

        void unsubscribe();


    }

    public interface ZhihuFragmentPresenter {

        void initZhihuFragment();

        void getBeforeDaily(String date);

        void unsubscribe();

    }

    public interface ArticleDetailActivityPresenter {

        void showContent(int id);

    }

    public interface FavWordsFragmentPresenter {

        void initFavWordsFragment();

        void randomList();

        void unsubscribe();

    }

    public interface FavLessonFragmentPresenter {

        void initFavLessonFragment();

        void unsubscribe();

    }

    public interface LessonsFragmentPresenter {

        void initLessonsFragment();

        void unsubscribe();

    }

    public interface GojuonFragmentPresenter {

        void initGojuonFragment(int category);

        void unsubscribe();

    }

    public interface GojuonMemoryFragmentPresenter {

        void initGojuonMemoryFragment(int category);

        void unsubscribe();

    }

    public interface TranslateFragmentPresenter {

        void initTranslateFragment();

        void checkFromLanguate(int from);

        void checkToLanguage(int to);

        void checkImageViewClick(int id);

        void doTranslate();

    }

    public interface PixivIllustTabFragmentPresenter {
        void initPixivIllustTabFragment();
    }

    public interface MemoryFragmentPresenter {

        void initMemoryFragment();

        void loadMore(int category);

        void setDate(int category);

    }

}
