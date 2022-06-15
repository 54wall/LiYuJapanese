package pri.weiqiang.jet.kt.liyujapanese.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pri.weiqiang.jet.kt.liyujapanese.R
import pri.weiqiang.jet.kt.liyujapanese.data.bean.Word

class WordAdapter(private var isExpandable: Boolean) :
    ListAdapter<Word, WordAdapter.ViewHolder>(DiffCallback) {

    //    var posOpened:ArrayList<Int> = ArrayList<Int>()
//    var posOpened:ArrayList<Int> = ArrayList()
    var posOpened = ArrayList<Int>()
    var opened = -1

    companion object {
        val TAG = WordAdapter::class.java.simpleName
    }

    object DiffCallback : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.word == newItem.word
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_word, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bean = getItem(position)
        holder.mTvWord.setText(holder.bean?.word)
        holder.mTvPhonetic.setText(holder.bean?.phonetic)
        holder.mTvTranslation.setText(holder.bean?.translation)
        holder.bind(position)
    }

    inner class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val mTvWord: TextView
        val mTvPhonetic: TextView
        val mTvTranslation: TextView
        val mIvMark: ImageView
        val mIvTTS: ImageView
        var bean: Word? = null

        override fun onClick(v: View?) {
            Log.e(TAG, "getAdapterPosition:" + getAdapterPosition() + ",opened:" + opened)
            when (v?.id) {
                R.id.iv_mark -> {
                    Log.e(TAG, "iv_mark onClick.")
                    //                    DBManager.getInstance().updateFav(bean);
                    if (bean?.fav == 0) {
                        bean?.fav = 1
//                        DBManager.getInstance().insertFav(bean);
                    } else {
                        bean?.fav = 0
//                        DBManager.getInstance().delFav(bean);
                    }
                }
                R.id.iv_tts -> {
                    Log.e(TAG, "iv_tts onClick.")
                }
                else -> {
                    Log.e(TAG, "itemView onClick.")
                    if (posOpened.contains(adapterPosition)) {
                        posOpened.remove(posOpened.indexOf(adapterPosition))
                    } else {
                        posOpened.add(adapterPosition)
                    }
                }


            }
            notifyItemChanged(adapterPosition)
        }

        init {
            mTvWord = itemView.findViewById(R.id.tv_word)
            mTvPhonetic = itemView.findViewById(R.id.tv_phonetic)
            mTvTranslation = itemView.findViewById(R.id.tv_translation)
            mIvMark = itemView.findViewById(R.id.iv_mark)
            mIvTTS = itemView.findViewById(R.id.iv_tts)
            itemView.setOnClickListener(this)
            mIvMark.setOnClickListener(this)
            mIvTTS.setOnClickListener(this)
        }

        fun bind(pos: Int) {
            if (bean?.fav == 0) {
                this.mIvMark.setImageResource(R.drawable.ic_bookmark_border_primary)
            } else {
                this.mIvMark.setImageResource(R.drawable.ic_bookmark_primary)
            }
            if (isExpandable) {
                this.mTvWord.setText(bean?.word)
                this.mTvPhonetic.setText(bean?.phonetic)
                this.mTvPhonetic.setVisibility(View.VISIBLE)
                this.mTvTranslation.setVisibility(View.VISIBLE)
            } else if (posOpened.contains(pos)) {
                this.mTvWord.setText(bean?.word)
                this.mTvPhonetic.setText(bean?.phonetic)
                this.mTvTranslation.setVisibility(View.VISIBLE)
                this.mTvPhonetic.setVisibility(View.VISIBLE)
            } else {
                this.mTvWord.setText(bean?.word)
                this.mTvPhonetic.setVisibility(View.GONE)
                this.mTvTranslation.setVisibility(View.GONE)
            }
        }
    }
}