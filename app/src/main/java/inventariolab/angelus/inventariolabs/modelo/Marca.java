package inventariolab.angelus.inventariolabs.modelo;

/**
 * Created by Angelus on 15/08/2017.
 */

public class Marca {
    private int id;
    private String name;

    public Marca() {

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

    @Override
    public String toString() {
        return "Marca{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
