package pri.weiqiang.jet.liyujapanese.data.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import pri.weiqiang.jet.liyujapanese.data.bean.Lesson;
import pri.weiqiang.jet.liyujapanese.data.bean.Word;
import pri.weiqiang.jet.liyujapanese.data.dao.LessonDao;
import pri.weiqiang.jet.liyujapanese.data.dao.WordDao;

@Database(entities = {Word.class,Lesson.class}, version = 1)
public abstract class VocabDatabase extends RoomDatabase {

    private static final String LOG_TAG = VocabDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "vocab";

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static volatile VocabDatabase sInstance;

    public static VocabDatabase getInstance(Context context) {
        Log.d(LOG_TAG, "Getting the database");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                                VocabDatabase.class, VocabDatabase.DATABASE_NAME)
                        .createFromAsset("databases/vocab.db")
                        .build();
                Log.d(LOG_TAG, "Made new database");
            }
        }
        return sInstance;
    }

    public abstract WordDao wordDao();

    public abstract LessonDao lessonDao();
}
