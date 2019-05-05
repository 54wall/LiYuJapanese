package pri.weiqaing.common.loader;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

/**
 * Created by Administrator on 2017/1/10.
 */

public class GlideImageLoader extends BaseImageLoader {

    private static GlideImageLoader instance;

    public static GlideImageLoader getInstance() {
        if (instance == null) {
            instance = new GlideImageLoader();
        }
        return instance;
    }

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .transition(new DrawableTransitionOptions().crossFade(2000))
                .into(imageView);
    }

    @Override
    public void displayCircleImage(Context context, Object path, ImageView imageView) {

        Glide.with(context)
                .load(path)
                .transition(new DrawableTransitionOptions().crossFade(2000))
                .into(imageView);
    }
}
