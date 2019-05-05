package pri.weiqiang.liyujapanese.ui.adapter.zhihu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import pri.weiqiang.liyujapanese.R;
import pri.weiqaing.common.config.Constants;
import pri.weiqaing.common.loader.GlideImageLoader;
import pri.weiqiang.liyujapanese.mvp.bean.zhihu.DisplaybleItem;
import pri.weiqiang.liyujapanese.ui.activity.ArticleDetailActivity;
import pri.weiqaing.common.widget.banner.Banner;

/**
 * Created by Administrator on 2017/1/4.
 */

public class HomeHeaderItemDelegate implements ItemViewDelegate<DisplaybleItem>, Banner.OnBannerClickListener {

    private static final String TAG = "HomeHeaderItemDelegate";

    private Context mContext;
    private List<Integer> mIds;
    private List<String> mTitles;

    @Override
    public int getItemViewLayoutId() {
        return R.layout.home_header;
    }

    @Override
    public boolean isForViewType(DisplaybleItem item, int position) {
        return item instanceof HomeHeaderItem;
    }

    @Override
    public void convert(ViewHolder holder, DisplaybleItem displaybleItem, int position) {
        mContext = holder.getConvertView().getContext();
        Banner banner = holder.getView(R.id.banner);
        HomeHeaderItem item = (HomeHeaderItem) displaybleItem;
        mIds = item.getIds();
        mTitles = item.getTitles();
        banner.setImages(item.getImages())
                .setBannerTitles(mTitles)
                .setImageLoader(GlideImageLoader.getInstance())
                .setOnBannerClickListener(this)
                .start();
    }

    @Override
    public void OnBannerClick(int position) {
        if (mIds == null) {
            Log.e(TAG, "error : id列表为空！");
            return;
        }
        Intent intent = new Intent(mContext, ArticleDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.FLAG_ZHIHU_ARTICLE_ID, mIds.get(position));
        bundle.putString(Constants.FLAG_ZHIHU_ARTICLE_TITLE, mTitles.get(position));
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

}
