package pri.weiqiang.liyujapanese.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import pri.weiqiang.liyujapanese.MyApplication;
import pri.weiqiang.liyujapanese.config.Constants;

public class SharedPreferenceManager {

    private volatile static SharedPreferenceManager instance = null;

    private final SharedPreferences sharedPreferences;

    private SharedPreferenceManager() {

        sharedPreferences = MyApplication.getInstance().getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);

    }

    public static SharedPreferenceManager getInstance() {
        if (instance == null) {
            synchronized (SharedPreferenceManager.class) {
                if (instance == null) {
                    instance = new SharedPreferenceManager();
                }
            }
        }
        return instance;
    }

    public String getString(String key, @Nullable String defValue) {

        return sharedPreferences.getString(key, defValue);

    }

    public void putString(String key, String value) {

        sharedPreferences.edit().putString(key, value).apply();

    }

    public int getInt(String key, int defValue) {

        return sharedPreferences.getInt(key, defValue);

    }

    public void putInt(String key, int value) {
        sharedPreferences.edit().putInt(key, value).apply();

    }

    public boolean getBoolean(String key, boolean defValue) {
        return sharedPreferences.getBoolean(key, defValue);
    }

    public void putBoolean(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }


}
