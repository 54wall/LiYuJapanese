package pri.weiqiang.liyujapanese.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.config.Constants;
import pri.weiqiang.liyujapanese.loader.GlideImageLoader;
import pri.weiqiang.liyujapanese.mvp.bean.zhihu.StoryContentEntity;
import pri.weiqiang.liyujapanese.mvp.presenter.ArticleDetailActivityPresenterImpl;
import pri.weiqiang.liyujapanese.mvp.presenter.BasePresenter;
import pri.weiqiang.liyujapanese.mvp.view.BaseView;
import pri.weiqiang.liyujapanese.rxbus.RxBus;
import pri.weiqiang.liyujapanese.rxbus.event.EventContainer;
import pri.weiqiang.liyujapanese.rxbus.event.SettingEvent;
import pri.weiqiang.liyujapanese.utils.HtmlUtil;


public class ArticleDetailActivity extends BaseActivity implements BaseView.ArticleDetailActivityView {

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
