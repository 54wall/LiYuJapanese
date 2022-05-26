package pri.weiqiang.liyujapanese.mvp.bean.newsapi;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by debajyotibasak on 17/12/17.
 */

public class NewsResponse {
    private String TAG = NewsResponse.class.getSimpleName();
    private String status;
    private int totalResults;
    private ArrayList<ArticleStructure> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public ArrayList<ArticleStructure> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<ArticleStructure> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        for (int i = 0; i < articles.size(); i++) {
            Log.e(TAG, "articles:" + articles.get(i).getTitle());
        }

        return "status:" + status +
                "totalResults:" + totalResults;
    }
}
