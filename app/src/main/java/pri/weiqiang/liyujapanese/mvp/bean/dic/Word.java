package pri.weiqiang.liyujapanese.mvp.bean.dic;

/**
 * Created by weiqiang on 2018/3/16.
 */

public class Word {

    private int id;
    private String book_id;
    private String lesson_id;
    private String word;
    private String phonetic;
    private String translation;
    private int fav;
    private int cache;

    public Word(int id, String book_id, String lesson_id, String word, String phonetic, String translation, int fav, int cache) {
        this.id = id;
        this.book_id = book_id;
        this.lesson_id = lesson_id;
        this.word = word;
        this.phonetic = phonetic;
        this.translation = translation;
        this.fav = fav;
        this.cache = cache;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", book_id=" + book_id +
                ", lesson_id=" + lesson_id +
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

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(String lesson_id) {
        this.lesson_id = lesson_id;
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
