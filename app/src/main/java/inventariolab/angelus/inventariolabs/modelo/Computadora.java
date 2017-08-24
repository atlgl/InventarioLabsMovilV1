package inventariolab.angelus.inventariolabs.modelo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Angelus on 15/08/2017.
 */

public class Computadora implements Parcelable {

    private int id;
    private String modelname;
    private String desc;
    private int mark_id;
    private String created_at;
    private String updated_at;

    private Marca mark;

    public Computadora() {
    }

    protected Computadora(Parcel in) {
        id = in.readInt();
        modelname = in.readString();
        desc = in.readString();
        mark_id = in.readInt();
        created_at = in.readString();
        updated_at = in.readString();
    }

    public static final Creator<Computadora> CREATOR = new Creator<Computadora>() {
        @Override
        public Computadora createFromParcel(Parcel in) {
            return new Computadora(in);
        }

        @Override
        public Computadora[] newArray(int size) {
            return new Computadora[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelname() {
        return modelname;
    }

    public void setModelname(String modelname) {
        this.modelname = modelname;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getMark_id() {
        return mark_id;
    }

    public void setMark_id(int mark_id) {
        this.mark_id = mark_id;
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

    public Marca getMark() {
        return mark;
    }

    public void setMark(Marca mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Computadora{" +
                "id=" + id +
                ", modelname='" + modelname + '\'' +
                ", desc='" + desc + '\'' +
                ", mark_id=" + mark_id +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", mark=" + mark +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(modelname);
        dest.writeString(desc);
        dest.writeInt(mark_id);
        dest.writeString(created_at);
        dest.writeString(updated_at);
    }
}
