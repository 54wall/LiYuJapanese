package pri.weiqiang.liyujapanese.mvp.view;

import android.content.DialogInterface;
import android.os.Bundle;

import java.util.List;

import pri.weiqiang.liyujapanese.mvp.bean.Book;
import pri.weiqiang.liyujapanese.mvp.bean.GojuonItem;
import pri.weiqiang.liyujapanese.mvp.bean.GojuonMemory;
import pri.weiqiang.liyujapanese.mvp.bean.GojuonTab;
import pri.weiqiang.liyujapanese.mvp.bean.LessonFav;
import pri.weiqiang.liyujapanese.mvp.bean.PixivIllustBean;
import pri.weiqiang.liyujapanese.mvp.bean.PixivIllustTab;
import pri.weiqiang.liyujapanese.mvp.bean.TranslateSpinnerItem;
import pri.weiqiang.liyujapanese.mvp.bean.Word;
import pri.weiqiang.liyujapanese.mvp.bean.zhihu.BeforeDailyEntity;
import pri.weiqiang.liyujapanese.mvp.bean.zhihu.StoryContentEntity;

public interface BaseView<T> {

    void setData(List<T> data);

    interface MainActivityView {

        void openDrawer();

        void closeDrawer();

        void switchWords(String lesson, Boolean isExpandable);

        void switchZhihu();

        void switchNewsAPI();

        void updateNewsAPI(String category);

        void switchLessons();

        void switchFavLesson(Boolean isExpandable);

        void switchFavWord(String lessonId, Boolean isExpandable);

        void expandWords();

        void switchGojuon();

        void switchGojuonMemory();

        void switchMemory();

        void switchTranslate();

        void switchGame();

        void switchPixivIllust();

        void showSnackBar(int id);

        void switchAbout();

        void switchSetting();

        void startPhotoViewActivity(Bundle bundle);

        void startPuzzleActivity();

        void startSupperzzleActivity();

        void showAlertDialog(int titleId, int messageId,
                             int positiveTextId, DialogInterface.OnClickListener positiveButtonListener,
                             int negativeTextId, DialogInterface.OnClickListener negativeButtonListener);
    }

    interface GojuonTabFragmentView extends BaseView<GojuonTab> {
        void scrollViewPager(int position);
    }

    interface GojuonFragmentView extends BaseView<GojuonItem> {

        void setRecyclerView(int type);

    }

    interface GojuonMemoryTabFragmentView extends BaseView<GojuonTab> {
        void scrollViewPager(int position);
    }


    interface GojuonMemoryFragmentView extends BaseView<GojuonMemory> {

        void setRecyclerView(int type);

    }

    interface WordsFragmentView extends BaseView<Word> {

        void setRecyclerView();

    }

    interface NewsAPIFragmentView {

        <T> void refreshNews(T t);

        <T> void loadNewsBefore(T t);

    }

    interface ZhihuFragmentView {

        <T> void refreshHomeList(T t);

        void loadBeforeDaily(BeforeDailyEntity beforeDailyEntity);

    }

    interface ArticleDetailActivityView {
        void showContent(StoryContentEntity storyContentEntity);
    }

    interface FavWordsFragmentView extends BaseView<Word> {

        void setRecyclerView();


    }

    interface FavLessonFragmentView extends BaseView<LessonFav> {

        void setRecyclerView();
    }

    interface LessonsFragmentView extends BaseView<Book> {

        void initView();

    }

    interface TranslateFragmentView {
        void showMsg(String msg);

        void showMsg(int msg);

        String getSrcText();

        void setSrcEditText(String text);

        String getDstText();

        void setDstTextView(String text);

        void setFromSpinner(List<TranslateSpinnerItem> list);

        void setToSpinner(List<TranslateSpinnerItem> list);
    }

    interface PixivIllustTabFragmentView extends BaseView<PixivIllustTab> {
        void showAlertDialog(int titleId, int messageId,
                             int positiveTextId, DialogInterface.OnClickListener positiveButtonListener,
                             int negativeTextId, DialogInterface.OnClickListener negativeButtonListener);

        void showMsg(String msg);

        void showMsg(int msg);
    }

    interface PixivIllustFragmentView extends BaseView<PixivIllustBean> {

        void showMsg(int msg);

        void showMsg(String msg);

        void showProgress();

        void hideProgress();

        void showOptionsDialog(String[] options, DialogInterface.OnClickListener listener);

        void showImg(String url, int id);

    }

    interface MemoryFragmentView extends BaseView<GojuonItem> {
        void showMsg(int msg);

        void showMsg(String msg);

        void hideFabMenu();

    }

    interface PuzzleActivityView {
        void setData(GojuonItem current, List<GojuonItem> jams);

        void showSelectDialog(String[] selection);

        void showResultDialog(int title, String msg, int icon,
                              int pbt, DialogInterface.OnClickListener pbl,
                              int nbt, DialogInterface.OnClickListener nbl);

        void showDialog(int icon, int title, String msg);

        void setTitle(String title);

        void setTitle(int title);

        void addCount();

        void clearCount();

        void showMsg(int msg);

        void showMsg(String msg);
    }

}
