package pri.weiqiang.liyujapanese.mvp.bean.zhihu;

import android.os.Parcel;
import android.os.Parcelable;


public class EditorsEntity implements Parcelable {
    public static final Creator<EditorsEntity> CREATOR = new Creator<EditorsEntity>() {
        @Override
        public EditorsEntity createFromParcel(Parcel source) {
            return new EditorsEntity(source);
        }

        @Override
        public EditorsEntity[] newArray(int size) {
            return new EditorsEntity[size];
        }
    };
    /**
     * url : http://www.zhihu.com/people/wezeit
     * bio : 微在 Wezeit 主编
     * id : 70
     * avatar : http://pic4.zhimg.com/068311926_m.jpg
     * name : 益康糯米
     */

    private String url;
    private String bio;
    private int id;
    private String avatar;
    private String name;

    public EditorsEntity() {
    }

    protected EditorsEntity(Parcel in) {
        this.url = in.readString();
        this.bio = in.readString();
        this.id = in.readInt();
        this.avatar = in.readString();
        this.name = in.readString();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.bio);
        dest.writeInt(this.id);
        dest.writeString(this.avatar);
        dest.writeString(this.name);
    }
}
