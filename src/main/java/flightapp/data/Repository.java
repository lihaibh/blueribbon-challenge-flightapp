package flightapp.data;

import java.util.List;
import java.util.function.Predicate;
import flightapp.entities.Entity;

public interface Repository<T extends Entity> {
    public List<T> filter(Predicate<T> predicate);

    public void save(T entity);
}
