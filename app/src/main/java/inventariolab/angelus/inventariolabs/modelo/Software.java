package inventariolab.angelus.inventariolabs.modelo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Angelus on 15/08/2017.
 */

public class Software implements Parcelable {

    private int id;
    private String name;
    private String desc;

    public Software() {
    }

    public Software(int id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }

    protected Software(Parcel in) {
        id = in.readInt();
        name = in.readString();
        desc = in.readString();
    }

    public static final Creator<Software> CREATOR = new Creator<Software>() {
        @Override
        public Software createFromParcel(Parcel in) {
            return new Software(in);
        }

        @Override
        public Software[] newArray(int size) {
            return new Software[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Software{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(desc);


    }
}
