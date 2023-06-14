package produktdatenbank.model;

public class Produkt {
    private int id;
    private String name;
    //private Firma firma;

    public Produkt(int id, String name/*, Firma firma */) {
        this.id = id;
        this.name = name;
        //this.firma = firma;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /* public Firma getFirma() {
        return firma;
    } */
}
