package inventariolab.angelus.inventariolabs.modelo;

/**
 * Created by Angelus on 15/08/2017.
 */

public class Perdidas
{
    private int id;
    private String desc;
    private int inventory_id;

    public Perdidas() {
    }

    public Perdidas(int id, String desc, int inventory_id) {
        this.id = id;
        this.desc = desc;
        this.inventory_id = inventory_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
    }

    @Override
    public String toString() {
        return "Perdidas{" +
                "id=" + id +
                ", desc='" + desc + '\'' +
                ", inventory_id=" + inventory_id +
                '}';
    }
}
