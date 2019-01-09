package pri.weiqiang.liyujapanese.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import pri.weiqiang.liyujapanese.R;


public class ImageDialog extends Dialog {


    public ImageDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {

        Context context;
        int resId;
        int width;
        int height;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setResId(int resId) {
            this.resId = resId;
            return this;
        }

        public Builder override(int width, int height) {
            this.width = width;
            this.height = height;
            return this;
        }

        public ImageDialog create() {

            ImageDialog dialog = new ImageDialog(context, R.style.AppTheme_Dialog_NoTitle);
            ImageView imageView = new ImageView(context);
            RequestOptions options = new RequestOptions()
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE);

            Glide.with(context)/*.asGif()*/.load(resId).apply(options).into(imageView);
//            Glide.with(context).load(resId).asGif().fitCenter().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
            dialog.addContentView(imageView, new LinearLayout.LayoutParams(width, height));

            return dialog;
        }


    }


}
