package flightapp.entities;

public class Baggage extends Entity {
    private Flight flight;
    private Integer weight;

    public Baggage(Integer id, Flight flight) {
        super(id);

        this.flight = flight;
    }

    public Flight getFlight() {
        return this.flight;
    }

    public Integer getWeight() {
        return this.weight;
    }
}
