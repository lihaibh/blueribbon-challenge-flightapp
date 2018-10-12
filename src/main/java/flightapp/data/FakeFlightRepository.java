package flightapp.data;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import flightapp.entities.Flight;
import flightapp.entities.Ticket;

@Service
public class FakeFlightRepository implements Repository<Flight> {
    @Autowired
    private FakeInMemDataSource fakeData;

    @Override
    public List<Flight> filter(Predicate<Flight> predicate) {
        return this.fakeData.getFlights().stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public void save(Flight entity) {
        fakeData.addFlight(entity);
    }
}
