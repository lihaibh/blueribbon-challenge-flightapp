package flightapp.entities;

public abstract class Entity {
    private Integer id;

    Entity(Integer id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
