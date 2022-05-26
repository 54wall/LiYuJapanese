package pri.weiqiang.jet.liyujapanese.ui.word;

import android.text.TextUtils;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import pri.weiqiang.jet.liyujapanese.App;
import pri.weiqiang.jet.liyujapanese.data.database.VocabDatabase;
import pri.weiqiang.jet.liyujapanese.data.database.Word;

public class WordFragmentViewModel extends ViewModel {

    private String TAG = WordFragmentViewModel.class.getSimpleName();
    private LiveData<List<Word>> wordList;
    private String initLessonid = "新标日初级_01";
    private static final String QUERY_KEY = "LessonId";
    private final SavedStateHandle mSavedStateHandler;

    public LiveData<List<Word>> getWordList() {
        return wordList;
    }

    public WordFragmentViewModel(SavedStateHandle savedStateHandle) {
        mSavedStateHandler = savedStateHandle;
        //无法直接使用update(String lessonId),不会响应改变，因为wordList的地址已经改变，不是原来的wordList了，需要重新增加观察者
        wordList = Transformations.switchMap(
                savedStateHandle.getLiveData(QUERY_KEY, "新标日初级_01"),
                lessonId -> {
                    if (TextUtils.isEmpty(lessonId)) {
                        //database中直接使用App.getInstance不要从ViewModel传入任何Context
                        return VocabDatabase.getInstance(App.getInstance()).wordDao().getWordByLessonId(lessonId);
                    }
                    return VocabDatabase.getInstance(App.getInstance()).wordDao().getWordByLessonId(lessonId);
                });
    }

    public void setQuery(String query) {
        mSavedStateHandler.set(QUERY_KEY, query);
    }
    //wrong:不会被观察者观察到
//    public void update(String lessonId){
//        wordList = VocabDatabase.getInstance(App.getInstance()).wordDao().getWordByLessonId(lessonId);
//    }
}
