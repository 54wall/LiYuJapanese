package pri.weiqiang.liyujapanese.network.zhihu;

import io.reactivex.Observable;
import pri.weiqiang.liyujapanese.mvp.bean.zhihu.ThemeContentListEntity;
import pri.weiqiang.liyujapanese.mvp.bean.zhihu.ThemesEntity;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ThemeApi {
    /**
     * 获取主题日报列表theme
     */
    @GET("themes")
    Observable<ThemesEntity> getThemes();

    /**
     * 获取主题日报内容列表
     *
     * @param themeId 主题日报id
     */
    @GET("theme/{themeId}")
    Observable<ThemeContentListEntity> getThemeContentList(@Path("themeId") int themeId);

}
