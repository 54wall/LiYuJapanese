package pri.weiqiang.liyujapanese.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.mvp.bean.pixivIllust.PixivIllustBean;

public class PixivIllustRecyclerAdapter extends RecyclerView.Adapter<PixivIllustRecyclerAdapter.ViewHolder> {

    Context context;
    List<PixivIllustBean> list;

    OnItemClickListener onItemClickListener;

    public PixivIllustRecyclerAdapter(Context context, List<PixivIllustBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pixivillust, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.bean = list.get(position);
        holder.tv_title.setText(holder.bean.getTitle());
        holder.tv_id.setText(String.valueOf(holder.bean.getId()));
        holder.tv_author.setText(holder.bean.getAuthor());

        Glide.with(context)/*.asBitmap()*/.load(holder.bean.getImg_240x480()).into(holder.iv_img);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(holder.bean);
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


    public interface OnItemClickListener {

        void onItemClick(PixivIllustBean bean);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View view;
        public final ImageView iv_img;
        public final TextView tv_title;
        public final TextView tv_id;
        public final TextView tv_author;

        public PixivIllustBean bean;

        public ViewHolder(View itemView) {
            super(itemView);

            this.view = itemView;
            this.iv_img = itemView.findViewById(R.id.iv_img);
            this.tv_title = itemView.findViewById(R.id.tv_word);
            this.tv_id = itemView.findViewById(R.id.tv_id);
            this.tv_author = itemView.findViewById(R.id.tv_author);


        }


    }

}
