package pri.weiqiang.liyujapanese.mvp.view.newsapi;

public interface NewsAPIFragmentView {
    <T> void refreshNews(T t);

    <T> void loadNewsBefore(T t);
}
