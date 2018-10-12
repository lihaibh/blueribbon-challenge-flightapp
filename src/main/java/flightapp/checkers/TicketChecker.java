package flightapp.checkers;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import flightapp.data.Repository;
import flightapp.entities.Ticket;

/**
 * Responsible for checking the ticket of the passanger
 */
public class TicketChecker implements Checker<Integer> {

    @Autowired
    private Repository<Ticket> ticketRepository;

    TicketChecker() {}

    @Override
    public Boolean isValid(Integer ticketId) {

        // Check if ticket exists (ordered) and it was not expired
        List<Ticket> validTickets = this.ticketRepository.filter((ticket) -> {
            return (ticket.getId() == ticketId)
                    && (ticket.getFlight().getLiftDate().before(new Date()));
        });

        // Check if the ticket of the passanger is avaiable
        return validTickets.size() == 1;
    }
}
