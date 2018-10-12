package flightapp.checkers;

import java.util.List;
import org.springframework.stereotype.Service;
import flightapp.entities.Passanger;

@Service
public class PassangerStationChecker implements PassangerChecker {

    private List<PassangerChecker> stations;

    @Override
    public Boolean isValid(Passanger passanger) {
        // Must pass all stations
        return stations.stream().filter((checker) -> {
            return checker.isValid(passanger);
        }).count() == stations.size();
    }
}
