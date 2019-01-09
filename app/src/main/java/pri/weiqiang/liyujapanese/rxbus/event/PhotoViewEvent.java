package pri.weiqiang.liyujapanese.rxbus.event;

public class PhotoViewEvent {

    String img_url;
    int img_id;

    public PhotoViewEvent(String img_url, int img_id) {
        this.img_url = img_url;
        this.img_id = img_id;
    }

    @Override
    public String toString() {
        return "PhotoViewEvent{" +
                "img_url='" + img_url + '\'' +
                ", img_id=" + img_id +
                '}';
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }
}
