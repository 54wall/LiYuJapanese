package pri.weiqiang.liyujapanese.mvp.bean.dic;

/**
 * Created by weiqiang on 2018/3/18.
 */

public class Lesson {

    private int id;
    private String book;
    private String title;
    private int count;

    public Lesson(int id, String book, String title, int count) {
        this.id = id;
        this.book = book;
        this.title = title;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                ", id=" + id +
                ", book=" + book +
                ", title=" + title +
                ", count=" + count +
                '}';
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
}
