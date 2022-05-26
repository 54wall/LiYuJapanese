package pri.weiqiang.liyujapanese.mvp.model.pixivIllust;

import java.util.List;

import io.reactivex.functions.Consumer;
import pri.weiqaing.common.base.mvp.BaseModel;
import pri.weiqiang.liyujapanese.mvp.bean.pixivIllust.PixivIllustBean;

public interface PixivIllustFragmentModel extends BaseModel {
    void getIllusts(String mode, Consumer<List<PixivIllustBean>> consumer);

    String[] getOptions();
}
