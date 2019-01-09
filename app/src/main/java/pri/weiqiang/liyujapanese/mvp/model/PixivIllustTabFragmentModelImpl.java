package pri.weiqiang.liyujapanese.mvp.model;

import java.util.ArrayList;
import java.util.List;

import pri.weiqiang.liyujapanese.MyApplication;
import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.mvp.bean.PixivIllustTab;
import pri.weiqiang.liyujapanese.network.pixiv.PixivIllustApi;
import pri.weiqiang.liyujapanese.utils.ResourceUtils;

public class PixivIllustTabFragmentModelImpl implements BaseModel.PixivIllustTabFragmentModel {

    @Override
    public List<PixivIllustTab> getData() {

        List<PixivIllustTab> data = new ArrayList<>();
        data.add(new PixivIllustTab(PixivIllustApi.MODE_DAILY, ResourceUtils.getString(MyApplication.getInstance(), R.string.daily)));
        data.add(new PixivIllustTab(PixivIllustApi.MODE_WEEKLY, ResourceUtils.getString(MyApplication.getInstance(), R.string.weekly)));
        data.add(new PixivIllustTab(PixivIllustApi.MODE_MONTHLY, ResourceUtils.getString(MyApplication.getInstance(), R.string.monthly)));
        data.add(new PixivIllustTab(PixivIllustApi.MODE_ROOKIE, ResourceUtils.getString(MyApplication.getInstance(), R.string.rookie)));
        data.add(new PixivIllustTab(PixivIllustApi.MODE_ORIGINAL, ResourceUtils.getString(MyApplication.getInstance(), R.string.original)));
        data.add(new PixivIllustTab(PixivIllustApi.MODE_MALE, ResourceUtils.getString(MyApplication.getInstance(), R.string.male)));
        data.add(new PixivIllustTab(PixivIllustApi.MODE_FEMALE, ResourceUtils.getString(MyApplication.getInstance(), R.string.female)));

        return data;
    }
}
