package pri.weiqiang.liyujapanese.ui.adapter.zhihu;

import java.util.Date;

import pri.weiqiang.liyujapanese.mvp.bean.zhihu.DisplaybleItem;
import pri.weiqiang.liyujapanese.utils.DateUtils;

/**
 * Created by Administrator on 2017/1/4.
 */

public class HomeSectionItem implements DisplaybleItem {

    private String strDate;

    private String formatDate;

    public HomeSectionItem(String strDate) {
        this.strDate = strDate;
    }

    public String getFormatDate() {
        if (strDate == null) {
            return null;
        }

        Date date = new Date();
        date = DateUtils.str2date(strDate, "yyyyMMdd");
        formatDate = DateUtils.date2str(date);
        return formatDate;
    }

    public void setFormatDate(String formatDate) {
        this.formatDate = formatDate;
    }
}
