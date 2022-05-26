package pri.weiqiang.liyujapanese.mvp.bean.gojuon;

public class GojuonSound {

    String rome;
    int resId;

    public GojuonSound(String rome, int resId) {
        this.rome = rome;
        this.resId = resId;
    }

    @Override
    public String toString() {
        return "GojuonSound{" +
                "rome='" + rome + '\'' +
                ", resId=" + resId +
                '}';
    }

    public String getRome() {
        return rome;
    }

    public void setRome(String rome) {
        this.rome = rome;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
