package inventariolab.angelus.inventariolabs.modelo;
/**
 * Created by Angelus on 15/08/2017.
 */

public class Usuarios {
    private int id;
    private String email;
    private String password;
    private String usertype;
    private int teacher_id;

    public Usuarios() {
    }

    public Usuarios(int id, String email, String password, String usertype, int teacher_id) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.usertype = usertype;
        this.teacher_id = teacher_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    @Override
    public String toString() {
        return "Usuarios{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", usertype='" + usertype + '\'' +
                ", teacher_id=" + teacher_id +
                '}';
    }
}
