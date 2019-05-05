package pri.weiqiang.liyujapanese.mvp.model.pixivIllust;

import java.util.List;

import io.reactivex.functions.Consumer;
import pri.weiqiang.liyujapanese.mvp.bean.pixivIllust.PixivIllustBean;
import pri.weiqaing.common.base.mvp.BaseModel;

public interface PixivIllustFragmentModel extends BaseModel {
    void getIllusts(String mode, Consumer<List<PixivIllustBean>> consumer);

    String[] getOptions();
}
