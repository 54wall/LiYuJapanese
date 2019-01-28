package pri.weiqiang.liyujapanese.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.mvp.bean.Book;


public class LeftMenuAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<Book> mBookList;
    private int mSelectedNum;
    private List<onItemSelectedListener> mSelectedListenerList;
    private String TAG = LeftMenuAdapter.class.getSimpleName();

    public LeftMenuAdapter(Context mContext, List<Book> mBookList) {
        this.mContext = mContext;
        this.mBookList = mBookList;
        this.mSelectedNum = -1;
        this.mSelectedListenerList = new ArrayList<>();
        if (mBookList.size() > 0)
            mSelectedNum = 0;
    }

    public void addItemSelectedListener(onItemSelectedListener listener) {
        if (mSelectedListenerList != null)
            mSelectedListenerList.add(listener);
    }

    public void removeItemSelectedListener(onItemSelectedListener listener) {
        if (mSelectedListenerList != null && !mSelectedListenerList.isEmpty())
            mSelectedListenerList.remove(listener);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e(TAG, "onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.left_menu_item, parent, false);
        LeftMenuViewHolder viewHolder = new LeftMenuViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.e(TAG, "onBindViewHolder");
        Book mBook = mBookList.get(position);
        LeftMenuViewHolder viewHolder = (LeftMenuViewHolder) holder;
        viewHolder.mTvName.setText(mBook.getName());
        if (mSelectedNum == position) {
            viewHolder.mLlLeftItem.setSelected(true);
        } else {
            viewHolder.mLlLeftItem.setSelected(false);
        }
    }

    @Override
    public int getItemCount() {
        return mBookList.size();
    }

    public int getSelectedNum() {
        return mSelectedNum;
    }

    public void setSelectedNum(int selectedNum) {
        if (selectedNum < getItemCount() && selectedNum >= 0) {
            this.mSelectedNum = selectedNum;
            notifyDataSetChanged();
        }
    }

    private void notifyItemSelected(int position) {
        if (mSelectedListenerList != null && !mSelectedListenerList.isEmpty()) {
            for (onItemSelectedListener listener : mSelectedListenerList) {
                listener.onLeftItemSelected(position, mBookList.get(position));
            }
        }
    }

    public interface onItemSelectedListener {
        void onLeftItemSelected(int postion, Book menu);
    }

    private class LeftMenuViewHolder extends RecyclerView.ViewHolder {

        TextView mTvName;
        LinearLayout mLlLeftItem;

        public LeftMenuViewHolder(final View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.tv_name);
            mLlLeftItem = itemView.findViewById(R.id.ll_left_item);
            mLlLeftItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int clickPosition = getAdapterPosition();
//                    setSelectedNum(clickPosition);
                    notifyItemSelected(clickPosition);
                }
            });
        }
    }
}
