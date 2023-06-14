package produktdatenbank.model;

import java.util.List;

public class Firma {
    private int id;
    private String name;

    //Konstruktor
    public Firma(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
