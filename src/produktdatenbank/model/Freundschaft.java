package produktdatenbank.model;

public class Freundschaft {
    private int personId1;
    private int personId2;

    public Freundschaft(int personId1, int personId2) {
        this.personId1 = personId1;
        this.personId2 = personId2;
    }

    public int getPersonId1() {
        return personId1;
    }

    public int getPersonId2() {
        return personId2;
    }

    @Override
    public String toString() {
        return "Freundschaft [personId1=" + personId1 + ", personId2=" + personId2 + "]";
    }
}
