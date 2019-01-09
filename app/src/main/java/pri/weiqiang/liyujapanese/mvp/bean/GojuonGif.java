package pri.weiqiang.liyujapanese.mvp.bean;

public class GojuonGif {

    String rome;
    int hiragana;
    int katakana;

    public GojuonGif(String rome, int hiragana, int katakana) {
        this.rome = rome;
        this.hiragana = hiragana;
        this.katakana = katakana;
    }

    @Override
    public String toString() {
        return "GojuonGif{" +
                "rome='" + rome + '\'' +
                ", hiragana=" + hiragana +
                ", katakana=" + katakana +
                '}';
    }

    public String getRome() {
        return rome;
    }

    public void setRome(String rome) {
        this.rome = rome;
    }

    public int getHiragana() {
        return hiragana;
    }

    public void setHiragana(int hiragana) {
        this.hiragana = hiragana;
    }

    public int getKatakana() {
        return katakana;
    }

    public void setKatakana(int katakana) {
        this.katakana = katakana;
    }
}


