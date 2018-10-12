package flightapp.entities;

public class Ticket extends Entity {
    private Flight flight;

    public Ticket(Integer id, Flight flight) {
        super(id);

        this.flight = flight;
    }

    public Flight getFlight() {
        return this.flight;
    }
}
