package pri.weiqaing.common.widget.badgeView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.view.ActionProvider;

import pri.weiqaing.common.R;

/**
 * Created by yiyi on 17/1/17.
 */

public class ActionProviderView extends ActionProvider {

    private ImageView mImageView;
    private TextView mTextView;

    public ActionProviderView(Context context) {
        super(context);
    }

    @Override
    public View onCreateActionView() {
        int size = getContext().getResources().getDimensionPixelSize(com.google.android.material.R.dimen.abc_action_bar_content_inset_material);
//        int size = getContext().getResources().getDimensionPixelSize(android.support.design.R.dimen.abc_action_bar_default_height_material);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(size, size);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_menu_action_provider, null, false);
        view.setLayoutParams(params);

        mImageView = view.findViewById(R.id.icon);
        mTextView = view.findViewById(R.id.num_tv);

        return view;
    }

    public void setImage(Drawable icon) {
        mImageView.setImageDrawable(icon);
    }

    public void setNumInt(String num) {
        mTextView.setText(num);
    }
}
