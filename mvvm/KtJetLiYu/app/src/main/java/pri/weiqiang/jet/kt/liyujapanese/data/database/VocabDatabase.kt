package pri.weiqiang.jet.kt.liyujapanese.data.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pri.weiqiang.jet.kt.liyujapanese.data.bean.Word
import pri.weiqiang.jet.kt.liyujapanese.data.dao.WordDao

@Database(entities = [Word::class], version = 1)
abstract class VocabDatabase : RoomDatabase() {
    abstract fun wordDao() : WordDao?

    companion object{
        private val DATABASE_NAME = "vocab"
        private val TAG = VocabDatabase::class.java.simpleName
        private var sInstance:VocabDatabase? = null
        fun getInstance(context:Context):VocabDatabase?{
//            return sInstance ?: synchronized(this){
//                sInstance = Room.
//                    databaseBuilder(context.applicationContext,VocabDatabase::class.java, DATABASE_NAME)
//                    .createFromAsset("databases/vocab.db")
//                    .build()
//                return sInstance
//            }
            if (sInstance == null) {
                synchronized(this) {
                    sInstance = Room.databaseBuilder(
                        context.applicationContext,
                        VocabDatabase::class.java, DATABASE_NAME
                    )
                        .createFromAsset("databases/vocab.db")
                        .build()
                    Log.d(TAG, "Made new database")
                }
            }
            return sInstance
        }
    }
}