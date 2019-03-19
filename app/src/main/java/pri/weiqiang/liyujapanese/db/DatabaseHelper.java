package pri.weiqiang.liyujapanese.db;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class DatabaseHelper extends SQLiteAssetHelper {

    public static final String DB_NAME = "android.db";//"vocab.db"
    public static final String DB_TABLE_GOJUON = "gojuon";
    public static final String DB_TABLE_WORDS = "words";
    public static final String DB_TABLE_LESSONS = "lessons";
    public static final String DB_TABLE_FAV = "fav";
    private static final int DB_VERSON = 1;
    private volatile static DatabaseHelper mInstance = null;

    /*使用单例模式避免 以下错误A SQLiteConnection object for database '/data/data/.../databases/....db' was leaked!
    Please fix your application to end transactions in progress properly and to close the database when it is no longer needed*/
    private DatabaseHelper(Context context) {

        super(context, DB_NAME, null, DB_VERSON);

    }

    public static DatabaseHelper getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DatabaseHelper.class) {
                if (mInstance == null) {
                    mInstance = new DatabaseHelper(context);
                }
            }
        }
        return mInstance;
    }
}
