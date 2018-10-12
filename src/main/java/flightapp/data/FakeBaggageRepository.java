package flightapp.data;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import flightapp.entities.Baggage;

@Service
public class FakeBaggageRepository implements Repository<Baggage> {
    @Autowired
    private FakeInMemDataSource fakeData;

    @Override
    public List<Baggage> filter(Predicate<Baggage> predicate) {
        return this.fakeData.getBaggages().stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public void save(Baggage entity) {
        fakeData.addBaggage(entity);
    }
}
