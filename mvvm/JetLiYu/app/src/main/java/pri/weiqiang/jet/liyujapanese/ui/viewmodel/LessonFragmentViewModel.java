package pri.weiqiang.jet.liyujapanese.ui.viewmodel;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import pri.weiqiang.jet.liyujapanese.App;
import pri.weiqiang.jet.liyujapanese.data.bean.Book;
import pri.weiqiang.jet.liyujapanese.data.bean.Lesson;
import pri.weiqiang.jet.liyujapanese.data.database.VocabDatabase;

public class LessonFragmentViewModel extends ViewModel {
    private String TAG = LessonFragmentViewModel.class.getSimpleName();
    private LiveData<List<Lesson>> lessonList;
    private LiveData<List<Book>> bookList;

    /**
     * 因为查询Lesson是一次性的，所以不需要像 {@link WordFragmentViewModel}一样去对wordList进行观察
     */
    public LiveData<List<Lesson>> getLessonList() {
        Log.e(TAG, "getLessonList");
        if (lessonList == null) {
            lessonList = VocabDatabase.getInstance(App.getInstance()).lessonDao().getAll();
        }
        return lessonList;
    }

    public LiveData<List<Book>> getBookList() {
        Log.e(TAG, "getBookList");
        return bookList = Transformations.switchMap(getLessonList(), lessonItemList -> {
            HashMap<String, List<Lesson>> map = new HashMap<>();
            for (Lesson lesson : lessonItemList) {
                List<Lesson> lessonInBookList;
                if (map.containsKey(lesson.getBook())) {
                    lessonInBookList = map.get(lesson.getBook());
                } else {
                    lessonInBookList = new ArrayList<>();
                }
                lessonInBookList.add(lesson);
                map.put(lesson.getBook(), lessonInBookList);
            }
            //TODO:为何无法new LiveData
            MutableLiveData<List<Book>> bookListLiveData = new MutableLiveData<List<Book>>();
            List<Book> bookList = new ArrayList<>();

            for (String key : map.keySet()) {
                bookList.add(new Book(key, map.get(key)));
            }
            bookListLiveData.setValue(bookList);
            return bookListLiveData;
        });


    }


}
