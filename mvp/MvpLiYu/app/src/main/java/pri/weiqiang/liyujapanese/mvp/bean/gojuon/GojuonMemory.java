package pri.weiqiang.liyujapanese.mvp.bean.gojuon;

public class GojuonMemory {

    int id;
    String hiragana;
    String katakana;
    String rome;
    String memory;
    String chengyu;

    public GojuonMemory(int id, String hiragana, String katakana, String rome, String memory, String chengyu) {
        this.id = id;
        this.hiragana = hiragana;
        this.katakana = katakana;
        this.rome = rome;
        this.memory = memory;
        this.chengyu = chengyu;

    }

    @Override
    public String toString() {
        return "GojuonItem{" +
                "id=" + id +
                ", hiragana='" + hiragana + '\'' +
                ", katakana='" + katakana + '\'' +
                ", rome='" + rome + '\'' +
                ", memory=" + memory +
                ", chengyu=" + chengyu +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getHiragana() {
        return hiragana;
    }

    public void setHiragana(String hiragana) {
        this.hiragana = hiragana;
    }

    public String getKatakana() {
        return katakana;
    }

    public void setKatakana(String katakana) {
        this.katakana = katakana;
    }

    public String getRome() {
        return rome;
    }

    public void setRome(String rome) {
        this.rome = rome;
    }

    public String getMemory() {
        return memory;
    }

    public String getChengyu() {
        return chengyu;
    }

}
