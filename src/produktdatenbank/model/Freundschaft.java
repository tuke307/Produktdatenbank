package produktdatenbank.model;

public class Freundschaft {
    private int person_id1;
    private int person_id2;

    public Freundschaft(int person_id1, int person_id2) {
        this.person_id1 = person_id1;
        this.person_id2 = person_id2;
    }

    public int getPerson_id1() {
        return person_id1;
    }

    public int getPerson_id2() {
        return person_id2;
    }
}
