package pri.weiqiang.liyujapanese.mvp.bean.newsapi;

import java.util.List;

public class NewsEntity implements DisplaybleNews {

    /**
     * images : ["http://pic4.zhimg.com/5615a788a32c2cc9469f825c4622879b.jpg"]
     * type : 0
     * id : 9115087
     * ga_prefix : 010222
     * title : 小事 · 悲喜无常
     */

    private int type;
    private int id;
    private String ga_prefix;
    private String title;
    private List<String> images;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
