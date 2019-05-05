package pri.weiqiang.liyujapanese.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import pri.weiqaing.common.base.BaseApplication;
import pri.weiqiang.liyujapanese.R;
import pri.weiqaing.common.config.Constants;
import pri.weiqiang.liyujapanese.mvp.bean.gojuon.GojuonItem;


public class GojuonRecyclerAdapter extends RecyclerView.Adapter<GojuonRecyclerAdapter.ViewHolder> {

    List<GojuonItem> list;
    OnItemClickListener onItemClickListener;
    OnItemLongClickListener onItemLongClickListener;


    public GojuonRecyclerAdapter(List<GojuonItem> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;

        if (viewType == Constants.TYPE_HEADER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gojuon_header, parent, false);
        } else if (viewType == Constants.TYPE_ITEM_DISABLE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gojuon_disable, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gojuon, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        GojuonItem item = list.get(position);

        if (BaseApplication.TYPE_MING == Constants.TYPE_HIRAGANA) {
            holder.tv_jiaming.setText(item.getHiragana());
        } else {
            holder.tv_jiaming.setText(item.getKatakana());
        }

        if (holder.tv_rome != null) {
            holder.tv_rome.setText(item.getRome());
        }

        holder.item = item;

        if (getItemViewType(position) == Constants.TYPE_ITEM && holder.item.isExisted()) {

            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (onItemClickListener != null) {
                        onItemClickListener.onClick(holder.item);
                    }

                }
            });

            if (holder.item.getCategory() != Constants.CATEGORY_AOYIN) {
                holder.view.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {

                        if (onItemLongClickListener != null) {
                            onItemLongClickListener.onLongClick(holder.item);
                        }

                        return true;
                    }
                });
            }


        }

    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
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

        void onClick(GojuonItem item);

    }

    public interface OnItemLongClickListener {

        void onLongClick(GojuonItem item);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View view;
        public final TextView tv_jiaming;
        public final TextView tv_rome;
        public GojuonItem item;

        public ViewHolder(View itemView) {
            super(itemView);

            this.view = itemView;
            this.tv_jiaming = itemView.findViewById(R.id.tv_jiaming);
            this.tv_rome = itemView.findViewById(R.id.tv_rome);

        }
    }


}
