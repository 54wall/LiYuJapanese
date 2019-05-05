package pri.weiqiang.liyujapanese.comparator;

import java.util.Comparator;

import pri.weiqiang.liyujapanese.mvp.bean.dic.LessonFav;

public class LessonFavComporator implements Comparator<LessonFav> {


    @Override
    public int compare(LessonFav o1, LessonFav o2) {
        if (o1.getCount() < o2.getCount()) {
            return 1;
        } else if (o1.getCount() == o2.getCount()) {

            if (o1.getCount() <= o2.getCount()) {
                return 1;
            } else
                return -1;
        } else {
            return -1;
        }
    }
}
