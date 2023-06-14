package produktdatenbank.model;

public class Produkt {
    private int id;
    private String name;

    public Produkt(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Produkt [id=" + id + ", name=" + name + "]";
    }
}
