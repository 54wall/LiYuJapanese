package pri.weiqiang.jet.kt.liyujapanese.ui.viewmodel

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import pri.weiqiang.jet.kt.liyujapanese.App
import pri.weiqiang.jet.kt.liyujapanese.data.bean.Word
import pri.weiqiang.jet.kt.liyujapanese.data.database.VocabDatabase

class WordFragmentViewModel(private val mSavedStateHandler: SavedStateHandle) : ViewModel() {
    private val TAG = WordFragmentViewModel::class.java.simpleName
    val wordList: LiveData<List<Word>>
    private val initLessonId = "新标日初级_01"

    fun setQuery(query: String) {
        mSavedStateHandler.set(QUERY_KEY, query)
    }

    companion object {
        private const val QUERY_KEY = "LessonId"
    }

    init {
        wordList = Transformations.switchMap(
            mSavedStateHandler.getLiveData(
                QUERY_KEY,
                "新标日初级_01"
            )
        ) { lessonId: String ->
            if (TextUtils.isEmpty(lessonId)) {
                return@switchMap VocabDatabase.getInstance(App.instance())?.wordDao()
                    ?.getWordByLessonId(lessonId)
            }
            VocabDatabase.getInstance(App.instance())?.wordDao()?.getWordByLessonId(lessonId)
        }
    }
}