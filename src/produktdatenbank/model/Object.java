package produktdatenbank.model;

public class Object {
    private int id;
    private String name;

    public Object(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Object [id=" + getId() + ", name=" + getName() + "]";
    }
}
