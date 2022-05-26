package pri.weiqiang.liyujapanese.mvp.bean;

public class BannerItem {

    int banner;
    String title;

    public BannerItem(int banner, String title) {
        this.banner = banner;
        this.title = title;
    }

    @Override
    public String toString() {
        return "BannerItem{" +
                "banner=" + banner +
                ", title='" + title + '\'' +
                '}';
    }

    public int getBanner() {
        return banner;
    }

    public void setBanner(int banner) {
        this.banner = banner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
