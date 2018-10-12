
package flightapp.entities;

import java.util.Date;

public class Flight extends Entity {
    private Date liftDate;
    private Destination destination;

    public Flight(Integer id, Date liftDate, Destination destination) {
        super(id);

        this.liftDate = liftDate;
        this.destination = destination;
    }

    public Destination getDestination() {
        return this.destination;
    }

    public Date getLiftDate() {
        return this.liftDate;
    }
}
