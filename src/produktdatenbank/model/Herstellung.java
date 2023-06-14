package produktdatenbank.model;

public class Herstellung {
    private int produkt_id;
    private int firma_id;

    public Herstellung(int produkt_id, int firma_id) {
        this.produkt_id = produkt_id;
        this.firma_id = firma_id;
    }

    public int getProdukt_id() {
        return produkt_id;
    }

    public int getFirma_id() {
        return firma_id;
    }
}
