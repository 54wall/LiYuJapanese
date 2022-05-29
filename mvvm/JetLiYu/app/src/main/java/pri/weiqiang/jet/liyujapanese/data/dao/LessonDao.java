package pri.weiqiang.jet.liyujapanese.data.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import pri.weiqiang.jet.liyujapanese.data.bean.Lesson;

@Dao
public interface LessonDao {
    @Query("SELECT * FROM lessons WHERE book like :book")
    LiveData<List<Lesson>> getLessonByBook(String book);

    @Query("SELECT * FROM lessons")
    LiveData<List<Lesson>> getAll();
}
