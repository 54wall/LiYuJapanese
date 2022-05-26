package pri.weiqiang.liyujapanese.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pri.weiqaing.common.utils.JpTTSUtils;
import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.manager.DBManager;
import pri.weiqiang.liyujapanese.mvp.bean.dic.Word;

public class FavWordsRecyclerAdapter extends RecyclerView.Adapter<FavWordsRecyclerAdapter.ViewHolder> {

    private static final String TAG = FavWordsRecyclerAdapter.class.getSimpleName();
    Context context;
    List<Word> list;
    // 列表展开标识
    int opened = -1;
    ArrayList<Integer> posOpened = new ArrayList<Integer>();
    OnItemClickListener onItemClickListener;
    Boolean isExpandable;

    public FavWordsRecyclerAdapter(Context context, List<Word> list, Boolean isExpandable) {
        this.context = context;
        this.list = list;
        this.isExpandable = isExpandable;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_word, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.bean = list.get(position);
        holder.mTvWord.setText(holder.bean.getWord());
        holder.mTvPhonetic.setText(String.valueOf(holder.bean.getPhonetic()));
        holder.mTvTranslation.setText(holder.bean.getTranslation());
        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setIsExpandable(boolean isExpandable) {
        this.isExpandable = isExpandable;
    }

    public interface OnItemClickListener {

        void onItemClick(Word bean);

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final View view;
        public final TextView mTvWord;
        public final TextView mTvPhonetic;
        public final TextView mTvTranslation;
        public final ImageView mIvMark;
        public final ImageView mIvTTS;

        public Word bean;

        public ViewHolder(View itemView) {
            super(itemView);

            this.view = itemView;
            this.mTvWord = itemView.findViewById(R.id.tv_word);
            this.mTvPhonetic = itemView.findViewById(R.id.tv_phonetic);
            this.mTvTranslation = itemView.findViewById(R.id.tv_translation);
            this.mIvMark = itemView.findViewById(R.id.iv_mark);
            this.mIvTTS = itemView.findViewById(R.id.iv_tts);
            itemView.setOnClickListener(this);
            mIvMark.setOnClickListener(this);
            mIvTTS.setOnClickListener(this);
        }

        // 此方法实现列表的展开和关闭
        public void bind(int pos) {
            if (bean.getFav() == 0) {
                this.mIvMark.setImageResource(R.drawable.ic_bookmark_border_primary);
            } else {
                this.mIvMark.setImageResource(R.drawable.ic_bookmark_primary);
            }
            if (isExpandable) {
                this.mTvWord.setText(bean.getWord() + "（" + bean.getPhonetic() + "）");
                this.mTvTranslation.setVisibility(View.VISIBLE);
            } else if (posOpened.contains(pos)) {
                this.mTvWord.setText(bean.getWord() + "（" + bean.getPhonetic() + "）");
                this.mTvTranslation.setVisibility(View.VISIBLE);
            } else {
                this.mTvWord.setText(bean.getWord());
                this.mTvTranslation.setVisibility(View.GONE);
            }
        }

        /**
         * recyclerView是不提供onItemClickListener的。所以列表的点击事件需要我们自己来实现
         *
         * @param v
         */
        @Override
        public void onClick(View v) {
            Log.e(TAG, "getAdapterPosition:" + getAdapterPosition() + ",opened:" + opened);
            switch (v.getId()) {
                case R.id.iv_mark:
                    Log.e(TAG, "iv_mark onClick.");
                    DBManager.getInstance().updateFav(bean);
                    if (bean.getFav() == 0) {
                        bean.setFav(1);
                        DBManager.getInstance().insertFav(bean);
                    } else {
                        bean.setFav(0);
                        DBManager.getInstance().delFav(bean);
                        list.remove(getAdapterPosition());
                        notifyItemRemoved(getAdapterPosition());
                    }

                    break;
                case R.id.iv_tts:
                    Log.e(TAG, "iv_tts onClick.");
                    JpTTSUtils.getInstance().SpeakJP(bean.getPhonetic());
                    break;
                default:
                    Log.e(TAG, "itemView onClick.");
                    if (posOpened.contains(getAdapterPosition())) {
                        posOpened.remove(posOpened.indexOf(getAdapterPosition()));
                    } else {
                        posOpened.add(getAdapterPosition());
                    }
            }
            notifyItemChanged(getAdapterPosition());

        }
    }


}
