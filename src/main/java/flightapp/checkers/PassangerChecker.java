package flightapp.checkers;

import flightapp.entities.Passanger;

public interface PassangerChecker extends Checker<Passanger> {
    public Boolean isValid(Passanger passanger);
}
