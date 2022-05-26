package pri.weiqaing.common.config;

import android.os.Environment;

public class Constants {

    public static final int FR_WORDS = 0;
    public static final int FR_FAV_WORDS = 1;
    public static final int FR_FAV_LESSON = 2;
    public static final int FR_LESSONS = 3;
    public static final int FR_GOJUON = 4;
    public static final int FR_MEMORY = 5;
    public static final int FR_TRANSLATE = 6;
    public static final int FR_GAME = 7;
    public static final int FR_PIXIV_ILLUST = 8;
    public static final int FR_ZHIHU = 9;
    public static final int FR_NEWS = 10;

    //Used by intents
    public static final String INTENT_URL = "url";
    //Used to save the instance of the title of Toolbar.
    public static final String TITLE_WEBVIEW_KEY = "save_text_webView";

    public static final String NEWS_COUNTRY = "jp";
    public static final String NEWS_CATEGORY_GENERAL = "general";
    public static final String NEWS_CATEGORY_TECHNOLOGY = "technology";
    public static final String NEWS_CATEGORY_SCIENCE = "science";
    public static final String NEWS_CATEGORY_BUSINESS = "business";
    public static final String NEWS_CATEGORY_ENTERTAINMENT = "entertainment";

    public static final String BAIDU_TRANSLATE_URL = "http://api.fanyi.baidu.com";
    public static final String BAIDU_TRANSLATE_APPID = "不再上传秘钥";
    public static final String BAIDU_TRANSLATE_SECRETKEY = "不再上传秘钥";

    public static final String PIXIV_URL = "http://www.pixiv.net";
    public static final String ZHIHU_BASE_URL = "http://news-at.zhihu.com/api/4/";

    public static final String NEWS_API_BASE_URL = "https://newsapi.org/v2/";
    public static final String API_NEWS_KEY = "ef7c2f7191ec474cbdcd564382744e38";
    public static final String API_NEWS_PAGE = "30";

    public static final String FLAG_ZHIHU_ARTICLE_ID = "article_id";
    public static final String FLAG_ZHIHU_ARTICLE_TITLE = "article_title";
    public static final String FLAG_LESSON = "lesson";
    public static final String FLAG_IS_EXPANDABLE = "is_expandable";
    public static final int OK = 200;
    public static final String CATEGORY_YIN = "category_yin";
    public static final String CATEGORY_GOJUON_MEMORY = "category_gojuon_memory";
    public static final int GOJUON_MEMORY = 0;
    public static final int GOJUON_CHENGYU = 1;
    public static final int CATEGORY_QINGYIN = 1;
    public static final int CATEGORY_ZHUOYIN = 2;
    public static final int CATEGORY_AOYIN = 3;
    public static final int ROW_QINGYIN = 12;
    public static final int COLUMN_QINGYIN = 6;
    public static final int ROW_ZHUOYIN = 6;
    public static final int COLUMN_ZHUOYIN = 6;
    public static final int COLUMN_CHENGYU = 3;
    public static final int ROW_AOYIN = 12;
    public static final int COLUMN_AOYIN = 4;
    public static final int TYPE_HIRAGANA = 666;
    public static final int TYPE_KATAKANA = 999;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_ITEM = 1;
    public static final int TYPE_ITEM_DISABLE = 2;
    public static final String PREF_NAME = "pref_jpstart";
    public static final String CURRENT_LESSON_ID = "current_lesson_id";
    public static final int DEFAULT_LESSON_ID = 82;
    public static final String CURRENT_LESSON = "current_lesson";
    public static final String DEFAULT_LESSON = "新标日初级_01";
    public static final String FLAG_TIPS_WORD = "flag_tips_word";
    public static final String FLAG_TIPS_JPSTART = "flag_tips_jpstart";
    public static final String FLAG_TIPS_TRANSLATE = "flag_tips_translate";
    public static final String FLAG_TIPS_MEMORY = "flag_tips_memory";
    public static final String FLAG_TIPS_PIXIVILLUST = "flag_tips_pixivillust";
    public static final String FLAG_TIPS_WIFI = "flag_tips_wifi";
    public static final String MODE_ILLUST = "mode_illust";
    public static final String IMG_URL = "img_url";
    public static final String IMG_ID = "img_id";
    public static final String FILEDIR_ROOT = Environment.getExternalStorageDirectory() + "/JPStart";
    public static final String FILETYPE_JPG = ".jpg";
    public static final String TTS_TYPE = "tts_type";
    public static final String TTS_MALE_01 = "male01";
    public static final String MODE_THEME = "mode_theme";
    public static final String MODE_AUTO = "auto";
    public static final String MODE_DAY = "day";
    public static final String MODE_NIGHT = "night";
    public static final String URL_AUTHOR = "https://github.com/54wall";
    public static final String IMG_BANNER = "img_banner";
    public static final String ALLOW_CONNECT_WITHOUT_WIFI = "allow_connect_without_wifi";
    public static final String HIGHEST_SCORE = "highest_score";

    private Constants() {
        throw new RuntimeException("异常");
    }


}
