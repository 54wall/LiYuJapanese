package pri.weiqiang.liyujapanese.ui.adapter.zhihu;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import pri.weiqaing.common.loader.GlideImageLoader;
import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.mvp.bean.zhihu.DisplaybleItem;
import pri.weiqiang.liyujapanese.mvp.bean.zhihu.EditorsEntity;

/**
 * Created by Administrator on 2017/1/6.
 */

public class ThemeSectionItemDelegate implements ItemViewDelegate<DisplaybleItem> {
    private String TAG = ThemeSectionItemDelegate.class.getSimpleName();

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_theme_story_list_section;
    }

    @Override
    public boolean isForViewType(DisplaybleItem item, int position) {
        return item instanceof ThemeSectionItem;
    }

    @Override
    public void convert(ViewHolder holder, DisplaybleItem displaybleItem, int position) {
        LinearLayout layout = holder.getView(R.id.editor_layout);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(45, 45);
        layoutParams.setMargins(30, 0, 0, 0);
        layoutParams.gravity = Gravity.CENTER_VERTICAL;
        layout.removeViews(1, layout.getChildCount() - 1);
        final List<EditorsEntity> editors = ((ThemeSectionItem) displaybleItem).getEditors();
        final Context mContext = holder.getConvertView().getContext();
        int imgCount = editors.size();
        for (int i = 0; i < imgCount; ++i) {
            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(layoutParams);
            //Glide.with(mContext).load(editors.get(i).getAvatar()).bitmapTransform(new CropCircleTransformation(mContext)).into(imageView);
            GlideImageLoader.getInstance().displayCircleImage(mContext, editors.get(i).getAvatar(), imageView);
            layout.addView(imageView);
        }

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "EditorListActivity is not finished.");
//                Intent intent = new Intent(mContext, EditorListActivity.class);
//                intent.putParcelableArrayListExtra("editors", (ArrayList<? extends Parcelable>) editors);
//                mContext.startActivity(intent);
            }
        });
    }
}
