package pri.weiqiang.liyujapanese.mvp.presenter;

import java.util.List;

import io.reactivex.functions.Consumer;
import pri.weiqiang.liyujapanese.mvp.bean.Word;
import pri.weiqiang.liyujapanese.mvp.model.BaseModel;
import pri.weiqiang.liyujapanese.mvp.model.WordsFragmentModelImpl;
import pri.weiqiang.liyujapanese.mvp.view.BaseView;
import pri.weiqiang.liyujapanese.utils.RandomListUtil;

/**
 * Created by weiqiang on 2018/3/16.
 */

public class WordsFragmentPresenterImpl extends BasePresenter<BaseView.WordsFragmentView> implements BasePresenter.WordsFragmentPresenter {

    private final String TAG = getClass().getSimpleName();
    BaseModel.WordsFragmentModel model;
    private List<Word> wordList;
    private String lesson;

    public WordsFragmentPresenterImpl(BaseView.WordsFragmentView view, String lesson) {
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
}
