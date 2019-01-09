package pri.weiqiang.liyujapanese.mvp.presenter;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import pri.weiqiang.liyujapanese.MyApplication;
import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.config.Constants;
import pri.weiqiang.liyujapanese.manager.SharedPreferenceManager;
import pri.weiqiang.liyujapanese.mvp.view.BaseView;
import pri.weiqiang.liyujapanese.rxbus.event.EventContainer;
import pri.weiqiang.liyujapanese.rxbus.event.GameEvent;
import pri.weiqiang.liyujapanese.rxbus.event.PhotoViewEvent;
import pri.weiqiang.liyujapanese.rxbus.event.SettingEvent;


public class MainActivityPresenterImpl extends BasePresenter<BaseView.MainActivityView> implements BasePresenter.MainActivityPresenter {

    private final String TAG = getClass().getSimpleName();

    public MainActivityPresenterImpl(BaseView.MainActivityView view) {
        super(view);
    }

    @Override
    public void initMainActivity() {
        onNavigationItemSelected(R.id.item_word);

    }

    @Override
    public void onRadioButtonChanged(int position) {
        switch (position) {

            case 0:
                MyApplication.TYPE_MING = Constants.TYPE_HIRAGANA;
                view.switchGojuon();
                break;
            case 1:
                MyApplication.TYPE_MING = Constants.TYPE_KATAKANA;
                view.switchGojuon();
                break;
            case 2:
                view.switchGojuonMemory();
                break;
            default:
                break;
        }
    }

    @Override
    public void onNavigationItemSelected(int id) {

        switch (id) {
            case R.id.item_news_api:
                view.switchNewsAPI();
                Log.e(TAG, "switchNewsAPI");
                break;

            case R.id.item_word:
                view.switchWords(SharedPreferenceManager.getInstance().getString(Constants.CURRENT_LESSON, Constants.DEFAULT_LESSON), false);
                if (SharedPreferenceManager.getInstance().getBoolean(Constants.FLAG_TIPS_WORD, true)) {

                    view.showAlertDialog(R.string.small_tips,
                            R.string.tips_word, R.string.remember, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }, R.string.do_not_remind, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    SharedPreferenceManager.getInstance().putBoolean(Constants.FLAG_TIPS_WORD, false);
                                    dialog.dismiss();
                                }
                            });

                }
                Log.e(TAG, "switchLessons");
                break;
            case R.id.item_gojuon:

                view.switchGojuon();

                if (SharedPreferenceManager.getInstance().getBoolean(Constants.FLAG_TIPS_JPSTART, true)) {

                    view.showAlertDialog(R.string.small_tips,
                            R.string.tips_jpstart, R.string.remember, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }, R.string.do_not_remind, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    SharedPreferenceManager.getInstance().putBoolean(Constants.FLAG_TIPS_JPSTART, false);
                                    dialog.dismiss();
                                }
                            });

                }

                break;
            case R.id.item_memory:

                if (SharedPreferenceManager.getInstance().getBoolean(Constants.FLAG_TIPS_MEMORY, true)) {

                    view.showAlertDialog(R.string.small_tips,
                            R.string.tips_memory, R.string.remember, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }, R.string.do_not_remind, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    SharedPreferenceManager.getInstance().putBoolean(Constants.FLAG_TIPS_MEMORY, false);
                                    dialog.dismiss();
                                }
                            });

                }

                view.switchMemory();
                break;
            case R.id.item_translate:

                view.switchTranslate();

                if (SharedPreferenceManager.getInstance().getBoolean(Constants.FLAG_TIPS_TRANSLATE, true)) {

                    view.showAlertDialog(R.string.small_tips,
                            R.string.tips_translate, R.string.remember, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }, R.string.do_not_remind, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    SharedPreferenceManager.getInstance().putBoolean(Constants.FLAG_TIPS_TRANSLATE, false);
                                    dialog.dismiss();
                                }
                            });

                }

                break;
            //后续将开放
            /*case R.id.item_pixiv_illust:

                view.switchPixivIllust();

                if (SharedPreferenceManager.getInstance().getBoolean(Constants.FLAG_TIPS_PIXIVILLUST, true)) {

                    view.showAlertDialog(R.string.small_tips, R.string.tips_pixivillust,
                            R.string.remember, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }, R.string.do_not_remind, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    SharedPreferenceManager.getInstance().putBoolean(Constants.FLAG_TIPS_PIXIVILLUST, false);
                                    dialog.dismiss();
                                }
                            });


                }


                break;*/
            //后续将开放
/*            case R.id.item_game:

                view.switchGame();

                break;*/

            case R.id.item_setting:
                view.switchSetting();
                break;
            case R.id.item_about:
                view.switchAbout();
                break;
            default:
                break;

        }

        view.closeDrawer();


    }

    @Override
    public void onBusEventInteraction(EventContainer eventContainer) {

        Log.i(TAG, "onBusEventInteraction: " + eventContainer);

        switch (eventContainer.getType()) {

            case EventContainer.TYPE_PHOTOVIEW:

                PhotoViewEvent photoViewEvent = (PhotoViewEvent) eventContainer.getEvent();

                Bundle bundle = new Bundle();
                bundle.putString(Constants.IMG_URL, photoViewEvent.getImg_url());
                bundle.putInt(Constants.IMG_ID, photoViewEvent.getImg_id());

                view.startPhotoViewActivity(bundle);

                break;

            case EventContainer.TYPE_GAME:

                GameEvent gameEvent = (GameEvent) eventContainer.getEvent();
                switch (gameEvent.getType()) {

                    case GameEvent.TYPE_PUZZLE:
                        view.startPuzzleActivity();
                        break;
                    case GameEvent.TYPE_SUPPERZZLE:
                        view.startSupperzzleActivity();
                        break;
                    default:
                        break;
                }
                break;
            case EventContainer.TYPE_SETTING:

                SettingEvent event = (SettingEvent) eventContainer.getEvent();
                view.showSnackBar(event.getMsg());

                break;

            default:
                break;
        }
    }

    @Override
    public void onMenuItemSelected(int id) {
        switch (id) {
            case R.id.item_content:
                Log.e(TAG, "switchLessons");
                view.switchLessons();
                break;
            case R.id.item_fav:
                Log.e(TAG, "switchFav");
                view.switchFavLesson(false);
                break;
            case R.id.item_expand:
                Log.e(TAG, "");
                view.expandWords();
                break;
            case R.id.item_general:
                Log.e(TAG, "update news general");
                view.updateNewsAPI(Constants.NEWS_CATEGORY_GENERAL);
                break;
            case R.id.item_business:
                Log.e(TAG, "update news business");
                view.updateNewsAPI(Constants.NEWS_CATEGORY_BUSINESS);
                break;
            case R.id.item_technology:
                Log.e(TAG, "update news technology");
                view.updateNewsAPI(Constants.NEWS_CATEGORY_TECHNOLOGY);
                break;
            case R.id.item_science:
                Log.e(TAG, "update news science");
                view.updateNewsAPI(Constants.NEWS_CATEGORY_SCIENCE);
                break;
            case R.id.item_entertainment:
                Log.e(TAG, "update news entertainment");
                view.updateNewsAPI(Constants.NEWS_CATEGORY_ENTERTAINMENT);
                break;
            case R.id.item_zhihu:
                Log.e(TAG, "switch Zhihu");
                view.switchZhihu();
                break;

            default:
                break;
        }
    }
}
