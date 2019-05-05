package pri.weiqiang.liyujapanese.mvp.bean.gojuon;

public class GojuonTab {

    int type;
    String title;

    public GojuonTab(int type, String title) {
        this.type = type;
        this.title = title;
    }

    @Override
    public String toString() {
        return "GojuonTab{" +
                "type=" + type +
                ", title='" + title + '\'' +
                '}';
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
