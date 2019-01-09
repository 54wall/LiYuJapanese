package pri.weiqiang.liyujapanese.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author weiqiang
 * @version 1.0
 * 为了排列输出
 * http://blog.csdn.net/arsenic/article/details/44967009
 * @date 创建时间：2018-9-29 下午5:12:03
 */
public class RandomListUtil {

    private static RandomListUtil instance = null;

    public static synchronized RandomListUtil getInstance() {

        if (instance == null) {
            instance = new RandomListUtil();
        }

        return instance;
    }

    public <V> List<V> randomList(List<V> sourceList) {

        if (sourceList == null || sourceList.size() == 0) {
            return sourceList;
        }
        List<V> random = new ArrayList<V>(sourceList.size());
        do {
            int index = Math.abs(new Random().nextInt(sourceList.size()));
            random.add(sourceList.remove(index));

        } while (sourceList.size() > 0);

        return random;

    }
}
