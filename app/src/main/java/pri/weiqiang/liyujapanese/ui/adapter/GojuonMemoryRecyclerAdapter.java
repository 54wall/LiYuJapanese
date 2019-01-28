package pri.weiqiang.liyujapanese.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.config.Constants;
import pri.weiqiang.liyujapanese.mvp.bean.GojuonMemory;

public class GojuonMemoryRecyclerAdapter extends RecyclerView.Adapter<GojuonMemoryRecyclerAdapter.ViewHolder> {

    private static final String TAG = GojuonMemoryRecyclerAdapter.class.getSimpleName();
    List<GojuonMemory> list;
    OnItemClickListener onItemClickListener;
    OnItemLongClickListener onItemLongClickListener;
    int type;

    public GojuonMemoryRecyclerAdapter(int type, List<GojuonMemory> list) {

        this.type = type;
        this.list = list;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gojuon_memory, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.bean = list.get(position);
        if (type == Constants.GOJUON_MEMORY) {
            holder.mTvWord.setText(holder.bean.getHiragana() + "(" + holder.bean.getKatakana() + ")");
            holder.mTvMemory.setText(holder.bean.getMemory());
        } else {
            holder.mTvWord.setText(holder.bean.getChengyu());
            holder.mTvMemory.setVisibility(View.GONE);
        }
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onItemClickListener != null) {
                    onItemClickListener.onClick(holder.bean);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemClickListener {

        void onClick(GojuonMemory bean);

    }

    public interface OnItemLongClickListener {

        void onLongClick(GojuonMemory bean);

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final View view;
        public final TextView mTvWord;
        public final TextView mTvMemory;


        public GojuonMemory bean;

        public ViewHolder(View itemView) {

            super(itemView);
            this.view = itemView;
            this.mTvWord = itemView.findViewById(R.id.tv_word);
            this.mTvMemory = itemView.findViewById(R.id.tv_memory);
            itemView.setOnClickListener(this);
        }


        /**
         * recyclerView是不提供onItemClickListener的。所以列表的点击事件需要我们自己来实现
         *
         * @param v
         */
        @Override
        public void onClick(View v) {

            notifyItemChanged(getAdapterPosition());

        }
    }


}
