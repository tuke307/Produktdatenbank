package produktdatenbank.model;

public class Person {
    private int id;
    private String name;
    private String geschlecht;

    public Person(int id, String name, String geschlecht) {
        this.id = id;
        this.name = name;
        this.geschlecht = geschlecht;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGeschlecht() {
        return geschlecht;
    }
}
