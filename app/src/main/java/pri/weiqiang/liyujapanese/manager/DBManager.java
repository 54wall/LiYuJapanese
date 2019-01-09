package pri.weiqiang.liyujapanese.manager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pri.weiqiang.liyujapanese.MyApplication;
import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.comparator.GojuonItemComporator;
import pri.weiqiang.liyujapanese.config.Constants;
import pri.weiqiang.liyujapanese.db.JPDatabase;
import pri.weiqiang.liyujapanese.mvp.bean.Book;
import pri.weiqiang.liyujapanese.mvp.bean.GojuonItem;
import pri.weiqiang.liyujapanese.mvp.bean.GojuonMemory;
import pri.weiqiang.liyujapanese.mvp.bean.Lesson;
import pri.weiqiang.liyujapanese.mvp.bean.LessonFav;
import pri.weiqiang.liyujapanese.mvp.bean.Word;
import pri.weiqiang.liyujapanese.utils.ResourceUtils;

public class DBManager {

    private static DBManager instance = null;
    public final String CREATE_FAV = "create table"
            + " " + JPDatabase.DB_TABLE_FAV
            + "("
            + "_id integer primary key autoincrement, "
            + "book_id varchar, "
            + "lesson_id varchar, "
            + "word varchar, "
            + "phonetic varchar,"
            + "translation varchar,"
            + "fav integer,"
            + "cache integer"
            + ")";
    private final String TAG = DBManager.class.getSimpleName();
    private List<GojuonItem> query = null;
    private List<GojuonMemory> gojuonMemoryList = null;
    private List<GojuonMemory> gojuonMemoryChengyu = null;
    private List<GojuonItem> qingYin = null;
    private List<GojuonItem> zhuoYin = null;
    private List<GojuonItem> aoYin = null;
    private List<GojuonItem> qingYinWithoutHeader = null;
    private List<GojuonItem> zhuoYinWithoutHeader = null;
    private List<GojuonItem> aoYinWithoutHeader = null;
    private List<Word> mWordList = null;
    private List<Word> mFavList = null;
    private Map<String, Integer> mLessonFavMap = null;
    private List<Lesson> mLessonList = null;
    private List<LessonFav> mLessonFavList = null;
    private ArrayList<Book> mBookList = null;


    private DBManager() {
    }

    public static synchronized DBManager getInstance() {

        if (instance == null) {
            instance = new DBManager();
        }

        return instance;
    }

    public void addHeaderString(List<GojuonItem> list, int row, int column) {

        for (int i = 1; i < column; i++) {

            GojuonItem item = list.get(i);
            String hiragana = item.getHiragana();
            String katakana = item.getKatakana();
            item.setHiragana(hiragana + ResourceUtils.getString(MyApplication.getInstance(), R.string.column));
            item.setKatakana(katakana + ResourceUtils.getString(MyApplication.getInstance(), R.string.column));

        }

        for (int i = 1; i < row; i++) {

            GojuonItem item = list.get(i * column);
            String hiragana = item.getHiragana();
            String katakana = item.getKatakana();
            item.setHiragana(hiragana + ResourceUtils.getString(MyApplication.getInstance(), R.string.row));
            item.setKatakana(katakana + ResourceUtils.getString(MyApplication.getInstance(), R.string.row));

        }

    }

    public void copyDBToSDcrad() {

        String oldPath = "data/data/pri.weiqiang.liyujapanese/databases/" + JPDatabase.DB_NAME;
        String newPath = Environment.getExternalStorageDirectory() + File.separator + JPDatabase.DB_NAME;

        copyFile(oldPath, newPath);
    }

    /**
     * 复制单个文件
     *
     * @param oldPath String 原文件路径
     * @param newPath String 复制后路径
     * @return boolean
     */
    public void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            File newfile = new File(newPath);
            if (!newfile.exists()) {
                newfile.createNewFile();
            }
            if (oldfile.exists()) { // 文件存在时
                InputStream inStream = new FileInputStream(oldPath); // 读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; // 字节数 文件大小
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();

        }

    }

    public void init() {

        getQingYinWithoutHeader();
        getZhuoYinWithoutHeader();
        getAoYinWithoutHeader();
        Log.e(TAG, TAG + " init finish.");

    }

    public synchronized List<Book> getBooks() {
        //仅第一次调用数据库时填充mBookList
        if (mBookList == null) {
            Log.e(TAG, "mBookList = null");
            SQLiteDatabase db = JPDatabase.getInstance(MyApplication.getInstance()).getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from " + JPDatabase.DB_TABLE_LESSONS, null);
            mBookList = new ArrayList<>();
            mLessonList = new ArrayList<>();
            Book bookItem;
            Lesson lessonItem;
            String curbook = "大家的日本语第一册";
            int i = 0;
            while (cursor.moveToNext() /*&& i < 50*/) {
                i++;
                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                String book = cursor.getString(cursor.getColumnIndex("book"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                int count = cursor.getInt(cursor.getColumnIndex("count"));
                if (book.equals(curbook)) {
                    lessonItem = new Lesson(id, book, title, count);
                    mLessonList.add(lessonItem);
                } else {
                    bookItem = new Book(curbook, mLessonList);
                    mBookList.add(bookItem);
                    mLessonList = new ArrayList<>();
                    lessonItem = new Lesson(id, book, title, count);
                    mLessonList.add(lessonItem);
                    curbook = book;
                }
            }
            if (curbook.equals("新编日语IV")) {
                bookItem = new Book(curbook, mLessonList);
                mBookList.add(bookItem);
            }
            cursor.close();
            db.close();
        }
        return mBookList;
    }

    public synchronized List<Word> queryWord(String lesson) {
        Log.e(TAG, "queryWord lesson:" + lesson);
        SQLiteDatabase db = JPDatabase.getInstance(MyApplication.getInstance()).getReadableDatabase();
        String selection = "lesson_id=?";
        String[] selectionArgs = new String[]{lesson};
        Cursor cursor = db.query(JPDatabase.DB_TABLE_WORDS,
                null, selection, selectionArgs, null, null,
                null);
        mWordList = new ArrayList<>();
        Word item;
        while (cursor.moveToNext()) {

            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String book_id = cursor.getString(cursor.getColumnIndex("book_id"));
            String lesson_id = cursor.getString(cursor.getColumnIndex("lesson_id"));
            String word = cursor.getString(cursor.getColumnIndex("word"));
            String phonetic = cursor.getString(cursor.getColumnIndex("phonetic"));
            String translation = cursor.getString(cursor.getColumnIndex("translation"));
            int fav = cursor.getInt(cursor.getColumnIndex("fav"));
            int cache = cursor.getInt(cursor.getColumnIndex("cache"));
            item = new Word(id, book_id, lesson_id, word, phonetic, translation, fav, cache);
//            Log.e(TAG, "word:" + item.toString());
            mWordList.add(item);
        }
        cursor.close();
        db.close();

        return mWordList;
    }

    public void updateFav(Word word) {
        Log.e(TAG, "updateFav fav:" + word.getFav());
        SQLiteDatabase db = JPDatabase.getInstance(MyApplication.getInstance()).getWritableDatabase();
        ContentValues values = new ContentValues();
        if (word.getFav() == 0) {
            values.put("fav", 1);
        } else {
            values.put("fav", 0);
        }
        db.update(JPDatabase.DB_TABLE_WORDS, values, "_id = ?", new String[]{String.valueOf(word.getId())});
        db.close();
        Log.e(TAG, "updateFav fav:" + word.getFav());

    }

    public synchronized List<Word> getAllFav() {
        Log.e(TAG, "getAllFav!!!!");
        //收藏这个表，因为经常更新，所以即使mFavList不为null也不能直接使用
        if (isTableExist(JPDatabase.DB_TABLE_FAV)) {
            SQLiteDatabase db = JPDatabase.getInstance(MyApplication.getInstance()).getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from " + JPDatabase.DB_TABLE_FAV, null);
            mFavList = new ArrayList<>();
            Word item;
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                String book_id = cursor.getString(cursor.getColumnIndex("book_id"));
                String lesson_id = cursor.getString(cursor.getColumnIndex("lesson_id"));
                String word = cursor.getString(cursor.getColumnIndex("word"));
                String phonetic = cursor.getString(cursor.getColumnIndex("phonetic"));
                String translation = cursor.getString(cursor.getColumnIndex("translation"));
                int fav = cursor.getInt(cursor.getColumnIndex("fav"));
                int cache = cursor.getInt(cursor.getColumnIndex("cache"));
                item = new Word(id, book_id, lesson_id, word, phonetic, translation, fav, cache);
                Log.e(TAG, "getAllFav word:" + item.toString());
                mFavList.add(item);

            }
            cursor.close();
            db.close();
        }

        return mFavList;
    }

    public synchronized List<Word> getFav(String lessonId) {
        Log.e(TAG, "getAllFav!!!!");
        //收藏这个表，因为经常更新，所以即使mFavList不为null也不能直接使用
        if (isTableExist(JPDatabase.DB_TABLE_FAV)) {
            SQLiteDatabase db = JPDatabase.getInstance(MyApplication.getInstance()).getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from " + JPDatabase.DB_TABLE_FAV, null);
            mFavList = new ArrayList<>();
            Word item;
            while (cursor.moveToNext()) {
                String lesson_id = cursor.getString(cursor.getColumnIndex("lesson_id"));
                if (lesson_id.equals(lessonId)) {
                    int id = cursor.getInt(cursor.getColumnIndex("_id"));
                    String book_id = cursor.getString(cursor.getColumnIndex("book_id"));
                    String word = cursor.getString(cursor.getColumnIndex("word"));
                    String phonetic = cursor.getString(cursor.getColumnIndex("phonetic"));
                    String translation = cursor.getString(cursor.getColumnIndex("translation"));
                    int fav = cursor.getInt(cursor.getColumnIndex("fav"));
                    int cache = cursor.getInt(cursor.getColumnIndex("cache"));
                    item = new Word(id, book_id, lesson_id, word, phonetic, translation, fav, cache);
                    Log.e(TAG, "getAllFav word:" + item.toString());
                    mFavList.add(item);
                }
            }
            cursor.close();
            db.close();
        }

        return mFavList;
    }

    public synchronized List<LessonFav> getFavLesson() {
        Log.e(TAG, "getFavLesson!!!!");
        //收藏这个表，因为经常更新，所以即使mFavList不为null也不能直接使用
        if (isTableExist(JPDatabase.DB_TABLE_FAV)) {
            SQLiteDatabase db = JPDatabase.getInstance(MyApplication.getInstance()).getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from " + JPDatabase.DB_TABLE_FAV, null);
            mLessonFavMap = new HashMap<>();
            mLessonFavList = new ArrayList<>();
            while (cursor.moveToNext()) {
                String lesson_id = cursor.getString(cursor.getColumnIndex("lesson_id"));
                Log.e(TAG, "getAllFav lesson_id:" + lesson_id);
                if (mLessonFavMap.containsKey(lesson_id)) {
                    int index = mLessonFavMap.get(lesson_id) + 1;
                    mLessonFavMap.put(lesson_id, index);
                } else {
                    mLessonFavMap.put(lesson_id, 1);
                }
            }
            cursor.close();
            db.close();
        }
        LessonFav lessonFav;
        for (Map.Entry<String, Integer> entry : mLessonFavMap.entrySet()) {
            lessonFav = new LessonFav(entry.getKey(), entry.getValue());
            mLessonFavList.add(lessonFav);
        }

        return mLessonFavList;
    }

    public synchronized void insertFav(Word word) {
        Log.e(TAG, "insertFav fav:" + word.getFav());
        if (!isTableExist(JPDatabase.DB_TABLE_FAV)) {
            createFav();
        }
        SQLiteDatabase db = JPDatabase.getInstance(MyApplication.getInstance()).getWritableDatabase();
        ContentValues values = new ContentValues();
        // 开始组装第一条数据
        values.put("_id", word.getId());
        values.put("book_id", word.getBook_id());
        values.put("lesson_id", word.getLesson_id());
        values.put("word", word.getWord());
        values.put("phonetic", word.getPhonetic());
        values.put("translation", word.getTranslation());
        values.put("fav", word.getFav());
        values.put("cache", word.getCache());
        db.insert(JPDatabase.DB_TABLE_FAV, null, values); // 插入第一条数据
        db.close();
    }

    public synchronized void delFav(Word word) {
        Log.e(TAG, "delFav fav:" + word.getFav());
        if (!isTableExist(JPDatabase.DB_TABLE_FAV)) {
            createFav();
        }
        SQLiteDatabase db = JPDatabase.getInstance(MyApplication.getInstance()).getWritableDatabase();
        db.delete(JPDatabase.DB_TABLE_FAV, "_id = ?", new String[]{String.valueOf(word.getId())});
        db.close();
    }

    public synchronized void createFav() {
        Log.e(TAG, "createFav");
        SQLiteDatabase db = JPDatabase.getInstance(MyApplication.getInstance()).getWritableDatabase();
        db.execSQL(CREATE_FAV);
        db.close();
    }

    public synchronized List<GojuonItem> query() {

        if (query == null) {
            SQLiteDatabase db = JPDatabase.getInstance(MyApplication.getInstance()).getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from " + JPDatabase.DB_TABLE_GOJUON, null);
            query = new ArrayList<>();
            GojuonItem item;
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                int row = cursor.getInt(cursor.getColumnIndex("row"));
                int column = cursor.getInt(cursor.getColumnIndex("column"));
                String hiragana = cursor.getString(cursor.getColumnIndex("hiragana"));
                String katakana = cursor.getString(cursor.getColumnIndex("katakana"));
                String rome = cursor.getString(cursor.getColumnIndex("rome"));
                int category = cursor.getInt(cursor.getColumnIndex("category"));
                int type = cursor.getInt(cursor.getColumnIndex("type"));
                boolean existed = cursor.getInt(cursor.getColumnIndex("existed")) == 1;

                item = new GojuonItem(id, row, column, hiragana, katakana, rome, category, type, existed);

                query.add(item);
            }
            cursor.close();
            db.close();
        }

        return query;
    }

    public synchronized List<GojuonMemory> getGojuonMemory() {

        if (gojuonMemoryList == null) {
            SQLiteDatabase db = JPDatabase.getInstance(MyApplication.getInstance()).getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from " + JPDatabase.DB_TABLE_GOJUON, null);
            gojuonMemoryList = new ArrayList<>();
            GojuonMemory item;
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String memory = cursor.getString(cursor.getColumnIndex("memory"));
                if (memory != null) {
                    String hiragana = cursor.getString(cursor.getColumnIndex("hiragana"));
                    String katakana = cursor.getString(cursor.getColumnIndex("katakana"));
                    String rome = cursor.getString(cursor.getColumnIndex("rome"));
                    String chengyu = cursor.getString(cursor.getColumnIndex("chengyu"));
                    item = new GojuonMemory(id, hiragana, katakana, rome, memory, chengyu);
                    gojuonMemoryList.add(item);
                }
            }
            cursor.close();
            db.close();
        }

        return gojuonMemoryList;
    }

    public synchronized List<GojuonMemory> getGojuonChengyu() {

        if (gojuonMemoryChengyu == null) {
            SQLiteDatabase db = JPDatabase.getInstance(MyApplication.getInstance()).getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from " + JPDatabase.DB_TABLE_GOJUON, null);
            gojuonMemoryChengyu = new ArrayList<>();
            GojuonMemory item;
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String chengyu = cursor.getString(cursor.getColumnIndex("chengyu"));
                if (chengyu != null) {
                    String hiragana = cursor.getString(cursor.getColumnIndex("hiragana"));
                    String katakana = cursor.getString(cursor.getColumnIndex("katakana"));
                    String rome = cursor.getString(cursor.getColumnIndex("rome"));
                    String memory = cursor.getString(cursor.getColumnIndex("memory"));
                    item = new GojuonMemory(id, hiragana, katakana, rome, memory, chengyu);
                    gojuonMemoryChengyu.add(item);
                }
            }
            cursor.close();
            db.close();
        }

        return gojuonMemoryChengyu;
    }

    public synchronized List<GojuonItem> getQingYin() {

        if (qingYin == null) {

            qingYin = new ArrayList<>();
            List<GojuonItem> query = query();
            for (GojuonItem item : query) {

                if (item.getCategory() == Constants.CATEGORY_QINGYIN) {
                    qingYin.add(item);
                }

            }

            Collections.sort(qingYin, new GojuonItemComporator());


        }

        return qingYin;
    }

    public List<GojuonItem> getZhuoYin() {

        if (zhuoYin == null) {

            zhuoYin = new ArrayList<>();
            List<GojuonItem> query = query();
            for (GojuonItem item : query) {

                if (item.getCategory() == Constants.CATEGORY_ZHUOYIN) {
                    zhuoYin.add(item);
                }

            }

            Collections.sort(zhuoYin, new GojuonItemComporator());


        }


        return zhuoYin;
    }

    public List<GojuonItem> getAoYin() {

        if (aoYin == null) {

            aoYin = new ArrayList<>();
            List<GojuonItem> query = query();
            for (GojuonItem item : query) {

                if (item.getCategory() == Constants.CATEGORY_AOYIN) {
                    aoYin.add(item);
                }

            }

            Collections.sort(aoYin, new GojuonItemComporator());


        }

        return aoYin;
    }

    public List<GojuonItem> getQingYinWithoutHeader() {

        if (qingYinWithoutHeader == null) {

            qingYinWithoutHeader = new ArrayList<>();
            List<GojuonItem> query = getQingYin();
            for (GojuonItem item : query) {

                if (item.getRow() != 0 && item.getColumn() != 0 && item.isExisted()) {
                    qingYinWithoutHeader.add(item);
                }

            }
        }


        return qingYinWithoutHeader;
    }

    public List<GojuonItem> getZhuoYinWithoutHeader() {

        if (zhuoYinWithoutHeader == null) {

            zhuoYinWithoutHeader = new ArrayList<>();
            List<GojuonItem> query = getZhuoYin();
            for (GojuonItem item : query) {

                if (item.getRow() != 0 && item.getColumn() != 0 && item.isExisted()) {
                    zhuoYinWithoutHeader.add(item);
                }

            }
        }

        return zhuoYinWithoutHeader;
    }

    public List<GojuonItem> getAoYinWithoutHeader() {

        if (aoYinWithoutHeader == null) {

            aoYinWithoutHeader = new ArrayList<>();
            List<GojuonItem> query = getAoYin();
            for (GojuonItem item : query) {

                if (item.getRow() != 0 && item.getColumn() != 0 && item.isExisted()) {
                    aoYinWithoutHeader.add(item);
                }

            }
        }

        return aoYinWithoutHeader;
    }

    /**
     * 判断数据库中某张表是否存在
     */
    private boolean isTableExist(String tableName) {
        boolean result = false;
        if (tableName == null) {
            return false;
        }
        SQLiteDatabase db = JPDatabase.getInstance(MyApplication.getInstance()).getReadableDatabase();
        Cursor cursor = null;

        try {
            String sql = "select count(*) as c from Sqlite_master  where type ='table' and name ='" + tableName.trim() + "' ";
            cursor = db.rawQuery(sql, null);
            if (cursor.moveToNext()) {
                int count = cursor.getInt(0);
                if (count > 0) {
                    result = true;
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        return result;
    }

}
