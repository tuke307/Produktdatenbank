package produktdatenbank.model;

public class Produkt extends Object {
    
    public Produkt(int id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "Produkt [id=" + getId() + ", name=" + getName() + "]";
    }
}
