package pri.weiqiang.liyujapanese.mvp.model.pixivIllust;

import java.util.ArrayList;
import java.util.List;

import pri.weiqaing.common.base.BaseApplication;
import pri.weiqaing.common.utils.ResourceUtils;
import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.mvp.bean.pixivIllust.PixivIllustTab;
import pri.weiqiang.liyujapanese.network.pixiv.PixivIllustApi;

public class PixivIllustTabFragmentModelImpl implements PixivIllustTabFragmentModel {

    @Override
    public void unsubscribe() {

    }

    @Override
    public List<PixivIllustTab> getData() {

        List<PixivIllustTab> data = new ArrayList<>();
        data.add(new PixivIllustTab(PixivIllustApi.MODE_DAILY, ResourceUtils.getString(BaseApplication.getInstance(), R.string.daily)));
        data.add(new PixivIllustTab(PixivIllustApi.MODE_WEEKLY, ResourceUtils.getString(BaseApplication.getInstance(), R.string.weekly)));
        data.add(new PixivIllustTab(PixivIllustApi.MODE_MONTHLY, ResourceUtils.getString(BaseApplication.getInstance(), R.string.monthly)));
        data.add(new PixivIllustTab(PixivIllustApi.MODE_ROOKIE, ResourceUtils.getString(BaseApplication.getInstance(), R.string.rookie)));
        data.add(new PixivIllustTab(PixivIllustApi.MODE_ORIGINAL, ResourceUtils.getString(BaseApplication.getInstance(), R.string.original)));
        data.add(new PixivIllustTab(PixivIllustApi.MODE_MALE, ResourceUtils.getString(BaseApplication.getInstance(), R.string.male)));
        data.add(new PixivIllustTab(PixivIllustApi.MODE_FEMALE, ResourceUtils.getString(BaseApplication.getInstance(), R.string.female)));

        return data;
    }
}
