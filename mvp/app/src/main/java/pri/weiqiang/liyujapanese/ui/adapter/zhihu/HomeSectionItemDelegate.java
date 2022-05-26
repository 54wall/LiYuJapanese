package pri.weiqiang.liyujapanese.ui.adapter.zhihu;

import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.mvp.bean.zhihu.DisplaybleItem;

/**
 * Created by Administrator on 2017/1/4.
 */

public class HomeSectionItemDelegate implements ItemViewDelegate<DisplaybleItem> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_story_list_section_head;
    }

    @Override
    public boolean isForViewType(DisplaybleItem item, int position) {
        return item instanceof HomeSectionItem;
    }

    @Override
    public void convert(ViewHolder holder, DisplaybleItem displaybleItem, int position) {
        if (position == 1) {
            holder.setText(R.id.story_list_header, "今日热闻");
        } else {
            holder.setText(R.id.story_list_header, ((HomeSectionItem) displaybleItem).getFormatDate());
        }
    }
}
