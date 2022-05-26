package pri.weiqiang.liyujapanese.mvp.presenter.dic;


import android.util.Log;

import java.util.List;

import io.reactivex.functions.Consumer;
import pri.weiqaing.common.base.mvp.BasePresenter;
import pri.weiqaing.common.utils.RandomListUtil;
import pri.weiqiang.liyujapanese.mvp.bean.dic.Word;
import pri.weiqiang.liyujapanese.mvp.model.dic.FavWordsFragmentModel;
import pri.weiqiang.liyujapanese.mvp.model.dic.FavWordsFragmentModelImpl;
import pri.weiqiang.liyujapanese.mvp.view.dic.FavWordsFragmentView;


/**
 * Created by weiqiang on 2018/3/25.
 */

public class FavWordsFragmentPresenterImpl extends BasePresenter<FavWordsFragmentView> implements BasePresenter.FavWordsFragmentPresenter {

    private final String TAG = getClass().getSimpleName();

    FavWordsFragmentModel model;
    private List<Word> wordList;
    private String lesson;

    public FavWordsFragmentPresenterImpl(FavWordsFragmentView view, String lesson) {
        super(view);
        this.lesson = lesson;
        model = new FavWordsFragmentModelImpl();
    }

    @Override
    public void initFavWordsFragment() {
        view.setRecyclerView();

        model.getData(new Consumer<List<Word>>() {
            @Override
            public void accept(List<Word> words) throws Exception {
                Log.e(TAG, "words.size:" + words.size());
                view.setData(words);
                wordList = words;
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e(TAG, "model.getData-throwable:" + throwable.toString());
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

