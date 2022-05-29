package pri.weiqiang.jet.liyujapanese.data.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "lessons")
public class Lesson {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private int id;
    @ColumnInfo(name = "book")
    private String book;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "count")
    private int count;

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", book='" + book + '\'' +
                ", title='" + title + '\'' +
                ", count=" + count +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
