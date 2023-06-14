package produktdatenbank.model;

public class Person extends Object {
    private String geschlecht;

    public Person(int id, String name, String geschlecht) {
        super(id, name);
        this.geschlecht = geschlecht;
    }

    public String getGeschlecht() {
        return geschlecht;
    }

    @Override
    public String toString() {
        return "Person [id=" + getId() + ", name=" + getName() + ", geschlecht=" + geschlecht + "]";
    }
}
