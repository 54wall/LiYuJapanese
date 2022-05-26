package pri.weiqiang.liyujapanese.mvp.bean.gojuon;

public class GojuonItem {

    int id;
    int row;
    int column;
    String hiragana;
    String katakana;
    String rome;
    int category;
    int type;
    boolean existed;

    public GojuonItem(int id, int row, int column, String hiragana, String katakana, String rome, int category, int type, boolean existed) {
        this.id = id;
        this.row = row;
        this.column = column;
        this.hiragana = hiragana;
        this.katakana = katakana;
        this.rome = rome;
        this.category = category;
        this.type = type;
        this.existed = existed;
    }

    @Override
    public String toString() {
        return "GojuonItem{" +
                "id=" + id +
                ", row=" + row +
                ", column=" + column +
                ", hiragana='" + hiragana + '\'' +
                ", katakana='" + katakana + '\'' +
                ", rome='" + rome + '\'' +
                ", category=" + category +
                ", type=" + type +
                ", existed=" + existed +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
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

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isExisted() {
        return existed;
    }

    public void setExisted(boolean existed) {
        this.existed = existed;
    }
}
