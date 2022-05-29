package pri.weiqiang.jet.liyujapanese.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import pri.weiqiang.jet.liyujapanese.R;
import pri.weiqiang.jet.liyujapanese.data.bean.Word;


public class WordAdapter extends ListAdapter<Word, WordAdapter.ViewHolder> {

    private static final String TAG = WordAdapter.class.getSimpleName();
    // 列表展开标识
    int opened = -1;
    ArrayList<Integer> posOpened = new ArrayList<Integer>();
    OnItemClickListener onItemClickListener;
    Boolean isExpandable;

    public WordAdapter(Boolean isExpandable) {
        super(DIFF_CALLBACK);
        this.isExpandable = isExpandable;
    }

    private static final DiffUtil.ItemCallback<Word> DIFF_CALLBACK = new DiffUtil.ItemCallback<Word>() {
        @Override
        public boolean areItemsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
            return oldItem.getWord().equals(newItem.getWord());
        }
    };

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_word, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.bean = getItem(position);
        holder.mTvWord.setText(holder.bean.getWord());
        holder.mTvPhonetic.setText(String.valueOf(holder.bean.getPhonetic()));
        holder.mTvTranslation.setText(holder.bean.getTranslation());
        holder.bind(position);

    }
    //使用RecyclerAdapter时使用
//    public void updateWordList(List<Word> list){
//        if(this.list!=null){
//            this.list.clear();
//            this.list.addAll(list);
//        }
//    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
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
                this.mTvWord.setText(bean.getWord());
                this.mTvPhonetic.setText(bean.getPhonetic());
                this.mTvPhonetic.setVisibility(View.VISIBLE);
                this.mTvTranslation.setVisibility(View.VISIBLE);
            } else if (posOpened.contains(pos)) {
                this.mTvWord.setText(bean.getWord());
                this.mTvPhonetic.setText(bean.getPhonetic());
                this.mTvTranslation.setVisibility(View.VISIBLE);
                this.mTvPhonetic.setVisibility(View.VISIBLE);
            } else {
                this.mTvWord.setText(bean.getWord());
                this.mTvPhonetic.setVisibility(View.GONE);
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
//                    DBManager.getInstance().updateFav(bean);
                    if (bean.getFav() == 0) {
                        bean.setFav(1);
//                        DBManager.getInstance().insertFav(bean);
                    } else {
                        bean.setFav(0);
//                        DBManager.getInstance().delFav(bean);
                    }
                    break;
                case R.id.iv_tts:
                    Log.e(TAG, "iv_tts onClick.");
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
