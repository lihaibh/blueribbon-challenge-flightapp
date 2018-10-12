package flightapp.controllers;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import flightapp.data.Repository;
import flightapp.entities.Baggage;
import flightapp.entities.Flight;

@RestController
public class BaggageCheckinController {

    @Autowired
    Repository<Baggage> baggageRepository;

    @Autowired
    Repository<Flight> flightRepository;

    private Boolean isBaggageExists(String id) {
        List<Baggage> baggages =
                baggageRepository.filter((baggage) -> baggage.getId() == Integer.parseInt(id));

        return null != baggages;
    }

    private Flight getFlightByDestinationId(String destinationId) {

        List<Flight> flights = flightRepository.filter((flight) -> {
            return (flight.getDestination().getId() == Integer.parseInt(destinationId)) &&
            // make sure to take available flights
            (flight.getLiftDate().after(new Date()));
        });

        return (null != flights) ? flights.get(0) : null;
    }

    @RequestMapping("/checkin")
    public boolean checkin(
            @RequestParam(name = "destinationId", required = true) String destinationId,
            @RequestParam(name = "baggageId", required = true) String baggageId) {
        if (isBaggageExists(baggageId)) {
            return false;
        }

        Flight flight = getFlightByDestinationId(destinationId);

        if (null == flight) {
            return false;
        }

        baggageRepository.save(new Baggage(Integer.parseInt(baggageId), flight));

        return true;
    }
}
