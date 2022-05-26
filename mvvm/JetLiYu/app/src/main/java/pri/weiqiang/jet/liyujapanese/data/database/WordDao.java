package pri.weiqiang.jet.liyujapanese.data.database;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface WordDao {

    @Query("SELECT * FROM words WHERE lesson_id LIKE :lessonId")
    LiveData<List<Word>> getWordByLessonId(String lessonId);

    @Query("SELECT * FROM words ")
    LiveData<List<Word>> getAll();

    @Query("SELECT * FROM words WHERE _id LIKE:id ")
    LiveData<Word> getWordById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Void insertAll(Word... words);


}
