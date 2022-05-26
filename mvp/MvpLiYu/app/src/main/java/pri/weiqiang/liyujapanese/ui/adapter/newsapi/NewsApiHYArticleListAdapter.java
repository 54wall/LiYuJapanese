package pri.weiqiang.liyujapanese.ui.adapter.newsapi;

import android.content.Context;

import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.List;

import pri.weiqiang.liyujapanese.mvp.bean.newsapi.DisplaybleNews;


/**
 * Created by Administrator on 2017/1/4.
 */

public class NewsApiHYArticleListAdapter extends MultiItemTypeAdapter<DisplaybleNews> {

    public NewsApiHYArticleListAdapter(Context context, List<DisplaybleNews> datas) {
        super(context, datas);

//        addItemViewDelegate(new HeaderItemDelegate());
        addItemViewDelegate(new SectionItemDelegate());
        addItemViewDelegate(new NewsApiArticleItemDelegate());
//        addItemViewDelegate(new ThemeSectionItemDelegate());
//        addItemViewDelegate(new ThemeHeaderItemDelegate());
    }

}
