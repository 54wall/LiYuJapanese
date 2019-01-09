package pri.weiqiang.liyujapanese.mvp.bean.zhihu;

import java.util.List;

public class LatestDailyEntity {

    /**
     * http://news-at.zhihu.com/api/4/news/latest
     * {"date":"20180411",
     * "stories":
     * [{"images":["https:\/\/pic1.zhimg.com\/v2-939ca8137d836f63e64feea5d4fe34d0.jpg"],"type":0,"id":9677811,"ga_prefix":"041110","title":"发现医生有个职业病，都不会把话说满"},
     * {"images":["https:\/\/pic3.zhimg.com\/v2-dff8867d32f974f541aaa692eb9d7d4a.jpg"],"type":0,"id":9677881,"ga_prefix":"041109","title":"「原生家庭」的说法越来越流行，甚至有些可怕过头了"},
     * {"images":["https:\/\/pic3.zhimg.com\/v2-af77097445f8cfb5e0a0ee1a4aaf916e.jpg"],"type":0,"id":9677640,"ga_prefix":"041108","title":"为什么我说每个职场人都该结交 1~2 个靠谱的猎头朋友？"},
     * {"images":["https:\/\/pic4.zhimg.com\/v2-51cb6fcfec4825c83c21e3bd7bbeca37.jpg"],"type":0,"id":9677945,"ga_prefix":"041107","title":"你买的「颐和园路 5 号口红」，可能是支「非法」化妆品"},
     * {"images":["https:\/\/pic2.zhimg.com\/v2-f0e3ccc29896563dd31598e4e7a129b9.jpg"],"type":0,"id":9677769,"ga_prefix":"041107","title":"给儿女带二胎的老人们"},{"images":["https:\/\/pic2.zhimg.com\/v2-d6f26c16d1000ea054a8893fd0a897e9.jpg"],"type":0,"id":9677848,"ga_prefix":"041106","title":"瞎扯 · 如何正确地吐槽"}],"top_stories":[{"image":"https:\/\/pic2.zhimg.com\/v2-0df232b14b7518ff128e3760dd932245.jpg","type":0,"id":9677811,"ga_prefix":"041110","title":"发现医生有个职业病，都不会把话说满"},{"image":"https:\/\/pic4.zhimg.com\/v2-5d7a3322fe3b8e7be6312c74f51f7d0f.jpg","type":0,"id":9677945,"ga_prefix":"041107","title":"你买的「颐和园路 5 号口红」，可能是支「非法」化妆品"},{"image":"https:\/\/pic1.zhimg.com\/v2-3a972b3f1b6c10e5ce28e9d7e66f2648.jpg","type":0,"id":9677881,"ga_prefix":"041109","title":"「原生家庭」的说法越来越流行，甚至有些可怕过头了"},{"image":"https:\/\/pic4.zhimg.com\/v2-6ae7ca613bb44ad8b0cb3c8bbba45bf7.jpg","type":0,"id":9677769,"ga_prefix":"041107","title":"给儿女带二胎的老人们"},{"image":"https:\/\/pic4.zhimg.com\/v2-a32d9edd1a04bcd9cbfdca02303f0a8f.jpg","type":0,"id":9677640,"ga_prefix":"041108","title":"为什么我说每个职场人都该结交 1~2 个靠谱的猎头朋友？"}]}
     */

    private String date;
    private List<StoriesEntity> stories;
    private List<TopStoriesEntity> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesEntity> getStories() {
        return stories;
    }

    public void setStories(List<StoriesEntity> stories) {
        this.stories = stories;
    }

    public List<TopStoriesEntity> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesEntity> top_stories) {
        this.top_stories = top_stories;
    }


}
