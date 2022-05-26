package pri.weiqiang.liyujapanese.mvp.bean.pixivIllust;

public class PixivIllustTab {

    String mode;
    String title;

    public PixivIllustTab(String mode, String title) {
        this.mode = mode;
        this.title = title;
    }

    @Override
    public String toString() {
        return "PixivIllustTab{" +
                "mode='" + mode + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
