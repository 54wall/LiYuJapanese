package pri.weiqiang.liyujapanese.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.mvp.bean.TranslateSpinnerItem;

public class TranslateSpinnerAdapter extends BaseAdapter {

    List<TranslateSpinnerItem> list;
    Context context;

    public TranslateSpinnerAdapter(List<TranslateSpinnerItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;

        TranslateSpinnerItem spinnerItem = list.get(position);

        if (spinnerItem.isHasIcon()) {
            view = LayoutInflater.from(context).inflate(R.layout.spinner_item_translate_icon, parent, false);
            TextView textView = view.findViewById(R.id.tv_name);
            textView.setText(spinnerItem.getName());
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_icon);
            Glide.with(context)/*.asBitmap()*/.load(spinnerItem.getIcon()).into(imageView);

        } else {
            view = LayoutInflater.from(context).inflate(R.layout.spinner_item_translate, parent, false);
            TextView textView = view.findViewById(R.id.tv_name);
            textView.setText(spinnerItem.getName());
        }

        return view;
    }
}
