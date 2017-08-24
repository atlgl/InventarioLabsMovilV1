package inventariolab.angelus.inventariolabs.modelo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Angelus on 15/08/2017.
 */

public class Marca implements Parcelable {
    private int id;
    private String name;

    public Marca() {

    }

    protected Marca(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    public static final Creator<Marca> CREATOR = new Creator<Marca>() {
        @Override
        public Marca createFromParcel(Parcel in) {
            return new Marca(in);
        }

        @Override
        public Marca[] newArray(int size) {
            return new Marca[size];
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

    @Override
    public String toString() {
        return "Marca{" +
                "id=" + id +
                ", name='" + name + '\'' +
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
    }
}
