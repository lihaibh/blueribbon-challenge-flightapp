package flightapp.data;

import java.time.LocalDate;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import edu.emory.mathcs.backport.java.util.Arrays;
import flightapp.entities.Baggage;
import flightapp.entities.Destination;
import flightapp.entities.Flight;
import flightapp.entities.Ticket;

@Service
public class FakeInMemDataSource {

    private List<Destination> destinations;

    // Represent the list of records of flights on the data source
    private List<Flight> flights;

    // Represent the list of ticket records for flights in the data source
    private List<Ticket> tickets;

    // Represent the list of bagage records
    private List<Baggage> bagages;

    public FakeInMemDataSource() {}


    public void addBaggage(Baggage baggage) {
        this.bagages.add(baggage);
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }

    public void addFlight(Flight flight) {
        this.flights.add(flight);
    }


    private List<Destination> generateDestinations() {
        return List.of(new Destination(1, "New York"), new Destination(2, "Germany"),
                new Destination(3, "Africa"), new Destination(4, "Spain"));
    }

    public List<Destination> getDestinations() {
        return this.destinations;
    }

    public List<Flight> getFlights() {
        return this.flights;
    }

    public List<Ticket> getTickets() {
        return this.tickets;
    }

    public List<Baggage> getBaggages() {
        return this.bagages;
    }

    @PostConstruct
    public void init() {
        this.destinations = this.generateDestinations();

        Flight[] flights = {
                new Flight(1, java.sql.Date.valueOf(LocalDate.now().plusDays(1)),
                        this.destinations.get(0)),
                new Flight(2, java.sql.Date.valueOf(LocalDate.now().plusDays(2)),
                        this.destinations.get(1)),
                new Flight(3, java.sql.Date.valueOf(LocalDate.now().plusDays(2)),
                        this.destinations.get(0))};

        Baggage[] baggages = {new Baggage(1, flights[0]), new Baggage(2, flights[1]),
                new Baggage(3, flights[1])};

        Ticket[] tickets = {new Ticket(1, flights[0]), new Ticket(2, flights[0]),
                new Ticket(3, flights[1]), new Ticket(3, flights[2]), new Ticket(3, flights[0])};



        this.flights = Arrays.asList(flights);
        this.bagages = Arrays.asList(baggages);
        this.tickets = Arrays.asList(tickets);

    }
}
