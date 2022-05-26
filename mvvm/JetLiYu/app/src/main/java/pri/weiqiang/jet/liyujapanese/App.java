package pri.weiqiang.jet.liyujapanese;

import android.app.Application;


public class App extends Application {

    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        if (mInstance == null) {
            mInstance = this;
        }
    }


    public static App getInstance() {
        return mInstance;
    }
}