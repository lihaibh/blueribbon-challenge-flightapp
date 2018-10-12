package flightapp.checkers;

public interface Checker<T> {
    public Boolean isValid(T data);
}
