package flightapp.entities;

public class Passanger extends Entity {
    private String identity;
    private String firstName;
    private String lastName;
    private Ticket ticket;

    public Passanger(Integer id) {
        super(id);
    }

    public String getIdentity() {
        return this.identity;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public Ticket getTicket() {
        return this.ticket;
    }
}
