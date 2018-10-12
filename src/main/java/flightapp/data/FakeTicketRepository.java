package flightapp.data;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import flightapp.entities.Ticket;

@Service
public class FakeTicketRepository implements Repository<Ticket> {
    @Autowired
    private FakeInMemDataSource fakeData;

    @Override
    public List<Ticket> filter(Predicate<Ticket> predicate) {
        return this.fakeData.getTickets().stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public void save(Ticket entity) {
        fakeData.addTicket(entity);
    }
}
