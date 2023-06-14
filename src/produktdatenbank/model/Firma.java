package produktdatenbank.model;

import java.util.List;

public class Firma {
    private int id;
    private String name;
    //private List<Produkt> produkte;

    //Konstruktor
    public Firma(int id, String name/*, List<Produkt> produkte*/) {
        this.id = id;
        this.name = name;
        //this.produkte = produkte;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /* public List<Produkt> getProdukte() {
        return produkte;
    } */
}
