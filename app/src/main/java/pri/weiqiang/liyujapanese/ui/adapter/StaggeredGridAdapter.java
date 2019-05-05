package pri.weiqiang.liyujapanese.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.mvp.bean.dic.LessonFav;
import pri.weiqiang.liyujapanese.ui.adapter.stagger.OnItemClickLitener;

/**
 * Created by android on 2016/6/4.
 */
public class StaggeredGridAdapter extends RecyclerView.Adapter<StaggeredGridHolder> {
    private View view;
    private List<Integer> heights;
    private LayoutInflater mInflater;
    private OnItemClickLitener mOnItemClickLitener;
    private List<LessonFav> list;

    public StaggeredGridAdapter(Context context, List<LessonFav> list) {
        mInflater = LayoutInflater.from(context);
        heights = new ArrayList<>();
        this.list = list;
        for (int i = 0; i < this.list.size(); i++) {
            heights.add(220 + this.list.get(i).getCount() * 15);
        }
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public StaggeredGridHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //利用反射将item的布局加载出来
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lesson_fav, null);
        //new一个我们的ViewHolder，findViewById操作都在LinearHolder的构造方法中进行了
        return new StaggeredGridHolder(view);
    }

    @Override
    public void onBindViewHolder(final StaggeredGridHolder holder, int position) {
        LayoutParams layoutParams = holder.mLlContent.getLayoutParams();
        layoutParams.height = heights.get(position);
        int index = list.get(position).getLesson_id().indexOf('_');
        String book = list.get(position).getLesson_id().substring(0, index);
        String lesson = list.get(position).getLesson_id().substring(index + 1);
        holder.mTvTitle.setText(book);
        holder.mTvLesson.setText("第" + lesson + "课");
        holder.mTvCount.setText(list.get(position).getCount() + "");
        holder.mLlContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getLayoutPosition();
                mOnItemClickLitener.onItemClick(holder.itemView, pos);
            }
        });
        holder.mLlContent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int pos = holder.getLayoutPosition();
                mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public String getItemLessonId(int postion) {
        return list.get(postion).getLesson_id();
    }

}

class StaggeredGridHolder extends RecyclerView.ViewHolder {

    TextView mTvTitle;
    TextView mTvLesson;
    TextView mTvCount;
    LinearLayout mLlContent;

    public StaggeredGridHolder(final View view) {
        super(view);
        mTvTitle = view.findViewById(R.id.tv_title);
        mLlContent = view.findViewById(R.id.ll_content);
        mTvLesson = view.findViewById(R.id.tv_lesson);
        mTvCount = view.findViewById(R.id.tv_count);
    }
}

