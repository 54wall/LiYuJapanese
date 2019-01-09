package pri.weiqiang.liyujapanese.ui.adapter.newsapi;

import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.mvp.bean.newsapi.DisplaybleNews;

/**
 * Created by Administrator on 2017/1/4.
 */

class SectionItemDelegate implements ItemViewDelegate<DisplaybleNews> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_story_list_section_head;
    }

    @Override
    public boolean isForViewType(DisplaybleNews item, int position) {
        return item instanceof SectionItem;
    }

    @Override
    public void convert(ViewHolder holder, DisplaybleNews displaybleItem, int position) {
        //根据新闻布局来设定position的数值
        if (position == 0) {
            holder.setText(R.id.story_list_header, "今日热闻");
        } else {
//            holder.setText(R.id.story_list_header, ((SectionItem) displaybleItem).getFormatDate());
            holder.setText(R.id.story_list_header, ((SectionItem) displaybleItem).getStrDate());
        }
    }
}
