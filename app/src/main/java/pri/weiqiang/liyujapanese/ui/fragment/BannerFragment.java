package pri.weiqiang.liyujapanese.ui.fragment;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import androidx.annotation.Nullable;
import butterknife.BindView;
import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.config.Constants;
import pri.weiqiang.liyujapanese.mvp.bean.BannerItem;

public class BannerFragment extends BaseFragment {

    @BindView(R.id.iv_banner)
    ImageView mImageView;

    int banner;

    public static BannerFragment newInstance(BannerItem item) {

        Bundle arguments = new Bundle();
        arguments.putInt(Constants.IMG_BANNER, item.getBanner());
        BannerFragment fragment = new BannerFragment();
        fragment.setArguments(arguments);

        return fragment;

    }


    @Override
    protected int getViewId() {
        return R.layout.fragment_banner;
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {

        banner = getArguments().getInt(Constants.IMG_BANNER);

    }

    @Override
    protected void doAction() {
        Glide.with(getContext())/*.asBitmap()*/.load(banner).into(mImageView);
    }
}
