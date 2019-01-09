package pri.weiqiang.liyujapanese.mvp.bean;

/**
 * Created by weiqiang on 2018/3/18.
 */

public class LessonFav {


    private String lesson_id;
    private int count;

    public LessonFav(String lesson_id, int count) {
        this.lesson_id = lesson_id;
        this.count = count;
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
                ", lesson_id=" + lesson_id +
                ", count=" + count +
                '}';
    }

    public String getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(String lesson_id) {
        this.lesson_id = lesson_id;
    }

}
