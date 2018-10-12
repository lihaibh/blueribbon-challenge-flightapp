package flightapp.services;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import org.springframework.stereotype.Service;

@Service
public class Cache<V> {
    private Map<Integer, ScheduledPair<V>> data = new Hashtable<Integer, ScheduledPair<V>>();
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public synchronized V putAndGetResult(Supplier<V> boundFunction, Integer identifier, int period,
            TimeUnit unit) {
        ScheduledPair<V> mappedResult = data.get(identifier);

        // invoke the function get the result and cache it
        if (mappedResult == null) {
            V computedValue = boundFunction.get();

            ScheduledPair<V> scheduledPair = Cache.ScheduledPair.of(computedValue,
                    scheduler.schedule(() -> remove(identifier), period, unit));

            this.data.put(identifier, scheduledPair);
        }

        return mappedResult.Value;
    }

    public V get(String identifier) {
        ScheduledPair<V> pair = data.get(identifier);

        if (null != pair) {
            return pair.Value;
        }

        return null;
    }

    public static Integer produceIdentifier(List<Object> arguments) {
        return Arrays.hashCode((Object[]) arguments.toArray());
    }

    private synchronized void remove(Integer identifier) {
        data.remove(identifier);
    }

    public static final class ScheduledPair<V> {
        public V Value;
        public ScheduledFuture<?> Future;

        public ScheduledPair(V value, ScheduledFuture<?> future) {
            this.Value = value;
            this.Future = future;
        }

        public static <R> ScheduledPair<R> of(R value, ScheduledFuture<?> future) {
            return new ScheduledPair<R>(value, future);
        }
    }
}
