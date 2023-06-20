package produktdatenbank.model;

public class Herstellung {
    private int produktId;
    private int firmaId;

    public Herstellung(int produktId, int firmaId) {
        this.produktId = produktId;
        this.firmaId = firmaId;
    }

    public int getProduktId() {
        return produktId;
    }

    public int getFirmaId() {
        return firmaId;
    }

    @Override
    public String toString() {
        return "Herstellung [produktId=" + produktId + ", firmaId=" + firmaId + "]";
    }
}
