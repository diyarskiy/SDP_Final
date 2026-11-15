package strategy;

import java.util.Comparator;
import java.util.List;
import adapter.Flight;

public class FastestStrategy implements OfferStrategy {
    @Override
    public Flight selectFlight(List<Flight> flights) {
        if (flights == null || flights.isEmpty()) {
            throw new IllegalArgumentException("Flight list must not be empty");
        }
        return flights.stream()
                .min(Comparator.comparingInt(Flight::getDurationMinutes)
                        .thenComparingInt(Flight::getPrice))
                .orElseThrow();
    }
}
