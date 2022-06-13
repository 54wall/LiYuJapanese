package pri.weiqiang.jet.kt.liyujapanese.data.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lessons")
data class Lesson(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "_id") val id: Int,
    @ColumnInfo(name = "book") val book: String?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "count") val count: Int
)