package pri.weiqiang.liyujapanese.network.pixiv.service;

import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;


public interface PixivService {

    interface IllustService {
        void getIllusts(String mode, Consumer<ResponseBody> consumer);

        void getIllust(int id, Consumer<ResponseBody> consumer);

    }

}
