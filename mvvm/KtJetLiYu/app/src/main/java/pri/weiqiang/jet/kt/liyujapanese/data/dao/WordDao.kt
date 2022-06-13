package pri.weiqiang.jet.kt.liyujapanese.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pri.weiqiang.jet.kt.liyujapanese.data.bean.Word

@Dao
interface WordDao {

    @Query("SELECT * FROM words WHERE lesson_id LIKE :lessonId")
    fun getWordByLessonId(lessonId:String):LiveData<List<Word>>

    @Query("SELECT * FROM words ")
    fun getAll():LiveData<List<Word>>

    @Query("SELECT * FROM words WHERE _id LIKE:id ")
    fun getWordById(id:Int):LiveData<List<Word>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg words:Word)

}