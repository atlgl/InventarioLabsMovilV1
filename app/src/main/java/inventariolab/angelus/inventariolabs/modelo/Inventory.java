package inventariolab.angelus.inventariolabs.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Angelus on 16/08/2017.
 */

public class Inventory implements Parcelable {
    private int id;
    private int user_id;
    private int computer_id;
    private int lab_id;
    private String barcode;
    private String inventorystate;
    private String created_at;
    private String updated_at;

    private Laboratorios lab;

    private Computadora computer;

    public Inventory() {
    }

    protected Inventory(Parcel in) {
        id = in.readInt();
        user_id = in.readInt();
        computer_id = in.readInt();
        lab_id = in.readInt();
        barcode = in.readString();
        inventorystate = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
    }

    public static final Creator<Inventory> CREATOR = new Creator<Inventory>() {
        @Override
        public Inventory createFromParcel(Parcel in) {
            return new Inventory(in);
        }

        @Override
        public Inventory[] newArray(int size) {
            return new Inventory[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getComputer_id() {
        return computer_id;
    }

    public void setComputer_id(int computer_id) {
        this.computer_id = computer_id;
    }

    public int getLab_id() {
        return lab_id;
    }

    public void setLab_id(int lab_id) {
        this.lab_id = lab_id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getInventorystate() {
        return inventorystate;
    }

    public void setInventorystate(String inventorystate) {
        this.inventorystate = inventorystate;
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

    public Laboratorios getLab() {
        return lab;
    }

    public void setLab(Laboratorios lab) {
        this.lab = lab;
    }

    public Computadora getComputer() {
        return computer;
    }

    public void setComputer(Computadora computer) {
        this.computer = computer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(user_id);
        dest.writeInt(computer_id);
        dest.writeInt(lab_id);
        dest.writeString(barcode);
        dest.writeString(inventorystate);
        dest.writeString(created_at);
        dest.writeString(updated_at);
    }
}
