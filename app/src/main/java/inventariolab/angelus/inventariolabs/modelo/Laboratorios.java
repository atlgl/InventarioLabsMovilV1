package inventariolab.angelus.inventariolabs.modelo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Angelus on 15/08/2017.
 */

public class Laboratorios implements Parcelable {

    private int id;
    private String name;
    private String desc;
    private String labstate;
    private String created_at;
    private String updated_at;



    public Laboratorios() {
    }

    protected Laboratorios(Parcel in) {
        id = in.readInt();
        name = in.readString();
        desc = in.readString();
        labstate = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
    }

    public static final Creator<Laboratorios> CREATOR = new Creator<Laboratorios>() {
        @Override
        public Laboratorios createFromParcel(Parcel in) {
            return new Laboratorios(in);
        }

        @Override
        public Laboratorios[] newArray(int size) {
            return new Laboratorios[size];
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

    public String getLabstate() {
        return labstate;
    }

    public void setLabstate(String labstate) {
        this.labstate = labstate;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
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
        dest.writeString(labstate);
        dest.writeString(created_at);
        dest.writeString(updated_at);
    }
}
