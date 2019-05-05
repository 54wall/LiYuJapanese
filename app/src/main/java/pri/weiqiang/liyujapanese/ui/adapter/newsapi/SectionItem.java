package pri.weiqiang.liyujapanese.ui.adapter.newsapi;

import android.util.Log;

import java.util.Date;

import pri.weiqiang.liyujapanese.mvp.bean.newsapi.DisplaybleNews;
import pri.weiqaing.common.utils.DateUtils;

/**
 * Created by Administrator on 2017/1/4.
 */

public class SectionItem implements DisplaybleNews {

    private String TAG = SectionItem.class.getSimpleName();

    private String strDate;

    private String formatDate;

    public SectionItem(String strDate) {
        this.strDate = strDate;
    }

    public String getFormatDate() {
        if (strDate == null) {
            return null;
        }

        Date date = new Date();

        Log.e(TAG, "strDate:" + strDate);

        date = DateUtils.str2date(strDate, "yyyyMMdd");
        Log.e(TAG, "date:" + date);
        formatDate = DateUtils.date2str(date);

        return formatDate;
    }

    public void setFormatDate(String formatDate) {
        this.formatDate = formatDate;
    }

    public String getStrDate() {
        return strDate;
    }
}
