package pri.weiqiang.jet.kt.liyujapanese.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pri.weiqiang.jet.kt.liyujapanese.App
import pri.weiqiang.jet.kt.liyujapanese.data.bean.Lesson
import pri.weiqiang.jet.kt.liyujapanese.data.database.VocabDatabase

class LessonFragmentViewModel: ViewModel() {
    private val TAG:String = LessonFragmentViewModel::class.java.simpleName
//    private val lessonList:LiveData<List<Lesson>> by lazy {
//        lessonList?.also {
//            VocabDatabase.getInstance(App.instance())?.lessonDao()?.getAll()
//        }
//    }
//
    fun getUsers(): LiveData<List<Lesson>>? {
        return lessonList
    }

    /**
     * 因为查询Lesson是一次性的，所以不需要像 [WordFragmentViewModel]一样去对wordList进行观察
     */
    var lessonList: LiveData<List<Lesson>>? = null
        get() {
            if (field == null) {
                field = VocabDatabase.getInstance(App.instance())?.lessonDao()?.getAll()
            }
            return field
        }
        private set
}