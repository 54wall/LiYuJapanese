package pri.weiqiang.jet.kt.liyujapanese.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import pri.weiqiang.jet.kt.liyujapanese.data.bean.Lesson

@Dao
interface LessonDao {
    @Query("SELECT * FROM lessons WHERE book like :book")
    fun getLessonByBook(book: String): LiveData<List<Lesson>>

    @Query("SELECT * FROM lessons")
    fun getAll(): LiveData<List<Lesson>>
}