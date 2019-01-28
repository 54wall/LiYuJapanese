package pri.weiqiang.liyujapanese.manager;

import android.app.Activity;
import android.util.Log;

import java.util.Vector;

public class ActivityManager {

    public volatile static Activity current;
    private static ActivityManager instance = null;
    private final String TAG = getClass().getSimpleName();
    private Vector<Activity> activities = new Vector<>();

    private ActivityManager() {
    }


    public static ActivityManager getInstance() {
        if (instance == null) {
            synchronized (ActivityManager.class) {
                if (instance == null) {
                    instance = new ActivityManager();
                }
            }
        }
        return instance;
    }


    public static Activity getCurrent() {
        return current;
    }

    public static void setCurrent(Activity current) {
        ActivityManager.current = current;
    }

    public void register(Activity activity) {

        if (!activities.contains(activity)) {
            activities.add(activity);
            Log.i(TAG, "register: OK -> " + activity);

        }

    }

    public void unregister(Activity activity) {
        Log.e(TAG, "unregister");
        if (activities.contains(activity)) {
            activities.remove(activity);
            Log.i(TAG, "unregister: OK -> " + activity);
            Log.i(TAG, "unregister: ALL -> " + activities);
        }

    }

    public void finishAll() {
        Log.e(TAG, "finishAll");
        current = null;

        for (Activity t : activities) {
            t.finish();
            Log.i(TAG, "unregister: OK -> " + t);
        }
        activities.clear();
        Log.i(TAG, "unregister: ALL -> " + activities);
        //为避免退出后立即进入，无有米广告的bug
        System.exit(0);
        // 不要删除，为什么要三秒后完全退出程序，清空后台
/*        Observable.timer(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        System.exit(0);
                    }
                });*/

    }
}
