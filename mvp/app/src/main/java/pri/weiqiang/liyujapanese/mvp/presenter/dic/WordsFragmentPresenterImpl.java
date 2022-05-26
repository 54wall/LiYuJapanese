package pri.weiqiang.liyujapanese.mvp.presenter.dic;

import android.util.Log;

import java.util.List;

import io.reactivex.functions.Consumer;
import pri.weiqaing.common.base.mvp.BasePresenter;
import pri.weiqaing.common.utils.RandomListUtil;
import pri.weiqiang.liyujapanese.mvp.bean.dic.Word;
import pri.weiqiang.liyujapanese.mvp.model.dic.WordsFragmentModel;
import pri.weiqiang.liyujapanese.mvp.model.dic.WordsFragmentModelImpl;
import pri.weiqiang.liyujapanese.mvp.view.dic.WordsFragmentView;

/**
 * Created by weiqiang on 2018/3/16.
 */

public class WordsFragmentPresenterImpl extends BasePresenter<WordsFragmentView> implements BasePresenter.WordsFragmentPresenter {

    private final String TAG = getClass().getSimpleName();
    WordsFragmentModel model;
    private List<Word> wordList;
    private String lesson;

    public WordsFragmentPresenterImpl(WordsFragmentView view, String lesson) {
        super(view);
        this.lesson = lesson;
        model = new WordsFragmentModelImpl();
    }

    @Override
    public void initWordsFragment() {
        view.setRecyclerView();
        model.getData(new Consumer<List<Word>>() {
            @Override
            public void accept(List<Word> list) throws Exception {
                view.setData(list);
                wordList = list;
            }
        }, lesson);
    }

    @Override
    public void randomList() {
        wordList = RandomListUtil.getInstance().randomList(wordList);
        view.setRecyclerView();
        view.setData(wordList);

    }

    @Override
    public void unsubscribe() {
        Log.e(TAG, "unsubscribe()");
        model.unsubscribe();
    }
}
