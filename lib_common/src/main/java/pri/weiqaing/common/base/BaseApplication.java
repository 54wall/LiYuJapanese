package pri.weiqaing.common.base;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.Utils;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.MobclickAgent;

import pri.weiqaing.common.config.Constants;
import pri.weiqaing.common.manager.SharedPreferenceManager;

public class BaseApplication extends Application {

    public static int CURRENT_ITEM = 0;
    public static int TYPE_MING = Constants.TYPE_HIRAGANA;
    public static int FROM_LAN = 0;
    public static int TO_LAN = 0;
    public static boolean ISWIFI = false;
    private static BaseApplication instance = null;

    public synchronized static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Bugly
        CrashReport.initCrashReport(getApplicationContext(), "0a341dafed", false);
        //使用LeakCanary检测内存泄露
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        instance = this;

        setDayNightMode(SharedPreferenceManager.getInstance().getString(Constants.MODE_THEME, Constants.MODE_DAY));
        Utils.init(this);
        ISWIFI = NetworkUtils.isWifiConnected();

        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);

    }

    public void setDayNightMode(String mode) {

        switch (mode) {
            case Constants.MODE_AUTO:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
                break;
            case Constants.MODE_NIGHT:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case Constants.MODE_DAY:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            default:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;

        }

    }


}
