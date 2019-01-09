package pri.weiqiang.liyujapanese.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import me.pwcong.radiobuttonview.view.RadioButtonView;
import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.config.Constants;
import pri.weiqiang.liyujapanese.manager.ActivityManager;
import pri.weiqiang.liyujapanese.manager.SharedPreferenceManager;
import pri.weiqiang.liyujapanese.mvp.presenter.BasePresenter;
import pri.weiqiang.liyujapanese.mvp.presenter.MainActivityPresenterImpl;
import pri.weiqiang.liyujapanese.mvp.view.BaseView;
import pri.weiqiang.liyujapanese.ui.fragment.FavLessonFragment;
import pri.weiqiang.liyujapanese.ui.fragment.FavWordsFragment;
import pri.weiqiang.liyujapanese.ui.fragment.GameFragment;
import pri.weiqiang.liyujapanese.ui.fragment.GojuonMemoryTabFragment;
import pri.weiqiang.liyujapanese.ui.fragment.GojuonTabFragment;
import pri.weiqiang.liyujapanese.ui.fragment.LessonsFragment;
import pri.weiqiang.liyujapanese.ui.fragment.MemoryFragment;
import pri.weiqiang.liyujapanese.ui.fragment.NewsAPIFragment;
import pri.weiqiang.liyujapanese.ui.fragment.PixivIllustTabFragment;
import pri.weiqiang.liyujapanese.ui.fragment.TranslateFragment;
import pri.weiqiang.liyujapanese.ui.fragment.WordsFragment;
import pri.weiqiang.liyujapanese.ui.fragment.ZhihuFragment;
import pri.weiqiang.liyujapanese.utils.ResourceUtils;


public class MainActivity extends BaseActivity implements BaseView.MainActivityView {

    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    RadioButtonView mRadioButtonView;
    private long mExitTime;
    private BasePresenter.MainActivityPresenter presenter;
    private LessonsFragment mLesssonsFragment;
    private int curFragmentIndex = Constants.FR_WORDS;
    private boolean isExpandable = false;

    @Override
    protected int getViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {

        presenter = new MainActivityPresenterImpl(this);
        initToolbar();
        initRadioButtonView();
        initDrawerLayout();
        initNavigationView();
    }

    private void initToolbar() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        Log.i(TAG, "initToolbar: OK");
    }

    private void initRadioButtonView() {

        mRadioButtonView = (RadioButtonView) getLayoutInflater().inflate(R.layout.button_radio, mToolbar, false);
        mRadioButtonView.setOptions(getRadioButtonOptions());
        mRadioButtonView.setOnRadioButtonChangedListener(new RadioButtonView.OnRadioButtonChangedListener() {
            @Override
            public void onRadioButtonChanged(String s, int i) {
                presenter.onRadioButtonChanged(i);
            }
        });

        Toolbar.LayoutParams params = new Toolbar.LayoutParams((int) (ResourceUtils.getDimension(MainActivity.this, R.dimen.radio_button_width)),
                ViewGroup.LayoutParams.MATCH_PARENT, GravityCompat.END);
        mToolbar.addView(mRadioButtonView, params);
        Log.i(TAG, "initRadioButtonView: OK");
    }

    private void initDrawerLayout() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Log.i(TAG, "initDrawerLayout: OK");
    }

    private void initNavigationView() {
        mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.i(TAG, "因为这里，初始化了getItemId():");
                presenter.onNavigationItemSelected(item.getItemId());
                return true;
            }
        });
        Log.i(TAG, "仅改变选择状态，初始化Fragment在doAction()中的presenter.initMainActivity();");
        mNavigationView.setCheckedItem(R.id.item_word);

        Log.i(TAG, "initNavigationView: OK");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_more, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Log.e(TAG, "onPrepareOptionsMenu:" + curFragmentIndex);
        // 动态设置ToolBar状态
        switch (curFragmentIndex) {
            case Constants.FR_WORDS:
                setNewsMenuVisible(menu, false);
                menu.findItem(R.id.item_content).setVisible(true);
                menu.findItem(R.id.item_fav).setVisible(true);
                menu.findItem(R.id.item_expand).setVisible(true);
                break;
            case Constants.FR_FAV_WORDS:
                setNewsMenuVisible(menu, false);
                menu.findItem(R.id.item_content).setVisible(true);
                menu.findItem(R.id.item_fav).setVisible(true);
                menu.findItem(R.id.item_expand).setVisible(true);
                break;
            case Constants.FR_FAV_LESSON:
                setNewsMenuVisible(menu, false);
                menu.findItem(R.id.item_content).setVisible(true);
                menu.findItem(R.id.item_fav).setVisible(true);
                menu.findItem(R.id.item_expand).setVisible(false);
                break;
            case Constants.FR_ZHIHU:
                setNewsMenuVisible(menu, true);
                menu.findItem(R.id.item_content).setVisible(false);
                menu.findItem(R.id.item_fav).setVisible(false);
                menu.findItem(R.id.item_expand).setVisible(false);
                break;
            case Constants.FR_NEWS:
                setNewsMenuVisible(menu, true);
                menu.findItem(R.id.item_content).setVisible(false);
                menu.findItem(R.id.item_fav).setVisible(false);
                menu.findItem(R.id.item_expand).setVisible(false);
                break;
            case Constants.FR_LESSONS:
            case Constants.FR_GOJUON:
            case Constants.FR_MEMORY:
            case Constants.FR_TRANSLATE:
            case Constants.FR_GAME:
            case Constants.FR_PIXIV_ILLUST:
                setNewsMenuVisible(menu, false);
            default:
                menu.findItem(R.id.item_content).setVisible(false);
                menu.findItem(R.id.item_fav).setVisible(false);
                menu.findItem(R.id.item_expand).setVisible(false);


        }
        return super.onPrepareOptionsMenu(menu);
    }

    private void setNewsMenuVisible(Menu menu, boolean isVisible) {
        menu.findItem(R.id.item_general).setVisible(isVisible);
        menu.findItem(R.id.item_technology).setVisible(isVisible);
        menu.findItem(R.id.item_science).setVisible(isVisible);
        menu.findItem(R.id.item_business).setVisible(isVisible);
        menu.findItem(R.id.item_entertainment).setVisible(isVisible);
        menu.findItem(R.id.item_zhihu).setVisible(isVisible);
        menu.findItem(R.id.action_search).setVisible(isVisible);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        presenter.onMenuItemSelected(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void doAction() {

        presenter.initMainActivity();

        Log.i(TAG, "完成初始化第一个Fragment doAction: OK");
    }


    @Override
    public void onBackPressed() {

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            closeDrawer();
        } else {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {

                showSnackBar(mToolbar, R.string.one_more_press_to_exit);

                mExitTime = System.currentTimeMillis();

            } else {
                ActivityManager.getInstance().finishAll();
            }
        }
    }


    @Override
    public void openDrawer() {
        mDrawerLayout.openDrawer(GravityCompat.START);
        Log.i(TAG, "openDrawer: OK");
    }

    @Override
    public void closeDrawer() {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        Log.i(TAG, "closeDrawer: OK");
    }

    @Override
    public void expandWords() {
        isExpandable = !isExpandable;
        switch (curFragmentIndex) {
            case Constants.FR_WORDS:
                switchWords(SharedPreferenceManager.getInstance().getString(Constants.CURRENT_LESSON, Constants.DEFAULT_LESSON), isExpandable);
                break;
            case Constants.FR_FAV_WORDS:
                Fragment current = getSupportFragmentManager().findFragmentById(R.id.content_main);
                if (current != null && current instanceof FavWordsFragment) {
                    ((FavWordsFragment) current).setExpandable(isExpandable);

                }
                break;
        }

    }

    @Override
    public void switchWords(String lesson, Boolean isExpandable) {

        curFragmentIndex = Constants.FR_WORDS;
        mRadioButtonView.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, WordsFragment.newInstance(lesson, isExpandable)).commit();
        Log.i(TAG, "switchWords: OK");
        int i = lesson.indexOf("_", 0);
        String title = lesson.substring(0, i) + " 第" + lesson.substring(i + 1) + "课";
        //mToolbar.setTitle(lesson);在首次进入app后不起作用，将显示鲤鱼日语，进入app再切换可以
        getSupportActionBar().setTitle(title);

    }

    @Override
    public void switchZhihu() {
        mToolbar.setTitle(R.string.zhihu_news);
        curFragmentIndex = Constants.FR_ZHIHU;
        mRadioButtonView.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new ZhihuFragment()).commit();
        Log.i(TAG, "switchZhihu: OK");
    }

    @Override
    public void switchNewsAPI() {
        mToolbar.setTitle(R.string.news_api);
        curFragmentIndex = Constants.FR_NEWS;
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new NewsAPIFragment()).commit();
        Log.i(TAG, "switchZhihu: OK");
    }

    @Override
    public void updateNewsAPI(String category) {

        String frName = getSupportFragmentManager().findFragmentById(R.id.content_main).getClass().getSimpleName();
        Log.e(TAG, "updateNewsAPI:" + frName);
        if (frName.equals(ZhihuFragment.class.getSimpleName())) {
            switchNewsAPI();
        } else if (frName.equals(NewsAPIFragment.class.getSimpleName())) {
            ((NewsAPIFragment) getSupportFragmentManager().findFragmentById(R.id.content_main)).updateNewsAPI(category);
        }


    }

    @Override
    public void switchFavLesson(Boolean isExpandable) {

        mToolbar.setTitle(R.string.menu_fav);
        curFragmentIndex = Constants.FR_FAV_LESSON;
        mRadioButtonView.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, FavLessonFragment.newInstance(isExpandable)).commit();
        Log.i(TAG, "switchFavLesson: OK");
    }

    @Override
    public void switchFavWord(String lessonId, Boolean isExpandable) {

        mToolbar.setTitle(R.string.menu_fav);
        curFragmentIndex = Constants.FR_FAV_WORDS;
        mRadioButtonView.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, FavWordsFragment.newInstance(lessonId, isExpandable)).commit();
        Log.i(TAG, "switchFavWord: OK");
    }

    @Override
    public void switchLessons() {

        mToolbar.setTitle(R.string.drawer_word);
        curFragmentIndex = Constants.FR_LESSONS;
        mRadioButtonView.setVisibility(View.GONE);

        if (mLesssonsFragment == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new LessonsFragment()).commit();
        } else {
            getSupportFragmentManager().beginTransaction().show(mLesssonsFragment).commit();
        }

        Log.i(TAG, "switchLessons: OK");
    }

    @Override
    public void switchGojuon() {

        mToolbar.setTitle(R.string.jp_gojuon);
        curFragmentIndex = Constants.FR_GOJUON;
        mRadioButtonView.setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new GojuonTabFragment()).commit();
        Log.i(TAG, "switchGojuon: OK");
    }

    @Override
    public void switchGojuonMemory() {

        mToolbar.setTitle(R.string.jp_gojuon);
        curFragmentIndex = Constants.FR_GOJUON;
        mRadioButtonView.setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new GojuonMemoryTabFragment()).commit();
        Log.i(TAG, "switchGojuonMemory: OK");
    }

    @Override
    public void switchMemory() {

        mToolbar.setTitle(R.string.memory);
        curFragmentIndex = Constants.FR_MEMORY;
        mRadioButtonView.setVisibility(View.GONE);

        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new MemoryFragment()).commit();

        Log.i(TAG, "switchMemory: OK");
    }

    @Override
    public void switchTranslate() {

        mToolbar.setTitle(R.string.translate);
        curFragmentIndex = Constants.FR_TRANSLATE;
        mRadioButtonView.setVisibility(View.GONE);

        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new TranslateFragment()).commit();

        Log.i(TAG, "switchTranslate: OK");
    }

    @Override
    public void switchGame() {
        mToolbar.setTitle(R.string.game);
        curFragmentIndex = Constants.FR_GAME;
        mRadioButtonView.setVisibility(View.GONE);

        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new GameFragment()).commit();

        Log.i(TAG, "switchGame: OK");
    }

    @Override
    public void switchPixivIllust() {

        mToolbar.setTitle(R.string.pixiv_illust);
        curFragmentIndex = Constants.FR_PIXIV_ILLUST;
        mRadioButtonView.setVisibility(View.GONE);

        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new PixivIllustTabFragment()).commit();

        Log.i(TAG, "switchPixivIllust: OK");
    }

    @Override
    public void showSnackBar(int id) {
        showSnackBar(mToolbar, id);
    }

    @Override
    public void switchSetting() {

        startActivity(new Intent(MainActivity.this, SettingActivity.class));

        Log.i(TAG, "switchSetting: OK");
    }

    @Override
    public void startPhotoViewActivity(Bundle bundle) {

        Intent intent = new Intent(MainActivity.this, PhotoViewActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    }

    @Override
    public void startPuzzleActivity() {

        Intent intent = new Intent(MainActivity.this, PuzzleActivity.class);
        startActivity(intent);

    }

    @Override
    public void startSupperzzleActivity() {
        Intent intent = new Intent(MainActivity.this, SupperzzleActivity.class);
        startActivity(intent);
    }

    @Override
    public void showAlertDialog(int titleId, int messageId,
                                int positiveTextId, DialogInterface.OnClickListener positiveButtonListener,
                                int negativeTextId, DialogInterface.OnClickListener negativeButtonListener) {

        new AlertDialog.Builder(MainActivity.this)
                .setTitle(titleId)
                .setMessage(messageId)
                .setPositiveButton(positiveTextId, positiveButtonListener)
                .setNegativeButton(negativeTextId, negativeButtonListener)
                .setIcon(R.drawable.ic_lightbulb_outline)
                .create()
                .show();

    }

    @Override
    public void switchAbout() {
        startActivity(new Intent(MainActivity.this, AboutActivity.class));

        Log.i(TAG, "switchAbout: OK");
    }

    private ArrayList<String> getRadioButtonOptions() {

        ArrayList<String> options = new ArrayList<>();
        options.add(ResourceUtils.getString(MainActivity.this, R.string.hiragana));
        options.add(ResourceUtils.getString(MainActivity.this, R.string.katakana));
        options.add(ResourceUtils.getString(MainActivity.this, R.string.gojun_tips));
        return options;

    }
}
