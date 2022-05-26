package pri.weiqiang.liyujapanese.ui.adapter.zhihu;

import android.content.Context;

import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.List;

import pri.weiqiang.liyujapanese.mvp.bean.zhihu.DisplaybleItem;

/**
 * Created by Administrator on 2017/1/4.
 */

public class HYArticleListAdapter extends MultiItemTypeAdapter<DisplaybleItem> {

    public HYArticleListAdapter(Context context, List<DisplaybleItem> datas) {
        super(context, datas);

        addItemViewDelegate(new HomeHeaderItemDelegate());
        addItemViewDelegate(new HomeSectionItemDelegate());
        addItemViewDelegate(new ArticleItemDelegate());
        addItemViewDelegate(new ThemeSectionItemDelegate());
        addItemViewDelegate(new ThemeHeaderItemDelegate());
    }

}
