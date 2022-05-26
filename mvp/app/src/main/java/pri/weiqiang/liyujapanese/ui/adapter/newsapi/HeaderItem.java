package pri.weiqiang.liyujapanese.ui.adapter.newsapi;


import java.util.ArrayList;
import java.util.List;

import pri.weiqiang.liyujapanese.mvp.bean.newsapi.DisplaybleNews;
import pri.weiqiang.liyujapanese.mvp.bean.zhihu.TopStoriesEntity;

/**
 * Created by Administrator on 2017/1/4.
 */

public class HeaderItem implements DisplaybleNews {
    private List<TopStoriesEntity> topStories;
    private List<String> images;
    private List<String> titles;
    private List<Integer> ids;

    public HeaderItem(List<TopStoriesEntity> topStories) {
        this.topStories = topStories;
        initData();
    }

    private void initData() {
        images = new ArrayList<>();
        titles = new ArrayList<>();
        ids = new ArrayList<>();

        for (TopStoriesEntity topStory : topStories) {
            images.add(topStory.getImage());
            titles.add(topStory.getTitle());
            ids.add(topStory.getId());
        }
    }

    public List<TopStoriesEntity> getTopStories() {
        return topStories;
    }

    public List<String> getImages() {
        return images;
    }

    public List<String> getTitles() {
        return titles;
    }

    public List<Integer> getIds() {
        return ids;
    }
}
