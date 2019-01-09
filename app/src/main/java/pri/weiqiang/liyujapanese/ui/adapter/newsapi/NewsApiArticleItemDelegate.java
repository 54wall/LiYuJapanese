package pri.weiqiang.liyujapanese.ui.adapter.newsapi;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.config.Constants;
import pri.weiqiang.liyujapanese.loader.GlideImageLoader;
import pri.weiqiang.liyujapanese.mvp.bean.newsapi.ArticleStructure;
import pri.weiqiang.liyujapanese.mvp.bean.newsapi.DisplaybleNews;
import pri.weiqiang.liyujapanese.ui.activity.WebViewActivity;

/**
 * Created by yiyi on 2017/1/6.
 */

class NewsApiArticleItemDelegate implements ItemViewDelegate<DisplaybleNews> {
    private String TAG = NewsApiArticleItemDelegate.class.getSimpleName();

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_story_list_content;
    }

    @Override
    public boolean isForViewType(DisplaybleNews item, int position) {
        return item instanceof ArticleStructure;
    }

    @Override
    public void convert(ViewHolder holder, DisplaybleNews displaybleItem, int position) {
        final Context context = holder.getConvertView().getContext();

        final ArticleStructure articleStructure = (ArticleStructure) displaybleItem;
        Log.e(TAG, "" + articleStructure.getContent());
        holder.setText(R.id.story_title_tv, articleStructure.getTitle());

        if (articleStructure.getUrlToImage() != null) {
            ImageView stroyImg = holder.getView(R.id.story_iv);
            GlideImageLoader.getInstance().displayImage(context, articleStructure.getUrlToImage(), stroyImg);
            holder.getView(R.id.multi_pic_iv).setVisibility(View.VISIBLE);
            holder.getView(R.id.story_frame_iv).setVisibility(View.VISIBLE);
        } else {
            holder.getView(R.id.story_frame_iv).setVisibility(View.GONE);
        }

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, ArticleDetailActivity.class);
//                intent.putExtra(Constants.FLAG_ZHIHU_ARTICLE_ID, articleStructure.getUrl());
//                intent.putExtra(Constants.FLAG_ZHIHU_ARTICLE_TITLE, articleStructure.getTitle());
//                context.startActivity(intent);
                Log.e(TAG, "getUrl:" + articleStructure.getUrl());
                Intent browserIntent = new Intent(context, WebViewActivity.class);
                browserIntent.putExtra(Constants.INTENT_URL, articleStructure.getUrl());
                context.startActivity(browserIntent);
            }
        });

    }
}
