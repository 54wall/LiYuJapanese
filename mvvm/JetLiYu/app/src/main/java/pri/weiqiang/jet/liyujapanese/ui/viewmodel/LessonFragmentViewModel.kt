package pri.weiqiang.jet.liyujapanese.ui.viewmodel

import androidx.lifecycle.ViewModel
import pri.weiqiang.jet.liyujapanese.ui.viewmodel.LessonFragmentViewModel
import androidx.lifecycle.LiveData
import pri.weiqiang.jet.liyujapanese.data.bean.Lesson
import pri.weiqiang.jet.liyujapanese.data.database.VocabDatabase
import pri.weiqiang.jet.liyujapanese.App

class LessonFragmentViewModel : ViewModel() {
    private val TAG = LessonFragmentViewModel::class.java.simpleName

    /**
     * 因为查询Lesson是一次性的，所以不需要像 [WordFragmentViewModel]一样去对wordList进行观察
     */
    var lessonList: LiveData<List<Lesson>>? = null
        get() {
            if (field == null) {
                field = VocabDatabase.getInstance(App.getInstance()).lessonDao().all
            }
            return field
        }
        private set
}