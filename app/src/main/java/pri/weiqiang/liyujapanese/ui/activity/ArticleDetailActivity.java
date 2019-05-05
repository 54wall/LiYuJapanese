package pri.weiqiang.liyujapanese.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import butterknife.BindView;
import pri.weiqaing.common.base.BaseActivity;
import pri.weiqaing.common.base.mvp.BasePresenter;
import pri.weiqaing.common.config.Constants;
import pri.weiqaing.common.loader.GlideImageLoader;
import pri.weiqaing.common.rxbus.RxBus;
import pri.weiqaing.common.rxbus.event.EventContainer;
import pri.weiqaing.common.rxbus.event.SettingEvent;
import pri.weiqaing.common.utils.HtmlUtil;
import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.mvp.bean.zhihu.StoryContentEntity;
import pri.weiqiang.liyujapanese.mvp.presenter.zhihu.ArticleDetailActivityPresenterImpl;
import pri.weiqiang.liyujapanese.mvp.view.zhihu.ArticleDetailActivityView;


public class ArticleDetailActivity extends BaseActivity implements ArticleDetailActivityView {

    private static final String TAG = ArticleDetailActivity.class.getSimpleName();

    BasePresenter.ArticleDetailActivityPresenter presenter;

    @BindView(R.id.toolBar)
    Toolbar mToolbar;
    @BindView(R.id.detail_bar_image)
    ImageView detailBarImg;
    @BindView(R.id.detail_bar_title)
    TextView detailBarTitle;
    @BindView(R.id.detail_bar_copyright)
    TextView detailBarCopyright;
    @BindView(R.id.wv_detail_content)
    WebView detailContentWV;

    @Override
    protected int getViewId() {

        return R.layout.activity_article_detail;
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {

        presenter = new ArticleDetailActivityPresenterImpl(this);
    }

    @Override
    protected void doAction() {
        Intent intent = getIntent();
        int id = intent.getIntExtra(Constants.FLAG_ZHIHU_ARTICLE_ID, 0);
        mToolbar.setTitle(intent.getStringExtra(Constants.FLAG_ZHIHU_ARTICLE_TITLE));
        setSupportActionBar(mToolbar);//setSupportActionBar没有则标题和返回监听都无效
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (id != 0) {
            presenter.showContent(id);
        } else {
            RxBus.getDefault().post(new EventContainer(EventContainer.TYPE_SETTING, new SettingEvent(R.string.zhihu_fail_get_content)));
        }
    }

    @Override
    public void showContent(StoryContentEntity storyContentEntity) {

        String imgUrl = storyContentEntity.getImage();
        GlideImageLoader.getInstance().displayImage(this, imgUrl, detailBarImg);
        detailBarCopyright.setText(storyContentEntity.getImage_source());
        String htmlData = HtmlUtil.createHtmlData(storyContentEntity.getBody(), storyContentEntity.getCss(), storyContentEntity.getJs());
        detailContentWV.loadData(htmlData, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
    }
}
