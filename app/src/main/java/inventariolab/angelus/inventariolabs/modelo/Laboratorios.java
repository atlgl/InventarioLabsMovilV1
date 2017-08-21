package inventariolab.angelus.inventariolabs.modelo;
/**
 * Created by Angelus on 15/08/2017.
 */

public class Laboratorios {

    private int id;
    private String name;
    private String desc;
    private String labstate;
    private String created_at;
    private String updated_at;



    public Laboratorios() {
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
}
