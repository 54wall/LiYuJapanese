package pri.weiqiang.liyujapanese.mvp.view;

import android.content.DialogInterface;
import android.os.Bundle;

public interface MainActivityView {
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
