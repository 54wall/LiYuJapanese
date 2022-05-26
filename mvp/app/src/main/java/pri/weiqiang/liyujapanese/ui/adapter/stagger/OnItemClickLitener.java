package pri.weiqiang.liyujapanese.ui.adapter.stagger;

import android.view.View;

/**
 * Created by android on 2016/6/4.
 */
public interface OnItemClickLitener {
    /*点击事件*/
    void onItemClick(View view, int position);

    /*长按事件*/
    void onItemLongClick(View view, int position);
}
