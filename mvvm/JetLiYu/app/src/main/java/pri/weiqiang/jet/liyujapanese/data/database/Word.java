package pri.weiqiang.jet.liyujapanese.data.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "words")
public class Word {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private int id;
    @ColumnInfo(name = "book_id")
    private String bookId;
    @ColumnInfo(name = "lesson_id")
    private String lessonId;
    @ColumnInfo(name = "word")
    private String word;
    @ColumnInfo(name = "phonetic")
    private String phonetic;
    @ColumnInfo(name = "translation")
    private String translation;
    @ColumnInfo(name = "fav")
    private int fav;
    @ColumnInfo(name = "cache")
    private int cache;

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", bookId='" + bookId + '\'' +
                ", lessonId='" + lessonId + '\'' +
                ", word='" + word + '\'' +
                ", phonetic='" + phonetic + '\'' +
                ", translation='" + translation + '\'' +
                ", fav=" + fav +
                ", cache=" + cache +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public int getFav() {
        return fav;
    }

    public void setFav(int fav) {
        this.fav = fav;
    }

    public int getCache() {
        return cache;
    }

    public void setCache(int cache) {
        this.cache = cache;
    }
}
