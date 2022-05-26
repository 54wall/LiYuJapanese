package pri.weiqiang.liyujapanese.ui.adapter.zhihu;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import pri.weiqaing.common.config.Constants;
import pri.weiqaing.common.loader.GlideImageLoader;
import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.mvp.bean.zhihu.DisplaybleItem;
import pri.weiqiang.liyujapanese.mvp.bean.zhihu.StoriesEntity;
import pri.weiqiang.liyujapanese.ui.activity.ArticleDetailActivity;

/**
 * Created by yiyi on 2017/1/6.
 */

public class ArticleItemDelegate implements ItemViewDelegate<DisplaybleItem> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_story_list_content;
    }

    @Override
    public boolean isForViewType(DisplaybleItem item, int position) {
        return item instanceof StoriesEntity;
    }

    @Override
    public void convert(ViewHolder holder, DisplaybleItem displaybleItem, int position) {
        final Context context = holder.getConvertView().getContext();

        final StoriesEntity storiesEntity = (StoriesEntity) displaybleItem;

        holder.setText(R.id.story_title_tv, storiesEntity.getTitle());

        if (storiesEntity.getImages() != null) {
            ImageView stroyImg = holder.getView(R.id.story_iv);
            GlideImageLoader.getInstance().displayImage(context, storiesEntity.getImages().get(0), stroyImg);
            holder.getView(R.id.multi_pic_iv).setVisibility(View.VISIBLE);
            holder.getView(R.id.story_frame_iv).setVisibility(View.VISIBLE);
        } else {
            holder.getView(R.id.story_frame_iv).setVisibility(View.GONE);
        }

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ArticleDetailActivity.class);
                intent.putExtra(Constants.FLAG_ZHIHU_ARTICLE_ID, storiesEntity.getId());
                intent.putExtra(Constants.FLAG_ZHIHU_ARTICLE_TITLE, storiesEntity.getTitle());
                context.startActivity(intent);
            }
        });

    }
}
