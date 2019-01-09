package pri.weiqiang.liyujapanese.mvp.presenter;

import android.content.DialogInterface;

import java.util.ArrayList;
import java.util.List;

import pri.weiqiang.liyujapanese.MyApplication;
import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.config.Constants;
import pri.weiqiang.liyujapanese.manager.ActivityManager;
import pri.weiqiang.liyujapanese.manager.SharedPreferenceManager;
import pri.weiqiang.liyujapanese.mvp.bean.GojuonItem;
import pri.weiqiang.liyujapanese.mvp.model.BaseModel;
import pri.weiqiang.liyujapanese.mvp.model.PuzzleActivityModelImpl;
import pri.weiqiang.liyujapanese.mvp.view.BaseView;
import pri.weiqiang.liyujapanese.ui.activity.PuzzleActivity;
import pri.weiqiang.liyujapanese.utils.ResourceUtils;

public class PuzzleActivityPresenterImpl extends BasePresenter<BaseView.PuzzleActivityView> implements BasePresenter.PuzzleActivityPresenter {

    BaseModel.PuzzleActivityModel model;

    public PuzzleActivityPresenterImpl(BaseView.PuzzleActivityView view) {
        super(view);
        model = new PuzzleActivityModelImpl();
    }

    @Override
    public void initPuzzleActivity() {

        view.showSelectDialog(model.getOptions());

    }

    @Override
    public void loadData() {

        List<GojuonItem> items = model.getItems();
        List<GojuonItem> jams = new ArrayList<>();

        GojuonItem current = items.get(0);
        jams.add(items.get(1));
        jams.add(items.get(2));
        jams.add(items.get(3));

        view.setData(current, jams);

    }

    @Override
    public void checkTypeSelect(int which) {

        switch (which) {

            case PuzzleActivity.TYPE_HIRAGANA_ROME:
                view.setTitle(R.string.hiragana_rome);
                view.clearCount();
                break;
            case PuzzleActivity.TYPE_HIRAGANA_KATAKANA:
                view.setTitle(R.string.hiragana_katakana);
                view.clearCount();
                break;
            case PuzzleActivity.TYPE_KATAKANA_ROME:
                view.setTitle(R.string.katakana_rome);
                view.clearCount();
                break;
            default:
                break;

        }

        loadData();

    }

    @Override
    public void checkAnswerSelect(int id, GojuonItem current, List<GojuonItem> items) {

        switch (id) {

            case R.id.btn_answer1:

                if (items.get(0).getId() == current.getId()) {

                    view.addCount();
                    loadData();

                } else {
                    view.clearCount();
                    showResult(current);

                }

                break;
            case R.id.btn_answer2:

                if (items.get(1).getId() == current.getId()) {

                    view.addCount();
                    loadData();

                } else {
                    view.clearCount();
                    showResult(current);
                }

                break;
            case R.id.btn_answer3:

                if (items.get(2).getId() == current.getId()) {
                    view.addCount();
                    loadData();

                } else {
                    view.clearCount();
                    showResult(current);
                }

                break;
            case R.id.btn_answer4:

                if (items.get(3).getId() == current.getId()) {
                    view.addCount();
                    loadData();

                } else {
                    view.clearCount();
                    showResult(current);
                }

                break;
            default:
                break;

        }

    }

    @Override
    public void checkMenuSelect(int id) {

        switch (id) {
            case R.id.menu_help:

                view.showDialog(R.drawable.ic_help_outline,
                        R.string.help,
                        ResourceUtils.getString(MyApplication.getInstance(), R.string.tips_puzzle_contents));

                break;
            case R.id.menu_ranking:

                int hs = SharedPreferenceManager.getInstance().getInt(Constants.HIGHEST_SCORE, 0);

                view.showDialog(R.drawable.ic_filter_list, R.string.highedt_score,
                        ResourceUtils.getString(MyApplication.getInstance(), R.string.tips_highest_score_contents) +
                                String.valueOf(hs));

                break;
            default:
                break;

        }


    }

    private void showResult(GojuonItem currrent) {

        String msg = currrent.getHiragana() + " -> " + currrent.getKatakana() + " -> " + currrent.getRome();

        view.showResultDialog(R.string.sad, msg, R.drawable.ic_mood_bad,
                R.string.one_more_time_2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        loadData();
                        dialog.dismiss();
                    }
                }, R.string.exit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        ActivityManager.getCurrent().finish();
                    }
                });

    }
}
