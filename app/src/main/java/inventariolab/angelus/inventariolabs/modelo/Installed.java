package inventariolab.angelus.inventariolabs.modelo;

/**
 * Created by Angelus on 15/08/2017.
 */

public class Installed {

    private int id;
    private int software_id;
    private int inventory_id;

    public Installed() {
    }

    public Installed(int id, int software_id, int inventory_id) {
        this.id = id;
        this.software_id = software_id;
        this.inventory_id = inventory_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSoftware_id() {
        return software_id;
    }

    public void setSoftware_id(int software_id) {
        this.software_id = software_id;
    }

    public int getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
    }

    @Override
    public String toString() {
        return "Installed{" +
                "id=" + id +
                ", software_id=" + software_id +
                ", inventory_id=" + inventory_id +
                '}';
    }
}
