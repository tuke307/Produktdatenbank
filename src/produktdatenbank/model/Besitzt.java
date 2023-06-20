package produktdatenbank.model;

public class Besitzt {
    private int personId;
    private int produktId;

    public Besitzt(int personId, int produktId) {
        this.personId = personId;
        this.produktId = produktId;
    }

    public int getPersonId() {
        return personId;
    }

    public int getProduktId() {
        return produktId;
    }

    @Override
    public String toString() {
        return "Besitzt [personId=" + personId + ", produktId=" + produktId + "]";
    }
}
