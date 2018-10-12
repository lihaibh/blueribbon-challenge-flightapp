package flightapp.entities;

public class Destination extends Entity {
    private String location;

    public Destination(Integer id, String location) {
        super(id);

        this.location = location;
    }

    public String getLocation() {
        return this.location;
    }
}
