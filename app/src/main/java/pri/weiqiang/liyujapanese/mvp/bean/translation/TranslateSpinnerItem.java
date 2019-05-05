package pri.weiqiang.liyujapanese.mvp.bean.translation;

public class TranslateSpinnerItem {

    int icon;
    String name;
    boolean hasIcon;


    public TranslateSpinnerItem(int icon, String name, boolean hasIcon) {
        this.icon = icon;
        this.name = name;
        this.hasIcon = hasIcon;
    }

    @Override
    public String toString() {
        return "TranslateSpinnerItem{" +
                "icon=" + icon +
                ", name='" + name + '\'' +
                ", hasIcon=" + hasIcon +
                '}';
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHasIcon() {
        return hasIcon;
    }

    public void setHasIcon(boolean hasIcon) {
        this.hasIcon = hasIcon;
    }
}
