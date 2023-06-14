package produktdatenbank.model;

import java.util.List;

public class Firma extends Object {

    public Firma(int id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "Firma [id=" + getId() + ", name=" + getName() + "]";
    }
}
