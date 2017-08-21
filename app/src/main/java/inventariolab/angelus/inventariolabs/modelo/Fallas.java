package inventariolab.angelus.inventariolabs.modelo;

/**
 * Created by Angelus on 15/08/2017.
 */

public class Fallas {
    private int id;
    private int inventory_id;
    private String desc;
    private String failtype;
    private String failstate;

    public Fallas() {
    }

    public Fallas(int id, int inventory_id, String desc, String failtype, String failstate) {
        this.id = id;
        this.inventory_id = inventory_id;
        this.desc = desc;
        this.failtype = failtype;
        this.failstate = failstate;
    }

    @Override
    public String toString() {
        return "Fallas{" +
                "id=" + id +
                ", inventory_id=" + inventory_id +
                ", desc='" + desc + '\'' +
                ", failtype='" + failtype + '\'' +
                ", failstate='" + failstate + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getFailtype() {
        return failtype;
    }

    public void setFailtype(String failtype) {
        this.failtype = failtype;
    }

    public String getFailstate() {
        return failstate;
    }

    public void setFailstate(String failstate) {
        this.failstate = failstate;
    }
}
