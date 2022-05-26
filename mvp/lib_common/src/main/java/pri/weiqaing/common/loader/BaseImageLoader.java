package pri.weiqaing.common.loader;

import android.content.Context;
import android.widget.ImageView;


/**
 * Created by yiyi on 17/1/17.
 */

public abstract class BaseImageLoader implements ImageLoaderInterface<ImageView> {
    @Override
    public ImageView creteImageView(Context context) {
        ImageView imageView = new ImageView(context);
        return imageView;
    }
}
