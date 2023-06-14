package produktdatenbank.model;

public class Besitzt {
    private int person_id;
    private int produkt_id;

    public Besitzt(int person_id, int produkt_id) {
        this.person_id = person_id;
        this.produkt_id = produkt_id;
    }

    public int getPerson_id() {
        return person_id;
    }

    public int getProdukt_id() {
        return produkt_id;
    }
}
