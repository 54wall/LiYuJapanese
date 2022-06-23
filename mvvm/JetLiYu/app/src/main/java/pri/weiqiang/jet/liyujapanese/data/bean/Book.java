package pri.weiqiang.jet.liyujapanese.data.bean;

import java.util.List;

public class Book {
    private String name;
    private List<Lesson> mLessons;

    public Book(String name, List<Lesson> mLessons) {
        this.name = name;
        this.mLessons = mLessons;
    }

    public List<Lesson> getLessonList() {
        return mLessons;
    }

    public void setLessonList(List<Lesson> mLessons) {
        this.mLessons = mLessons;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
