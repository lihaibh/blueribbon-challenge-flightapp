package flightapp.controllers;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import flightapp.checkers.TicketChecker;
import flightapp.services.Cache;

@RestController
public class TicketAvailablityController {
    static Logger logger = LogManager.getLogger(TicketAvailablityController.class.getName());

    @Autowired
    private TicketChecker ticketChecker;

    @Autowired
    private Cache<Boolean> cache;

    /**
     * Check the availability of the passanger's ticket
     * 
     */
    @GetMapping("/isTicketAvailable")
    public Boolean isTicketAvailable(@RequestParam(name = "id", required = true) Integer id) {
        logger.info("A user checked if his ticket is available");

        // Get function bound to it's arguments
        Supplier<Boolean> isValidByTicketFunction = () -> this.ticketChecker.isValid(id);

        // Create an identifier for the argument list
        Integer argumentsIdentifier = Cache.produceIdentifier(Arrays.asList(id));

        // Cache if its not exists for 5 hours - there is no need to invoke the function if the
        // result is chached
        return cache.putAndGetResult(isValidByTicketFunction, argumentsIdentifier, 5,
                TimeUnit.HOURS);
    }
}
