package pri.weiqiang.jet.kt.liyujapanese.data.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class Word(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "_id") val id:Int,
    @ColumnInfo(name = "book_id") val  bookId:String?,
    @ColumnInfo(name = "lesson_id") val lessonId:String?,
    @ColumnInfo(name ="word") val word:String?,
    @ColumnInfo(name = "phonetic") val phonetic:String?,
    @ColumnInfo(name = "translation") val translation:String?,
    @ColumnInfo(name = "fav") val fav:Int,//同cache
    /*
    * Expected:
    TableInfo{name='words', columns={cache=Column{name='cache', type='INTEGER', affinity='3', notNull=false, primaryKeyPosition=0, defaultValue='null'}, phonetic=Column{name='phonetic', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='null'}, translation=Column{name='translation', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='null'}, fav=Column{name='fav', type='INTEGER', affinity='3', notNull=false, primaryKeyPosition=0, defaultValue='null'}, _id=Column{name='_id', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=1, defaultValue='null'}, book_id=Column{name='book_id', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='null'}, lesson_id=Column{name='lesson_id', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='null'}, word=Column{name='word', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='null'}}, foreignKeys=[], indices=[]}
     Found:
    TableInfo{name='words', columns={cache=Column{name='cache', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=0, defaultValue='null'}, phonetic=Column{name='phonetic', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='null'}, translation=Column{name='translation', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='null'}, fav=Column{name='fav', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=0, defaultValue='null'}, _id=Column{name='_id', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=1, defaultValue='null'}, book_id=Column{name='book_id', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='null'}, lesson_id=Column{name='lesson_id', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='null'}, word=Column{name='word', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='null'}}, foreignKeys=[], indices=[]}*/
    @ColumnInfo(name = "cache") val cache:Int //Int?则表示可为空，数据库默认为0不为空，会报
)