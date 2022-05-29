package pri.weiqiang.jet.liyujapanese.ui.viewmodel;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import pri.weiqiang.jet.liyujapanese.App;
import pri.weiqiang.jet.liyujapanese.data.bean.Lesson;
import pri.weiqiang.jet.liyujapanese.data.database.VocabDatabase;

public class LessonFragmentViewModel extends ViewModel {
    private String TAG = LessonFragmentViewModel.class.getSimpleName();
    private LiveData<List<Lesson>> lessonList;

    /**
     * 因为查询Lesson是一次性的，所以不需要像 {@link WordFragmentViewModel}一样去对wordList进行观察
     */
    public LiveData<List<Lesson>> getLessonList() {
        if (lessonList == null) {
            lessonList = VocabDatabase.getInstance(App.getInstance()).lessonDao().getAll();
        }
        return lessonList;
    }


}
