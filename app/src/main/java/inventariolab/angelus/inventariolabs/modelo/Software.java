package inventariolab.angelus.inventariolabs.modelo;

/**
 * Created by Angelus on 15/08/2017.
 */

public class Software {

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
}
