package inventariolab.angelus.inventariolabs.modelo;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by Angelus on 15/08/2017.
 */

public class Maestros {

    private int id;
    private String firstname;
    private String lastname;
    private String secondname;
    private Date birthdate;
    private Bitmap photo;

    public Maestros() {
    }

    public Maestros(int id, String firstname, String lastname, String secondname, Date birthdate, Bitmap photo) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.secondname = secondname;
        this.birthdate = birthdate;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Maestros{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", secondname='" + secondname + '\'' +
                ", birthdate=" + birthdate +
                ", photo=" + photo +
                '}';
    }
}
