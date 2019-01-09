package pri.weiqiang.liyujapanese.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2017/1/5.
 */

public class DateUtils {

    private static final String PATTERN_DATE = "MM月dd日";
    private static final String PATTERN_WEEKDAY = "EEEE";
    private static final String PATTERN_SPLIT = " ";
    private static final String PATTERN = PATTERN_DATE + PATTERN_SPLIT + PATTERN_WEEKDAY;
    private static String TAG = DateUtils.class.getSimpleName();

    public static String date2str(Date date) {
        return date2str(date, PATTERN);
    }

    private static String date2str(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
        return sdf.format(date);
    }

    public static Date str2date(String str) {
        return str2date(str, PATTERN);
    }

    /**
     * 获取今天日期 格式为yyyy-MM-dd
     */
    public static String getCurDate() {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE));
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Log.e(TAG, "前n天==" + dft.format(endDate));
        return dft.format(endDate);
    }

    public static Date str2date(String str, String format) {
        Log.e(TAG, "str2date! str=" + str);
        Date date = null;
        try {
            if (str != null) {
                SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
                date = sdf.parse(str);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "date " + date);
        return date;
    }


    /**
     * 获得指定日期的前一天
     *
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    public static String getSpecifiedDayBefore(String specifiedDay) {
//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);

        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayBefore;
    }

    /**
     * 获得指定日期的后一天
     *
     * @param specifiedDay
     * @return
     */
    public static String getSpecifiedDayAfter(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);

        String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayAfter;
    }

}
